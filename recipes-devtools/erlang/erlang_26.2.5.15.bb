require erlang-${PV}-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Fix-for-Werror-format-security.patch"
SRC_URI += "file://pr-gh-7952-Fix-build-on-Yocto.patch"
SRC_URI += "file://0001-Add-missing-wx-dependencies.patch"

SRCREV = "4d9b61940e8d4dd00059306983022e3114fdf245"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/26:"
