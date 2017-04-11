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

#ifndef MULTIHOP_NAVIGATION_H
#define MULTIHOP_NAVIGATION_H

enum {
  /* Default sampling period. */
  AM_NAVIGATION_CTP = 0x93,
  AM_NAVIGATION = 0x94
};

typedef nx_struct navigation {
  nx_uint16_t id; /* Mote id of sending mote. */
  nx_uint16_t count; /* The readings are samples count * NREADINGS onwards */
  nx_uint16_t mobile_mote_id;
  nx_uint16_t parent; /* ParentMote id of sending mote. */
} navigation_t;

#endif
