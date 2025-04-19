include erlang.inc
require erlang-${PV}-manifest.inc

PR = "r0"

SRC_URI += "file://0001-Fix-for-Werror-format-security.patch"
SRC_URI += "file://pr-gh-7952-Fix-build-on-Yocto.patch"
SRC_URI += "file://0001-erts-fix-incompatible-pointer-type.patch"

SRCREV = "ae0052c7f891ce805e2b53493a5304e2ee008aeb"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/25:"