SUMMARY = "A minimal phoenix server"
SECTION = "examples"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=62612d1c0b9e46d8296dd0097c07db91"

S = "${WORKDIR}/${BPN}"

SRCREV = "0b925bb4ab0013b2341068effcf8af5fa1d5d979"
PV = "0.1.0-git${SRCPV}"
SRC_URI = "git://github.com/meta-erlang/hello-world;branch=master;subpath=${BPN}"

inherit mix