SUMMARY = "A console-only image with more full-featured Linux system \
functionality installed with Erlang-Red installed."

LICENSE = "MIT"

include common.inc
include testimage-common.inc

APPLICATION = "erlang-red"
# For testing and demonstration
APPLICATION += "erlang-red-testflows"

inherit core-image

TEST_SUITES:qemuall += "erlang_red"