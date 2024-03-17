SUMMARY = "Replacement for /sbin/init that launches an Erlang/OTP Release"
DESCRIPTION = "This is a replacement for /sbin/init that launches an Erlang/OTP release. It is intentionally minimalist as it expects Erlang/OTP to be in charge of application initialization and supervision. It can be thought of as a simple Erlang/OTP release start script with some basic system initialization."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9a690f6d9d9ecde5e7332edfb7183ba0"

RECIPE_MAINTAINER = "João Henrique Ferreira de Freitas <joaohf@gmail.com>"

S = "${WORKDIR}/git"
SRCREV = "77101dba98d44e88bf95b8801e203916424ca304"
PR = "r0"

SRC_URI = "git://github.com/nerves-project/erlinit;branch=main;protocol=https \
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

FILES:${PN} = "${base_sbindir} ${sysconfdir}"
FILES:${PN}-dbg += "/.debug"
