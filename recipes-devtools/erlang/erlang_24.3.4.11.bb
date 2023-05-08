include erlang.inc
include erlang-${PV}.inc
require erlang-${PV}-manifest.inc

PR = "r0"

SRC_URI += "file://0001-Add-pkg-config-support-for-erl_interface.patch"
SRC_URI += "file://0002-Add-pkg-config-support-for-erts.patch"
SRC_URI += "file://0001-Allow-use-of-autoconf-2.71.patch"
SRC_URI += "file://0001-Detect-libdlpi-only-when-host_os-is-solaris.patch"
SRC_URI += "file://0001-Enable-wx-cross-compilation.patch"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/24/${PV}:${THISDIR}/files/24:"

OTP_BUILD_CONFIGURE_OPTS = "update_configure --no-commit"

PACKAGECONFIG = "pkgconfig"
PACKAGECONFIG:class-native = "pkgconfig"
PACKAGECONFIG:class-nativesdk = "pkgconfig wx observer"
