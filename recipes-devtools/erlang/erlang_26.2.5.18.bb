require erlang-${PV}-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Fix-for-Werror-format-security.patch"
SRC_URI += "file://pr-gh-7952-Fix-build-on-Yocto.patch"
SRC_URI += "file://0001-Add-missing-wx-dependencies.patch"

SRCREV = "8c304e09e4d9301f56f6d7b2c4c9f60b4f9d9425"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/26:"
