SUMMARY = "A minimal phoenix server"
SECTION = "examples"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=62612d1c0b9e46d8296dd0097c07db91"

S = "${UNPACKDIR}/${BPN}"

SRCREV = "794cb26751ad5584eb9ee6182a1fa90a69bfe5e5"
PV = "0.1.0+git${SRCPV}"
SRC_URI = "git://github.com/meta-erlang/hello-world;branch=master;subpath=${BPN};protocol=https"

inherit mix-phoenix
