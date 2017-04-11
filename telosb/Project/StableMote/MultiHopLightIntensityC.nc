/*
 * Copyright (c) 2006 Intel Corporation
 * All rights reserved.
 *
 * This file is distributed under the terms in the attached INTEL-LICENSE     
 * file. If you do not find these files, copies can be found by writing to
 * Intel Research Berkeley, 2150 Shattuck Avenue, Suite 1300, Berkeley, CA, 
 * 94704.  Attention:  Intel License Inquiry.
 */


#include "MultihopLightIntensity.h"

module MultiHopLightIntensityC @safe(){
  uses {
    // Interfaces for initialization:
    interface Timer<TMilli> as Timer;
    interface Boot;
    interface SplitControl as RadioControl;
    interface SplitControl as SerialControl;
    interface StdControl as RoutingControl;
    
    // Interfaces for communication, multihop and serial:
    interface Send;
    interface Receive;
    interface Receive as NavReceiver;
    interface AMSend as SerialSend;
    interface CollectionPacket;
    interface RootControl;

    interface Queue<message_t *> as UARTQueue;
    interface Pool<message_t> as UARTMessagePool;

    // Miscalleny:
    interface Leds;

    interface CtpInfo;
    interface LinkEstimator;
  }
}

implementation {
  task void uartSendTask();
  static void fatal_problem();
  static void report_problem();
  static void report_sent();
  static void report_received();

  uint8_t uartlen;
  message_t sendbuf;
  message_t uartbuf;
  bool sendbusy=FALSE, uartbusy=FALSE;

  /* Current local state - interval, version and accumulated readings */
  navigation_t local;


  /* When we head an Oscilloscope message, we check it's sample count. If
     it's ahead of ours, we "jump" forwards (set our count to the received
     count). However, we must then suppress our next count increment. This
     is a very simple form of "time" synchronization (for an abstract
     notion of time). */
  bool suppress_count_change;

  // 
  // On bootup, initialize radio and serial communications, and our
  // own state variables.
  //
  event void Boot.booted() {
    local.id = TOS_NODE_ID;
	local.parent = 100;

    call Timer.startPeriodic( 5000 );
    // Beginning our initialization phases:
    if (call RadioControl.start() != SUCCESS)
      fatal_problem();

    if (call RoutingControl.start() != SUCCESS)
      fatal_problem();
  }

  event void RadioControl.startDone(error_t error) {
    if (error != SUCCESS)
      fatal_problem();

    if (sizeof(local) > call Send.maxPayloadLength())
      fatal_problem();

    if (call SerialControl.start() != SUCCESS)
      fatal_problem();
  }

  event void SerialControl.startDone(error_t error) {
    if (error != SUCCESS)
      fatal_problem();

    // This is how to set yourself as a root to the collection layer:
    if (local.id == 0) {
    	call RootControl.setRoot();
    }
  }

  event void RadioControl.stopDone(error_t error) { }
  event void SerialControl.stopDone(error_t error) { }

  event message_t*
  Receive.receive(message_t* msg, void *payload, uint8_t len) {
    navigation_t* in = (navigation_t*)payload;
    navigation_t* out;
    if (uartbusy == FALSE) {
      out = (navigation_t*)call SerialSend.getPayload(&uartbuf, sizeof(navigation_t));

      	if (len != sizeof(navigation_t) || out == NULL) {
      		return msg;
      	}
      	else {
      		memcpy(out, in, sizeof(navigation_t));
      	}
      	uartlen = sizeof(navigation_t);
      	post uartSendTask();
    } else {
      // The UART is busy; queue up messages and service them when the
      // UART becomes free.
      message_t *newmsg = call UARTMessagePool.get();
      if (newmsg == NULL) {
        // drop the message on the floor if we run out of queue space.
        report_problem();
        return msg;
      }

      //Serial port busy, so enqueue.
      out = (navigation_t*)call SerialSend.getPayload(newmsg, sizeof(navigation_t));
      if (out == NULL) {
      	return msg;
      }
      memcpy(out, in, sizeof(navigation_t));

      if (call UARTQueue.enqueue(newmsg) != SUCCESS) {
        // drop the message on the floor and hang if we run out of
        // queue space without running out of queue space first (this
        // should not occur).
        call UARTMessagePool.put(newmsg);
        fatal_problem();
        return msg;
      }
    }

    return msg;
  }

  task void uartSendTask() {
    if (call SerialSend.send(0xffff, &uartbuf, uartlen) != SUCCESS) {
      report_problem();
    } else {
      uartbusy = TRUE;
    }
  }

  event void SerialSend.sendDone(message_t *msg, error_t error) {
    uartbusy = FALSE;
    if (call UARTQueue.empty() == FALSE) {
      // We just finished a UART send, and the uart queue is
      // non-empty.  Let's start a new one.
      message_t *queuemsg = call UARTQueue.dequeue();
      if (queuemsg == NULL) {
        fatal_problem();
        return;
      }
      memcpy(&uartbuf, queuemsg, sizeof(message_t));
      if (call UARTMessagePool.put(queuemsg) != SUCCESS) {
        fatal_problem();
        return;
      }
      post uartSendTask();
    }
  }

  /* At each sample period:
     - if local sample buffer is full, send accumulated samples
     - read next sample
  */

  am_addr_t parent = 0;

  event void Send.sendDone(message_t* msg, error_t error) {
    if (error == SUCCESS)
      report_sent();
    else
      report_problem();

    sendbusy = FALSE;
  }

  event void Timer.fired()
  {
    if (!sendbusy) {
      navigation_t *o = (navigation_t *)call Send.getPayload(&sendbuf, sizeof(navigation_t));

      if (o == NULL) {
        fatal_problem();
        return;
      }

      memcpy(o, &local, sizeof(local));
      if (call Send.send(&sendbuf, sizeof(local)) == SUCCESS)
        sendbusy = TRUE;
      else
        report_problem();
    }
  }

  event message_t* NavReceiver.receive(message_t* msg, void* payload, uint8_t len) {
	report_received();
    if (!sendbusy) {
        navigation_t *o = (navigation_t *)call Send.getPayload(&sendbuf, sizeof(navigation_t));

        if (o == NULL) {
          fatal_problem();
          return msg;
        }

        if (call CtpInfo.getParent(&parent) == SUCCESS) {
          local.parent = parent;
        } else {
          //To indentify failure in get parent
          local.parent = 21;
        }

        memcpy(o, &local, sizeof(local));
        if (call Send.send(&sendbuf, sizeof(local)) == SUCCESS)
          sendbusy = TRUE;
        else
          report_problem();
    }

    /* Part 2 of cheap "time sync": increment our count if we didn't
       jump ahead. */
    if (!suppress_count_change)
      local.count++;
    suppress_count_change = FALSE;
    return msg;
  }

  event void LinkEstimator.evicted(am_addr_t addr){}


  // Use LEDs to report various status issues.
  static void fatal_problem() { 
    call Leds.led0On(); 
    call Leds.led2On();
  }

  static void report_problem() { call Leds.led0Toggle(); }
  static void report_sent() { call Leds.led1Toggle(); }
  static void report_received() { call Leds.led2Toggle(); }
}
