require ${PN}.inc

SRCREV = "df48c260e74c3e9058ff8681ce9f554e6fa0fe34"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/24:"

SRC_URI += "file://0001-Allow-use-of-autoconf-2.71.patch"
SRC_URI += "file://0001-Detect-libdlpi-only-when-host_os-is-solaris.patch"