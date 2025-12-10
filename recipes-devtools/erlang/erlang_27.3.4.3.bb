require erlang-${PV}-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Enable-x32-support-for-crypto-configure.patch \
            file://0002-Enable-the-correct-ifdef-branch-when-x32-is-enabled.patch \
            file://0001-Fix-incompatible-pointer-types.patch \
            file://0002-Fix-implicit-int.patch \
            file://0003-Fix-incompatible-pointer-type-ErlDrvPort.patch \
            file://0001-Add-missing-wx-dependencies.patch \
            file://0001-erts-fix-static-function-prototypes.patch \
            file://0001-wx-check-WXE_WEBVIEW-before-include-wx-webview.h.patch \
           "

SRCREV = "940ec0f6f0370ecf5cd93cae31fd91f4651ddca6"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/27:"
