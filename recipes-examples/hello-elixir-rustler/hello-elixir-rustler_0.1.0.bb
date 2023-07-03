SUMMARY = "Elixir rustler example"
SECTION = "examples"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=34f8c1142fd6208a8be89399cb521df9"

S = "${WORKDIR}/${BPN}"

SRCREV = "83738bbd45b8b3b1602e850385fdf1cc76175352"
PV = "0.1.0-git${SRCPV}"
SRC_URI = "git://github.com/meta-erlang/hello-world;branch=master;subpath=${BPN};protocol=https"

inherit mix-rustler
