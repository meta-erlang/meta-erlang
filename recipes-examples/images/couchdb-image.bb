SUMMARY = "A console-only image with more full-featured Linux system \
functionality installed with CouchDB installed."

LICENSE = "MIT"

include common.inc
include testimage-common.inc

APPLICATION = "couchdb"
APPLICATION += "${@bb.utils.contains('IMAGE_CLASSES', 'testimage', 'curl', '', d)}"

inherit core-image

TEST_SUITES:qemuall += "couchdb"

QB_MEM ?= "-m 1024"
IMAGE_ROOTFS_EXTRA_SPACE = "256000"
