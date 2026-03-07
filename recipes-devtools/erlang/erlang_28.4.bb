require erlang-${PV}-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Enable-x32-support-for-crypto-configure.patch \
            file://0002-Enable-the-correct-ifdef-branch-when-x32-is-enabled.patch \
            "

SRCREV = "927bcc220b923ba1fef574618e82a3b7a2a4471d"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/28:"
