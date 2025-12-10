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
            file://0001-erts-avoid-use-bool-keyword-when-compiling-with-lttn.patch \
            file://0001-erts-fix-static-function-prototypes.patch \
            file://0001-wx-check-WXE_WEBVIEW-before-include-wx-webview.h.patch \
           "

SRCREV = "52199ed7e79646b73bacc47c92967ce9970b2373"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/25:"
