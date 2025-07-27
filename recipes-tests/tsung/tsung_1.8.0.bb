SUMMARY = "Tsung is a high-performance benchmark framework for various protocols including HTTP, XMPP, LDAP, etc"

DESCRIPTION = "Tsung is multi-protocol distributed load testing tool. \
It can be used to test the scalability and performances of IP based client/server applications \
(supported protocols: HTTP, WebDAV, SOAP, PostgreSQL, MySQL, LDAP, MQTT, AMQP and Jabber/XMPP)."

HOMEPAGE = "http://tsung.erlang-projects.org/"

SECTION = "test"

DEPENDS = "erlang-native"

RDEPENDS:${PN} = "erlang erlang-compiler erlang-crypto erlang-xmerl erlang-ssl erlang-public-key erlang-asn1 erlang-inets erlang-os-mon"

LICENSE = "GPL-2.0-only"

LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/processone/tsung;protocol=https;branch=master \
          file://0001-Disable-crosscompile-before-checking-erlang-native-e.patch \
          file://0002-Use-sh-instead-of-bash.patch \
          file://0001-Remove-ERL_COMPILER_OPTIONS-variable.patch"

SRCREV = "8f0bddadca6baa05fd1935080e24b312bde053b5"
PR = "r0"

inherit autotools-brokensep

export ERL_COMPILER_OPTIONS = "deterministic"

do_install:append() {
	# Fix tsung scripts
	sed -i -e 's,^ERL=.*$,ERL=${libdir}/erlang/bin/erl,g' \
		${D}/${bindir}/tsung
	sed -i -e 's,^ERL=.*$,ERL=${libdir}/erlang/bin/erl,g' \
		${D}/${bindir}/tsung-recorder
}

ALLOW_EMPTY:${PN}-tools = "1"
DESCRIPTION:${PN}-tools = ""
RDEPENDS:${PN}-tools = "${PN} perl python3"
FILES:${PN}-tools = "${bindir}/tsplot ${bindir/tsung-recorder \
    ${libdir}/tsung/bin/*.pl ${libdir}/tsung/tsung_plotter \
    ${datadir}/tsung/tsung_plotter"

FILES:${PN}-src += "${libdir}/tsung/tsung-*/src ${libdir}/tsung/tsung-*/include \
    ${libdir}/tsung/tsung_recorder-*/src ${libdir}/tsung/tsung_recorder-*/include \
    ${libdir}/tsung/tsung_controller-*/src ${libdir}/tsung/tsung_controller-*/include"

PACKAGE_BEFORE_PN = "${PN}-tools"
