SUMMARY = "A console-only image with more full-featured Linux system \
functionality installed with Erlang/OTP and Elixir installed."

LICENSE = "MIT"

include common.inc
include testimage-common.inc

APPLICATION = "\
    packagegroup-beam-erlang \
    packagegroup-beam-elixir \
    "

inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"

