include erlang.inc
include erlang-${PV}.inc
require erlang-${PV}-manifest.inc

PR = "r0"

SRC_URI += "file://0001-erts-add-pkg-config-support-for-erts.patch"
SRC_URI += "file://0002-erl_interface-Add-pkg-config-support-for-erl_interfa.patch"
SRC_URI += "file://0001-wx-Enable-wx-cross-compilation.patch"
SRC_URI += "file://0001-Fix-reproducibility-issue.patch"
SRC_URI += "file://0001-Use-autoconf-2.72c.patch"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/27:"

OTP_BUILD_CONFIGURE_OPTS = "update_configure --no-commit"

EXTRA_OECONF:append:class-target = " --enable-deterministic-build"

PACKAGECONFIG = "pkgconfig"
PACKAGECONFIG:class-native = "pkgconfig"
PACKAGECONFIG:class-nativesdk = "pkgconfig wx observer"
