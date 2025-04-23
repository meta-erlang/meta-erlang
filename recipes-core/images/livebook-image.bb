SUMMARY = "A small image with Livebook."

IMAGE_INSTALL = "\
    livebook \
    "

IMAGE_LINGUAS = " "
USE_NLS = "no"

LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"

IMAGE_ROOTFS_EXTRA_SPACE = "51200"