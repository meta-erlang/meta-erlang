include erlang.inc
include erlang-28.0-rc0.inc
require erlang-28.0-rc0-manifest.inc

PR = "r0"

SRC_URI += "file://0001-Enable-x32-support-for-crypto-configure.patch \
            file://0002-Enable-the-correct-ifdef-branch-when-x32-is-enabled.patch"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/28:"
