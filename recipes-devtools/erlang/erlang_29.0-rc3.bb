require erlang-29.0-rc3-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Enable-x32-support-for-crypto-configure.patch \
            file://0002-Enable-the-correct-ifdef-branch-when-x32-is-enabled.patch \
            file://0001-erts-Fix-tty_tinfo_make_map-call.patch \
            file://0001-Skip-odbc-build-when-without-odbc-is-requested-by-us.patch \
            file://0001-Use-autoconf-2.73.patch \
            "

SRCREV = "7721ab7462a426c107964b0f1e47207475e085e3"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/29:"
