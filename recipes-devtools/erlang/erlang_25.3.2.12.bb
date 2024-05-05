include erlang.inc
include erlang-${PV}.inc
require erlang-${PV}-manifest.inc

PR = "r0"

SRC_URI += "file://0001-Fix-for-Werror-format-security.patch"
SRC_URI += "file://pr-gh-7952-Fix-build-on-Yocto.patch"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/25/${PV}:${THISDIR}/files/25:"