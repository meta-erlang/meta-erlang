SUMMARY = "A very basic x11 image with more full-featured Linux system \
functionality installed with wings."

LICENSE = "MIT"

include common.inc
include testimage-common.inc

IMAGE_FEATURES += "x11-base"

APPLICATION = "wings"

inherit core-image

REQUIRED_DISTRO_FEATURES = "x11"

#TEST_SUITES:qemuall += "wings"

QB_MEM ?= "-m 1024"
IMAGE_ROOTFS_EXTRA_SPACE = "1048576"
