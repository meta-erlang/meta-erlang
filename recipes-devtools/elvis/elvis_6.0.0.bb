SUMMARY = "Erlang Style Reviewer"
DESCRIPTION = "Command-line interface for Elvis, the Erlang style reviewer."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6af349f36434aa6e14afc0f203e8ab4"

RECIPE_MAINTAINER = "João Henrique Ferreira de Freitas <joaohf@gmail.com>"

SRC_URI = "git://github.com/inaka/elvis.git;branch=master;protocol=https;tag=${PV}"

SRCREV = "c4ca8fa918c9096c6e4d5d995ed03d86c6a3f0bb"

inherit rebar3

REBAR3_PROFILE = "release"

do_compile() {
    cd ${S}
    rebar3 as ${REBAR3_PROFILE} escriptize
}

do_install() {
    install -d 0755 ${D}/${bindir}
    install -m 0755 ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/bin/elvis ${D}/${bindir}/elvis
}

FILES:${PN} = "${bindir}/elvis"
FILES:${PN}-dbg += "/.debug"

BBCLASSEXTEND = "native nativesdk"

