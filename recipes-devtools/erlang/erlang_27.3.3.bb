require erlang-${PV}-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Enable-x32-support-for-crypto-configure.patch \
            file://0002-Enable-the-correct-ifdef-branch-when-x32-is-enabled.patch \
            file://0001-Fix-incompatible-pointer-types.patch \
            file://0002-Fix-implicit-int.patch \
            file://0003-Fix-incompatible-pointer-type-ErlDrvPort.patch \
            file://0001-Add-missing-wx-dependencies.patch \
           "

SRCREV = "10e20b1dbe39b056fab430e50b08cb4f3696ae87"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/27:"
