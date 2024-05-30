SUMMARY = "Erlang Style Reviewer"
DESCRIPTION = "Command-line interface for Elvis, the Erlang style reviewer."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=75f5126d170bbca5f1ef6a84b032dca7"

RECIPE_MAINTAINER = "João Henrique Ferreira de Freitas <joaohf@gmail.com>"

SRC_URI = "git://github.com/inaka/elvis.git;branch=master;protocol=https"

S = "${WORKDIR}/git"
SRCREV = "88748c1bd384eba8b3c11932555c98cf46f4d2fd"

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

