require erlang-${PV}-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Enable-x32-support-for-crypto-configure.patch \
            file://0002-Enable-the-correct-ifdef-branch-when-x32-is-enabled.patch \
            file://0001-erts-fix-static-function-prototypes.patch \
            "

SRCREV = "d9454dbccbaaad4b8796095c8e653b71b066dfaf"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/28:"
