require erlang-${PV}-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Enable-x32-support-for-crypto-configure.patch \
            file://0002-Enable-the-correct-ifdef-branch-when-x32-is-enabled.patch \
            "

SRCREV = "b74eae92e1dee97d57a9ab3ea3c36e8b701a9ca6"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/28:"
