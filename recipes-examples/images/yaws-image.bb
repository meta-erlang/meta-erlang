SUMMARY = "A console-only image with more full-featured Linux system \
functionality installed with yaws installed."

LICENSE = "MIT"

include common.inc
include testimage-common.inc

APPLICATION = "yaws"
APPLICATION += "${@bb.utils.contains('IMAGE_CLASSES', 'testimage', 'curl', '', d)}"

inherit core-image

TEST_SUITES:qemuall += "yaws"