SUMMARY = "A small image with Livebook."

SUMMARY = "A console-only image with more full-featured Linux system \
functionality installed with Livebook installed."

LICENSE = "MIT"

include common.inc
include testimage-common.inc

APPLICATION = "livebook"
APPLICATION += "${@bb.utils.contains('IMAGE_CLASSES', 'testimage', 'curl', '', d)}"

inherit core-image

TEST_SUITES:qemuall += "livebook"

IMAGE_ROOTFS_EXTRA_SPACE = "51200"