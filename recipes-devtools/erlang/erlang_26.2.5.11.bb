include erlang.inc
require erlang-${PV}-manifest.inc

PR = "r0"

SRC_URI += "file://0001-Fix-for-Werror-format-security.patch"
SRC_URI += "file://pr-gh-7952-Fix-build-on-Yocto.patch"
SRC_URI += "file://0001-erts-fix-incompatible-pointer-type.patch"

SRCREV = "9f6c4eb54823324d1e6f8cb95c15feb09f09044e"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/26:"