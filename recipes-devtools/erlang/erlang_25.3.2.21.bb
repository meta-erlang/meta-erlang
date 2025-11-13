require erlang-${PV}-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Fix-for-Werror-format-security.patch"
SRC_URI += "file://pr-gh-7952-Fix-build-on-Yocto.patch"
SRC_URI += "file://0001-Add-missing-wx-dependencies.patch"

SRCREV = "52199ed7e79646b73bacc47c92967ce9970b2373"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/25:"
