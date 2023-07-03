SUMMARY = "A minimal phoenix server"
SECTION = "examples"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=62612d1c0b9e46d8296dd0097c07db91"

S = "${WORKDIR}/${BPN}"

SRCREV = "b78ae4017ac62652b08f6712b8038530ad72293f"
PV = "0.1.0-git${SRCPV}"
SRC_URI = "git://github.com/meta-erlang/hello-world;branch=master;subpath=${BPN};protocol=https"

inherit mix-phoenix
