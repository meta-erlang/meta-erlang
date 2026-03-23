require erlang-${PV}-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Enable-x32-support-for-crypto-configure.patch \
            file://0002-Enable-the-correct-ifdef-branch-when-x32-is-enabled.patch \
            file://0001-Add-missing-wx-dependencies.patch"

SRCREV = "4aa809ae4eb4a20ad89c23837376acf179877f39"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/27:"
