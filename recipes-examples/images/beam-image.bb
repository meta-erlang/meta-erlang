SUMMARY = "A console-only image with more full-featured Linux system \
functionality installed with full (all modules) Erlang/OTP and Elixir installed."

LICENSE = "MIT"

include common.inc
include testimage-common.inc

APPLICATION = "\
    packagegroup-beam-erlang \
    packagegroup-beam-elixir \
    "

inherit core-image

TEST_SUITES:qemuall += "beam"

IMAGE_ROOTFS_EXTRA_SPACE = "64000"