require erlang-29.0-rc2-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Enable-x32-support-for-crypto-configure.patch \
            file://0002-Enable-the-correct-ifdef-branch-when-x32-is-enabled.patch \
            file://0001-erts-Fix-tty_tinfo_make_map-function-arguments.patch \
            "

SRCREV = "92322002e7287640f7615fed89a677505b44bfdd"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/28:${THISDIR}/files/29:"
