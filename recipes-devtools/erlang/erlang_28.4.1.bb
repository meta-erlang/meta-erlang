require erlang-${PV}-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Enable-x32-support-for-crypto-configure.patch \
            file://0002-Enable-the-correct-ifdef-branch-when-x32-is-enabled.patch \
            "

SRCREV = "c8fca7e34e4db5d977ccf292cc2490ddb05b5879"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/28:"
