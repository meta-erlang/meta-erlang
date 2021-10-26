include erlang.inc
include erlang-${PV}.inc
require erlang-${PV}-manifest.inc

PR = "r0"

SRC_URI += "file://0001-Add-pkg-config-support-for-erl_interface.patch"
SRC_URI += "file://0002-Add-pkg-config-support-for-erts.patch"
SRC_URI += "file://0001-Allow-use-of-autoconf-2.71.patch"

FILESEXTRAPATHS_prepend := "${THISDIR}/files/24:"

OTP_BUILD_CONFIGURE_OPTS = "update_configure --no-commit"

PACKAGECONFIG = "pkgconfig"
