include erlang.inc
include erlang-${PV}.inc
require erlang-${PV}-manifest.inc

PR = "r0"

SRC_URI += "file://0001-Fix-for-Werror-format-security.patch \
            file://pr-gh-7952-Fix-build-on-Yocto.patch \
            file://0001-Fix-incompatible-pointer-types.patch \
            file://0002-Fix-implicit-int.patch \
            file://0003-Fix-incompatible-pointer-type-ErlDrvPort.patch \
           "

FILESEXTRAPATHS:prepend := "${THISDIR}/files/26:"