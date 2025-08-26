SUMMARY = "A console-only image with more full-featured Linux system \
functionality installed with RabbitMQ installed."

LICENSE = "MIT"

include common.inc
include testimage-common.inc

APPLICATION = "rabbitmq-server"

inherit core-image

TEST_SUITES:qemuall += "rabbitmq"

QB_MEM = "-m 1024"
IMAGE_ROOTFS_EXTRA_SPACE = "256000"
