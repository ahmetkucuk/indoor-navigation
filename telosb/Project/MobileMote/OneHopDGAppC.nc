// $Id: OneHopDGAppC.nc,v 1.6 2010-06-29 22:07:14 scipio Exp $

/**
 * Blink is a basic application that toggles a mote's LED periodically.
 * It does so by starting a Timer that fires every second. It uses the
 * OSKI TimerMilli service to achieve this goal.
 *
 * @author ahmetkucuk
 **/


#include "RadioSenseToLeds.h"

configuration OneHopDGAppC
{
}
implementation
{
  components MainC, OneHopDGC, LedsC;
  components new TimerMilliC() as Timer;


  components ActiveMessageC;
  components new AMSenderC(AM_NAVIGATION);
  components new AMReceiverC(AM_NAVIGATION);

  OneHopDGC -> MainC.Boot;

  OneHopDGC.Timer -> Timer;
  OneHopDGC.Leds -> LedsC;
  OneHopDGC.Packet -> AMSenderC;
  OneHopDGC.AMSend -> AMSenderC;
  OneHopDGC.RadioControl -> ActiveMessageC;
}

