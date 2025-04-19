require erlang-28.0-rc3-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Enable-x32-support-for-crypto-configure.patch \
            file://0002-Enable-the-correct-ifdef-branch-when-x32-is-enabled.patch \
            file://0001-Add-missing-wx-dependencies.patch \
            "

SRCREV = "feae4e669818cb794a1755036c1b11f672634f8f"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/28:"
