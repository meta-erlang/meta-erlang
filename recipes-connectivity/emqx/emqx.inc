SUMMARY = "EMQ X Broker - Scalable Distributed MQTT Message Broker for IoT in 5G Era"
DESCRIPTION = "EMQ X broker is a fully open source, highly scalable, highly available distributed MQTT messaging broker for IoT, M2M and Mobile applications that can handle tens of millions of concurrent clients."

HOMEPAGE = "https://www.emqx.io"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

S = "${WORKDIR}/git"

SRC_URI = "\
    git://github.com/emqx/emqx-rel;branch=master \
    file://Makefile.c_src.bcrypt  \
    file://rebar.config.bcrypt \
    file://emqx.patch \
    "

inherit rebar3