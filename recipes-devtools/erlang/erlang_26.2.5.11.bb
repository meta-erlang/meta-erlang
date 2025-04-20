include erlang.inc
require erlang-${PV}-manifest.inc

PR = "r0"

SRC_URI += "file://0001-erts-add-pkg-config-support-for-erts.patch"
SRC_URI += "file://0002-erl_interface-Add-pkg-config-support-for-erl_interfa.patch"
SRC_URI += "file://0001-wx-Enable-wx-cross-compilation.patch"
SRC_URI += "file://otp-0002-Remove-rpath.patch"
SRC_URI += "file://0001-erts-fix-incompatible-pointer-type.patch"

SRCREV = "9f6c4eb54823324d1e6f8cb95c15feb09f09044e"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/26:"

OTP_BUILD_CONFIGURE_OPTS = "update_configure --no-commit"

EXTRA_OECONF:class-target += " --with-ssl-rpath=no"

PACKAGECONFIG = "pkgconfig"
PACKAGECONFIG:class-native = "pkgconfig"
PACKAGECONFIG:class-nativesdk = "pkgconfig wx observer"
