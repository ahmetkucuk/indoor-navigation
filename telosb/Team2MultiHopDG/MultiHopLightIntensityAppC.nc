/*
 * Copyright (c) 2006 Intel Corporation
 * All rights reserved.
 *
 * This file is distributed under the terms in the attached INTEL-LICENSE     
 * file. If you do not find these files, copies can be found by writing to
 * Intel Research Berkeley, 2150 Shattuck Avenue, Suite 1300, Berkeley, CA, 
 * 94704.  Attention:  Intel License Inquiry.
 */


configuration MultihopLightIntensityAppC { }
implementation {
  components MainC, MultiHopLightIntensityC, LedsC, new TimerMilliC(), new HamamatsuS1087ParC() as Sensor;

  //MainC.SoftwareInit -> Sensor;
  
  MultiHopLightIntensityC.Boot -> MainC;
  MultiHopLightIntensityC.Timer -> TimerMilliC;
  MultiHopLightIntensityC.Read -> Sensor;
  MultiHopLightIntensityC.Leds -> LedsC;

  //components ;

  //
  // Communication components.  These are documented in TEP 113:
  // Serial Communication, and TEP 119: Collection.
  //
  components CollectionC as Collector,  // Collection layer
    ActiveMessageC,                         // AM layer
    new CollectionSenderC(AM_OSCILLOSCOPE), // Sends multihop RF
    SerialActiveMessageC,                   // Serial messaging
    new SerialAMSenderC(AM_OSCILLOSCOPE);   // Sends to the serial port

  MultiHopLightIntensityC.RadioControl -> ActiveMessageC;
  MultiHopLightIntensityC.SerialControl -> SerialActiveMessageC;
  MultiHopLightIntensityC.RoutingControl -> Collector;

  MultiHopLightIntensityC.Send -> CollectionSenderC;
  MultiHopLightIntensityC.SerialSend -> SerialAMSenderC.AMSend;
  MultiHopLightIntensityC.Snoop -> Collector.Snoop[AM_OSCILLOSCOPE];
  MultiHopLightIntensityC.Receive -> Collector.Receive[AM_OSCILLOSCOPE];
  MultiHopLightIntensityC.RootControl -> Collector;

  components new PoolC(message_t, 10) as UARTMessagePoolP,
    new QueueC(message_t*, 10) as UARTQueueP;

  MultiHopLightIntensityC.UARTMessagePool -> UARTMessagePoolP;
  MultiHopLightIntensityC.UARTQueue -> UARTQueueP;
  
  components new PoolC(message_t, 20) as DebugMessagePool,
    new QueueC(message_t*, 20) as DebugSendQueue,
    new SerialAMSenderC(AM_CTP_DEBUG) as DebugSerialSender,
    UARTDebugSenderP as DebugSender;

  DebugSender.Boot -> MainC;
  DebugSender.UARTSend -> DebugSerialSender;
  DebugSender.MessagePool -> DebugMessagePool;
  DebugSender.SendQueue -> DebugSendQueue;
  Collector.CollectionDebug -> DebugSender;

}
