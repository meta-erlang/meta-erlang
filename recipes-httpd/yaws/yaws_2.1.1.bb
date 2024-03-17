SUMMARY = "This is Yaws, a webserver for dynamic content written in Erlang"

DESCRIPTION = "Yaws is a HTTP high perfomance 1.1 webserver particularly well suited for dynamic-content web applications. \
The main advantages of Yaws compared to other Web technologies are performance and elegance. \
The performance comes from the underlying Erlang system and its ability to handle concurrent processes in an efficent way. \
Its elegance comes from Erlang as well. Web applications don't have to be written in ugly ad hoc languages."

HOMEPAGE = "http://yaws.hyber.org/"

SECTION = "net"

RECIPE_MAINTAINER = "João Henrique Ferreira de Freitas <joaohf@gmail.com>"

DEPENDS = "erlang-native"

RDEPENDS:${PN} = "erlang erlang-compiler erlang-crypto erlang-xmerl erlang-ssl erlang-public-key erlang-asn1"

LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://LICENSE;md5=5d697daa4658cdb1e2074fd1f1b4f2a4"

SRC_URI = "git://github.com/klacke/yaws;protocol=https;branch=master \
           file://yaws.conf \
           file://yaws.init \
           file://0001-Fix-libpam-header-include.patch"

PV = "2.1.1+git${SRCPV}"
PR = "r0"
SRCREV = "8f633eed10f2862ff83a851925f2525cbd5a00c2"

S = "${WORKDIR}/git"

inherit autotools-brokensep systemd

SYSTEMD_SERVICE:${PN} = "yaws.service"

export yawsdir = "${libdir}/yaws-${PV}"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'pam', d)}"

PACKAGECONFIG[pam] = "--enable-pam,--disable-pam,libpam"

# Disable deterministic builds due some bugs when calling make install
#export YAWS_DETERMINISTIC_BUILD = "true"
EXTRA_OECONF:append = " --enable-deterministic-build --with-source-date-epoch=1683409604"

EXTRA_OEMAKE = "WARNINGS_AS_ERRORS="

PARALLEL_MAKEINST = ""

do_install:append() {
	# Install systemd unit files
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${S}/scripts/systemd/yaws.service ${D}${systemd_unitdir}/system
	sed -i -e 's,%bindir%,${bindir},g' \
		-e 's,%etcdir%,${sysconfdir},g' \
		${D}${systemd_unitdir}/system/yaws.service

	# Remove python from cgi example
	#sed -i -e 's,#!/usr/bin/python,#!/bin/sh,g' \
	#	${D}/var/yaws/www/cgi-bin/foo.py

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

        # Remove any volatile files
        rm -rf ${D}/var/log

        # Remove code examples
        rm -rf ${D}/var/yaws/www/*
        rm -rf ${D}/${libdir}/yaws-*/examples
}

CONFFILES:${PN} = "${sysconfdir}/yaws/yaws.conf"

FILES:${PN} += " ${libdir}/yaws-*"
