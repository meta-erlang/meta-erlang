include erlang.inc
include erlang-${PV}.inc
require erlang-${PV}-manifest.inc

PV = "27.0~rc1"
PR = "r0"

SRC_URI += "file://0001-Enable-x32-support-for-crypto-configure.patch \
            file://0002-Enable-the-correct-ifdef-branch-when-x32-is-enabled.patch"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/27:"
