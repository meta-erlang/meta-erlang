#!/bin/sh

if [ -z "$YANGER_ROOTDIR" ]
then
    ROOTDIR="%ROOTDIR%"
else
    ROOTDIR="$YANGER_ROOTDIR"
fi
BINDIR=$ROOTDIR/bin
PROGNAME=`echo $0 | sed 's/.*\///'`
exec "$BINDIR/yanger" ${1+"$@"}
