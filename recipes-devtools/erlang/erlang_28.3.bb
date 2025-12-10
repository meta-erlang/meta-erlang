require erlang-${PV}-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Enable-x32-support-for-crypto-configure.patch \
            file://0002-Enable-the-correct-ifdef-branch-when-x32-is-enabled.patch \
            "

SRCREV = "ab489c0b260c637a9487f8753909ea94dacbbec2"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/28:"
