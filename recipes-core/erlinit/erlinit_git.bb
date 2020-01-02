SUMMARY = "Replacement for /sbin/init that launches an Erlang/OTP Release"
DESCRIPTION = "This is a replacement for /sbin/init that launches an Erlang/OTP release. It is intentionally minimalist as it expects Erlang/OTP to be in charge of application initialization and supervision. It can be thought of as a simple Erlang/OTP release start script with some basic system initialization."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9a690f6d9d9ecde5e7332edfb7183ba0"

S = "${WORKDIR}/git"
SRCREV = "53b0a4dfc5aab5f883652527e29e2db39ac546ae"
PV = "1.6.0-git${SRCPV}"
PR = "r0"

SRC_URI = "git://github.com/nerves-project/erlinit;branch=master \
    file://erlinit.config \
    "

TARGET_CC_ARCH += "${LDFLAGS}"

EXTRA_OEMAKE = "erlinit"

do_install() {
        install -d 0755 ${D}/${base_sbindir}
        install -d 0755 ${D}/${sysconfdir}
	install -m 0755 ${S}/erlinit ${D}/${base_sbindir}/init
        install -m 0644 ${WORKDIR}/erlinit.config ${D}/${sysconfdir}/erlinit.config
}

FILES_${PN} = "${base_sbindir} ${sysconfdir}"
FILES_${PN}-dbg += "/.debug"
