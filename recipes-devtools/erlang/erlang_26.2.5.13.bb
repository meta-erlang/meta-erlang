require erlang-${PV}-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Fix-for-Werror-format-security.patch \
            file://pr-gh-7952-Fix-build-on-Yocto.patch \
            file://0001-Fix-incompatible-pointer-types.patch \
            file://0002-Fix-implicit-int.patch \
            file://0003-Fix-incompatible-pointer-type-ErlDrvPort.patch \
            file://0001-Add-missing-wx-dependencies.patch \
            file://0001-erts-fix-incompatible-pointer-type.patch \
            file://0001-Use-autoconf-2.72.patch \
            file://0001-erts-fix-static-function-prototypes.patch \
           "

SRCREV = "0ac548b57c0491196c27e39518b5f6acf9326c1e"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/26:"