include erlang.inc
include erlang-${PV}.inc
require erlang-${PV}-manifest.inc

PR = "r0"

SRC_URI += "file://0001-Add-pkg-config-support-for-erl_interface.patch"
SRC_URI += "file://0002-Add-pkg-config-support-for-erts.patch"
SRC_URI += "file://0003-Update-configure-scripts.patch"

FILESEXTRAPATHS_prepend := "${THISDIR}/files/22:"

PACKAGECONFIG = "pkgconfig"