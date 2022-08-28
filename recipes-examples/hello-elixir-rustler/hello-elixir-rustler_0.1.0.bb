SUMMARY = "Elixir rustler example"
SECTION = "examples"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=34f8c1142fd6208a8be89399cb521df9"

S = "${WORKDIR}/${BPN}"

SRCREV = "ff7ba3d7fe6c48b4a74bd94d6181e4d25f35631f"
PV = "0.1.0-git${SRCPV}"
SRC_URI = "git://github.com/meta-erlang/hello-world;branch=master;subpath=${BPN};protocol=https"

CARGO_SRC_DIR = "native/helloelixirrustler_rustlernif"

inherit mix-rustler
