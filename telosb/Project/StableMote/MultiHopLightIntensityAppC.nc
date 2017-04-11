/*
 * Copyright (c) 2006 Intel Corporation
 * All rights reserved.
 *
 * This file is distributed under the terms in the attached INTEL-LICENSE     
 * file. If you do not find these files, copies can be found by writing to
 * Intel Research Berkeley, 2150 Shattuck Avenue, Suite 1300, Berkeley, CA, 
 * 94704.  Attention:  Intel License Inquiry.
 */


configuration MultiHopLightIntensityAppC { }
implementation {
  components MainC, MultiHopLightIntensityC, LedsC;

  //MainC.SoftwareInit -> Sensor;
  
  MultiHopLightIntensityC.Boot -> MainC;
  MultiHopLightIntensityC.Leds -> LedsC;

  //components ;

  //
  // Communication components.  These are documented in TEP 113:
  // Serial Communication, and TEP 119: Collection.
  //
  components CollectionC as Collector,  // Collection layer
    ActiveMessageC,                         // AM layer
    new CollectionSenderC(AM_NAVIGATION_CTP), // Sends multihop RF
    SerialActiveMessageC,                   // Serial messaging
    new SerialAMSenderC(AM_NAVIGATION_CTP);   // Sends to the serial port
  
  components CtpP as Ctp;

  MultiHopLightIntensityC.RadioControl -> ActiveMessageC;
  MultiHopLightIntensityC.SerialControl -> SerialActiveMessageC;
  MultiHopLightIntensityC.RoutingControl -> Collector;

  MultiHopLightIntensityC.Send -> CollectionSenderC;
  MultiHopLightIntensityC.SerialSend -> SerialAMSenderC.AMSend;
  MultiHopLightIntensityC.Receive -> Collector.Receive[AM_NAVIGATION_CTP];
  MultiHopLightIntensityC.RootControl -> Collector;

  components new PoolC(message_t, 10) as UARTMessagePoolP,
    new QueueC(message_t*, 10) as UARTQueueP;

  MultiHopLightIntensityC.UARTMessagePool -> UARTMessagePoolP;
  MultiHopLightIntensityC.UARTQueue -> UARTQueueP;

  MultiHopLightIntensityC.CtpInfo -> Ctp;
  MultiHopLightIntensityC.LinkEstimator -> Ctp;

  components new AMReceiverC(AM_NAVIGATION);
  MultiHopLightIntensityC.NavReceiver -> AMReceiverC;


  components new TimerMilliC() as Timer;
  MultiHopLightIntensityC.Timer -> Timer;

}
