require erlang-${PV}-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Fix-for-Werror-format-security.patch"
SRC_URI += "file://pr-gh-7952-Fix-build-on-Yocto.patch"
SRC_URI += "file://0001-Add-missing-wx-dependencies.patch"

SRCREV = "cdb375af6b456946b1ff3fc993d122018f20b031"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/26:"
