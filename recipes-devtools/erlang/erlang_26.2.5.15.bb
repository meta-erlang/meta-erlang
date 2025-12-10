require erlang-${PV}-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Fix-for-Werror-format-security.patch \
            file://pr-gh-7952-Fix-build-on-Yocto.patch \
            file://0001-Fix-incompatible-pointer-types.patch \
            file://0002-Fix-implicit-int.patch \
            file://0003-Fix-incompatible-pointer-type-ErlDrvPort.patch \
            file://0001-Add-missing-wx-dependencies.patch \
            file://0001-Use-autoconf-2.72.patch \
            file://0001-erts-fix-static-function-prototypes.patch \
            file://0001-wx-check-WXE_WEBVIEW-before-include-wx-webview.h.patch \
           "

SRCREV = "4d9b61940e8d4dd00059306983022e3114fdf245"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/26:"
