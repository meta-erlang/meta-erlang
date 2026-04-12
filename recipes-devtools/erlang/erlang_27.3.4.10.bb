require erlang-${PV}-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Enable-x32-support-for-crypto-configure.patch \
            file://0002-Enable-the-correct-ifdef-branch-when-x32-is-enabled.patch \
            file://0001-Add-missing-wx-dependencies.patch"

SRCREV = "0aa261a7765c9698aabc7f7c5be878a8850a9c5f"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/27:"
