#!/bin/bash
rm -r build/telosb/
mkdir build/telosb/
ncc -o build/telosb/main.exe -Os -O -mmpy=none -Wall -Wshadow -DDEF_TOS_AM_GROUP=0x7d -Wnesc-all -target=telosb -fnesc-cfile=build/telosb/app.c -board=	OneHopDGAppC.nc -lm
msp430-objcopy --output-target=ihex build/telosb/main.exe build/telosb/main.ihex
