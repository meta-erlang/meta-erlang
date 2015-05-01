SUMMARY = "Replacement for /sbin/init that launches an Erlang/OTP Release"
DESCRIPTION = "This is a replacement for /sbin/init that launches an Erlang/OTP release. It is intentionally minimalist as it expects Erlang/OTP to be in charge of application initialization and supervision. It can be thought of as a simple Erlang/OTP release start script with some basic system initialization."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=944e951e4520da54eb89bafc017c06c2"

S = "${WORKDIR}/git"
SRCREV = "0a3b5c4c5b660b9b2abddeefd7cb4e8d7b1c15b3"
PV = "0.6.0-git${SRCPV}"
PR = "r0"

SRC_URI = "git://github.com/nerves-project/erlinit;branch=master \
    file://erlinit.config \
    "

RDEPENDS_${PN} += "erlang"

EXTRA_OEMAKE = "erlinit"

do_install() {
        install -d 0755 ${D}/${base_sbindir}
        install -d 0755 ${D}/${sysconfdir}
	install -m 0755 ${S}/erlinit ${D}/${base_sbindir}/init
        install -m 0644 ${WORKDIR}/erlinit.config ${D}/${sysconfdir}/erlinit.config
}

FILES_${PN} = "${base_sbindir} ${sysconfdir}"
FILES_${PN}-dbg += "/.debug"
