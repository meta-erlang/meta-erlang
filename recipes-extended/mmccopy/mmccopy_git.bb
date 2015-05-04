SUMMARY = "Convenient alternative to dd for writing images to SDCards" 
DESCRIPTION = "The mmccopy utility is an easier-to-use alternative to dd for writing images to SDCards and MMC memory."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=944e951e4520da54eb89bafc017c06c2"

S = "${WORKDIR}/git"
SRCREV = "781cfe68de4011f1c5591a337792f6dddf344fa6"
PV = "1.0.0-git${SRCPV}"
PR = "r0"

SRC_URI = "git://github.com/fhunleth/mmccopy;branch=master"

inherit autotools

BBCLASSEXTEND = "native nativesdk"

