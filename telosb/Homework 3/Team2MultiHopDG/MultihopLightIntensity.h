/*
 * Copyright (c) 2006 Intel Corporation
 * All rights reserved.
 *
 * This file is distributed under the terms in the attached INTEL-LICENSE     
 * file. If you do not find these files, copies can be found by writing to
 * Intel Research Berkeley, 2150 Shattuck Avenue, Suite 1300, Berkeley, CA, 
 * 94704.  Attention:  Intel License Inquiry.
 */

/**
 * @author David Gay
 * @author Kyle Jamieson
 */

#ifndef MULTIHOP_INTENSITY_H
#define MULTIHOP_INTENSITY_H

enum {
  /* Number of readings per to be done before sending the values*/
  NREADINGS = 9,
  /* Default sampling period. */
  DEFAULT_INTERVAL = 500,
  AM_OSCILLOSCOPE = 0x93
};

typedef nx_struct lightintensity {
  nx_uint16_t version; /* Version of the interval. */
  nx_uint16_t interval; /* Samping period. */
  nx_uint16_t id; /* Mote id of sending mote. */
  nx_uint16_t count; /* The readings are samples count * NREADINGS onwards */
  nx_uint16_t readings[3];
  nx_uint16_t parent; /* ParentMote id of sending mote. */
  nx_uint16_t secret; /* Random Secret of sending mote. */
} lightintensity_t;

#endif
