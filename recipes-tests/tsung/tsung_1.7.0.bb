SUMMARY = "Tsung is a high-performance benchmark framework for various protocols including HTTP, XMPP, LDAP, etc"

DESCRIPTION = "Tsung is multi-protocol distributed load testing tool. \
It can be used to test the scalability and performances of IP based client/server applications \
(supported protocols: HTTP, WebDAV, SOAP, PostgreSQL, MySQL, LDAP, MQTT, AMQP and Jabber/XMPP)."

HOMEPAGE = "http://tsung.erlang-projects.org/"

SECTION = "test"

DEPENDS = "erlang-native"

RDEPENDS_${PN} = "erlang erlang-compiler erlang-crypto erlang-xmerl erlang-ssl erlang-public-key erlang-asn1 erlang-inets erlang-os-mon"

LICENSE = "GPL-2.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/processone/tsung;protocol=https \
          file://0001-Disable-crosscompile-before-checking-erlang-native-e.patch \
          file://0002-Use-sh-instead-of-bash.patch"

SRCREV = "cf6195d0ddad0b445947258febc2681e741a9764"
PR = "r1"

S = "${WORKDIR}/git"

inherit autotools-brokensep

do_install_append() {
	# Fix tsung scripts
	sed -i -e 's,^ERL=.*$,ERL=${libdir}/erlang/bin/erl,g' \
		${D}/${bindir}/tsung
	sed -i -e 's,^ERL=.*$,ERL=${libdir}/erlang/bin/erl,g' \
		${D}/${bindir}/tsung-recorder
}

#ALLOW_EMPTY_${PN}-examples = "1"
#DESCRIPTION_${PN}-examples = ""
#RDEPENDS_${PN}-examples = "${PN}"
#FILES_${PN}-examples = "${libdir}/yaws-*/examples /var/yaws/www/*"

#FILES_${PN} += " ${libdir}/yaws-*"


ALLOW_EMPTY_${PN}-tools = "1"
DESCRIPTION_${PN}-tools = ""
RDEPENDS_${PN}-tools = "${PN} perl python3"
FILES_${PN}-tools = "${bindir}/tsplot ${bindir/tsung-recorder \
    ${libdir}/tsung/bin/*.pl ${libdir}/tsung/tsung_plotter \
    ${datadir}/tsung/tsung_plotter"

FILES_${PN}-src += "${libdir}/tsung/tsung-*/src ${libdir}/tsung/tsung-*/include \
    ${libdir}/tsung/tsung_recorder-*/src ${libdir}/tsung/tsung_recorder-*/include \
    ${libdir}/tsung/tsung_controller-*/src ${libdir}/tsung/tsung_controller-*/include"

PACKAGE_BEFORE_PN = "${PN}-tools"
