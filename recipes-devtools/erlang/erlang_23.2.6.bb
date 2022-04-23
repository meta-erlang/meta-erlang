include erlang.inc
include erlang-${PV}.inc
require erlang-${PV}-manifest.inc

PR = "r0"

SRC_URI += "file://0002-Add-pkg-config-support-for-erts.patch"

FILESEXTRAPATHS_prepend := "${THISDIR}/files/23:"
