SUMMARY = "A release assembler for Erlang."
DESCRIPTION = "Relx assembles releases for an Erlang/OTP release. Given a release specification and a list of directories in which to search for OTP applications it will generate a release output. That output depends heavily on what plugins available and what options are defined, but usually it is simply a well configured release directory." 
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=2c2fa1d0e3e31bc54af95828d407a5c1"

S = "${WORKDIR}/git"
SRCREV = "1e15397a4924804f248facc18ccd07076baef7a4"
PV = "3.19.0-git${SRCPV}"
PR = "r0"

SRC_URI = "git://github.com/erlware/relx;branch=master"

DEPENDS += "erlang-native"

do_compile() {
    ./rebar3 escriptize
}

do_install() {
        install -d 0755 ${D}/${bindir}
	install -m 0755 ${S}/_build/default/bin/relx ${D}/${bindir}/relx
}

FILES_${PN} = "${bindir}"
FILES_${PN}-dbg += "/.debug"

BBCLASSEXTEND = "native nativesdk"

