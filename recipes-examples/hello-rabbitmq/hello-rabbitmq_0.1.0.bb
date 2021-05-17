SUMMARY = "A minimal client that sends heart beats to a rabbitmq server"
SECTION = "examples"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=45e23f2f8aef013e4b28c43fc2c406b4"

PV = "0.1.0-git${SRCPV}"
SRCREV = "af95837946b1ac9591d46ee270be600d3be5923d"
SRC_URI = "git://github.com/meta-erlang/hello-world;branch=master;subpath=${BPN}"

inherit rebar3

REBAR3_PROFILE = "prod"