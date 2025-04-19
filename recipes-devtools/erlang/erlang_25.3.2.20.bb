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
           "

SRCREV = "ae0052c7f891ce805e2b53493a5304e2ee008aeb"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/25:"