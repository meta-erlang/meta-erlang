include erlang.inc
require erlang-${PV}-manifest.inc

PR = "r0"

SRC_URI += "file://0001-Enable-x32-support-for-crypto-configure.patch \
            file://0002-Enable-the-correct-ifdef-branch-when-x32-is-enabled.patch"

SRCREV = "10e20b1dbe39b056fab430e50b08cb4f3696ae87"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/27:"
