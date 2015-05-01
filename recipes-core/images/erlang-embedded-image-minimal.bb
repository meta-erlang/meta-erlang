SUMMARY = "A small image just capable of allowing a device to boot with Erlang."

IMAGE_INSTALL = "\
    packagegroup-erlang-embedded \
    "

IMAGE_LINGUAS = " "
USE_NLS="no"

LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"

