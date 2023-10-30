SUMMARY = "Collects and displays various information about AtomVM and the environment in which it is running."
SECTION = "examples"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=745e8b23501916820b8a509f8e3ba3d4"

ATOMVM_EXAMPLE = "erlang/system_info"

S = "${WORKDIR}/system_info"

SRCREV = "8e54aaf475a74b59a20f914e575202b1810a7954"
PV = "0.1.0+git${SRCPV}"
SRC_URI = "git://github.com/atomvm/atomvm_examples;branch=master;subpath=${ATOMVM_EXAMPLE};protocol=https"

inherit atomvm
