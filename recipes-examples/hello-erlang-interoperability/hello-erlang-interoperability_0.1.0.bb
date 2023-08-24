SUMMARY = "A minimal Erlang application with Erlang port driver written in C"
SECTION = "examples"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4072a65404e353b02008ca87f23e6002"

S = "${WORKDIR}/${BPN}"

SRCREV = "bddabf5e67b6acc3ce6be91319dd180a74101183"
PV = "0.1.0+git${SRCPV}"
SRC_URI = "git://github.com/meta-erlang/hello-world;branch=master;subpath=${BPN};protocol=https"

inherit rebar3

REBAR3_PROFILE = "prod"
REBAR3_RELEASE = "${REBAR3_RELEASE_NAME}-0.1.0"
