include erlang.inc
include erlang-${PV}.inc
require erlang-${PV}-manifest.inc

PR = "r1"

FILESEXTRAPATHS_prepend := "${THISDIR}/files/22.3:"

OTP_BUILD_CONFIGURE_OPTS = "update_configure --no-commit"