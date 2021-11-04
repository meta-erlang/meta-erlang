include erlang.inc
include erlang-${PV}.inc
require erlang-${PV}-manifest.inc

PR = "r0"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/21:"

OTP_BUILD_CONFIGURE_OPTS = "skip"

SRC_URI += "file://0001-Update-configure-scripts.patch"
SRC_URI += "file://0002-Remove-configure-output-from-gitignore.patch"