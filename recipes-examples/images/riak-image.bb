SUMMARY = "A console-only image with more full-featured Linux system \
functionality installed with OpenRiak installed."

LICENSE = "MIT"

include common.inc
include testimage-common.inc

APPLICATION = "riak"
APPLICATION += "${@bb.utils.contains('IMAGE_CLASSES', 'testimage', 'curl', '', d)}"

inherit core-image

TEST_SUITES:qemuall += "riak"

QB_MEM ?= "-m 1024"
IMAGE_ROOTFS_EXTRA_SPACE = "256000"
