
#ifndef RADIO_SENSE_TO_LEDS_H
#define RADIO_SENSE_TO_LEDS_H

typedef nx_struct mobile_mote_msg {
  nx_uint16_t error;
  nx_uint16_t id;
} mobile_mote_msg;

enum {
  AM_NAVIGATION = 0x94
};

#endif
