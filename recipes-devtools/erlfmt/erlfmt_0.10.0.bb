SUMMARY = "An automated code formatter for Erlang"
DESCRIPTION = "erlfmt is an opinionated Erlang code formatter. By automating the process of formatting the code, it frees your team up to focus on what the code does, instead of what it looks like."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dcbf253b3d6d09ae7e64cb34b4d0ec33"

SRC_URI = "git://github.com/WhatsApp/erlfmt.git;branch=main;protocol=https"

S = "${WORKDIR}/git"
SRCREV = "0d9907bbf8a4659529a2e0b96c5829f8f96e907f"
PV .= "+git${SRCPV}"

inherit rebar3

DEPENDS += "rebar3-native"

REBAR3_PROFILE = "release"

do_compile() {
    rebar3 as ${REBAR3_PROFILE} escriptize
}

do_install() {
    install -d 0755 ${D}/${bindir}
    install -m 0755 ${S}/_build/${REBAR3_PROFILE}/bin/erlfmt ${D}/${bindir}/erlfmt
}

FILES_${PN} = "${bindir}/erlfmt"
FILES_${PN}-dbg += "/.debug"

BBCLASSEXTEND = "native nativesdk"

