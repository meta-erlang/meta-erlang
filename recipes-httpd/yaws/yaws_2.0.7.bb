SUMMARY = "This is Yaws, a webserver for dynamic content written in Erlang"

DESCRIPTION = "Yaws is a HTTP high perfomance 1.1 webserver particularly well suited for dynamic-content web applications. \
The main advantages of Yaws compared to other Web technologies are performance and elegance. \
The performance comes from the underlying Erlang system and its ability to handle concurrent processes in an efficent way. \
Its elegance comes from Erlang as well. Web applications don't have to be written in ugly ad hoc languages."

HOMEPAGE = "http://yaws.hyber.org/"

SECTION = "net"

DEPENDS = "erlang-native \
           ${@bb.utils.contains('DISTRO_FEATURES', 'pam', 'libpam', '', d)}"

RDEPENDS_${PN} = "erlang erlang-compiler erlang-crypto erlang-xmerl erlang-ssl erlang-public-key erlang-asn1"

LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://LICENSE;md5=5d697daa4658cdb1e2074fd1f1b4f2a4"

SRC_URI = "git://github.com/klacke/yaws;protocol=https \
           file://yaws.conf \
           file://yaws.init"

PV = "2.0.7"
PR = "r1"
SRCREV = "c5aa1e300105578f3c3c025b367f4d2725ae5b5d"

S = "${WORKDIR}/git"

inherit autotools-brokensep systemd

SYSTEMD_SERVICE_${PN} = "yaws.service"

export yawsdir = "${libdir}/yaws-${PV}"

EXTRA_OECONF = "\
                ${@bb.utils.contains('DISTRO_FEATURES', 'pam', '--enable-pam', '--disable-pam', d)}"

do_install_append() {
	# Install systemd unit files
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${S}/scripts/systemd/yaws.service ${D}${systemd_unitdir}/system
	sed -i -e 's,%bindir%,${bindir},g' \
		-e 's,%etcdir%,${sysconfdir},g' \
		${D}${systemd_unitdir}/system/yaws.service

	# Remove python from cgi example
	sed -i -e 's,#!/usr/bin/python,#!/bin/sh,g' \
		${D}/var/yaws/www/cgi-bin/foo.py

	# Fix host-user-contaminated
	chown -R root:root ${D}/var/yaws/www

	# Fix yaws script
	sed -i -e 's,^erl=.*$,erl=${libdir}/erlang/bin/erl,g' \
		-e 's,^run_erl=.*$,run_erl=${libdir}/erlang/bin/run_erl,g' \
		-e 's,^to_erl=.*$,to_erl=${libdir}/erlang/bin/to_erl,g' \
		${D}/${bindir}/yaws

	# Install yaws.conf from this recipe
	install ${WORKDIR}/yaws.conf ${D}/${sysconfdir}/yaws/yaws.conf
	
	# Install init.d
	if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
		install ${WORKDIR}/yaws.init ${D}/${sysconfdir}/init.d/yaws
	fi
}

CONFFILES_${PN} = "${sysconfdir}/yaws/yaws.conf"

ALLOW_EMPTY_${PN}-examples = "1"
DESCRIPTION_${PN}-examples = ""
RDEPENDS_${PN}-examples = "${PN}"
FILES_${PN}-examples = "${libdir}/yaws-*/examples /var/yaws/www/*"

FILES_${PN} += " ${libdir}/yaws-*"

PACKAGE_BEFORE_PN = "${PN}-examples"

