require erlang-29.0-rc1-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Enable-x32-support-for-crypto-configure.patch \
            file://0002-Enable-the-correct-ifdef-branch-when-x32-is-enabled.patch \
            file://10736.patch \
            "

SRCREV = "04f1ebb18053d48f18e2a52e6ebe09b5f3d25580"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/28:${THISDIR}/files/29:"
