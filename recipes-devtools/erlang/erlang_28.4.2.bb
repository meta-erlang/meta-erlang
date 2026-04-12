require erlang-${PV}-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Enable-x32-support-for-crypto-configure.patch \
            file://0002-Enable-the-correct-ifdef-branch-when-x32-is-enabled.patch \
            file://0001-Use-autoconf-2.73.patch \
            "

SRCREV = "002fa5032baa0f6a8c4587c81f60e3273775a4d1"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/28:"
