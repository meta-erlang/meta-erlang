SUMMARY = "A small image with Livebook."

SUMMARY = "A console-only image with more full-featured Linux system \
functionality installed with Livebook installed."

LICENSE = "MIT"

include common.inc
include testimage-common.inc

APPLICATION = "livebook"

inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"

IMAGE_ROOTFS_EXTRA_SPACE = "51200"