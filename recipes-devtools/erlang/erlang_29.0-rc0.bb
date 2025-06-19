require erlang-29.0-rc0-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Enable-x32-support-for-crypto-configure.patch \
            file://0002-Enable-the-correct-ifdef-branch-when-x32-is-enabled.patch \
            file://0001-erts-fix-static-function-prototypes.patch \
            "

SRCREV = "f57443fd2cbc51183acb65c57365e887c212b3b2"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/28:"
