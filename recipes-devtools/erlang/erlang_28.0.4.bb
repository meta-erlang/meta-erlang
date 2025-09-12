require erlang-${PV}-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Enable-x32-support-for-crypto-configure.patch \
            file://0002-Enable-the-correct-ifdef-branch-when-x32-is-enabled.patch \
            file://0001-erts-fix-static-function-prototypes.patch \
            "

SRCREV = "eec5ff40a2ae2c3b3c6d952709ba4f16a6769024"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/28:"
