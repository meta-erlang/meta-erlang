SUMMARY = "Erlang Style Reviewer"
DESCRIPTION = "Command-line interface for Elvis, the Erlang style reviewer."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=92ca8142cc86af13840e0d8e92d2c68c"

RECIPE_MAINTAINER = "João Henrique Ferreira de Freitas <joaohf@gmail.com>"

SRC_URI = "git://github.com/inaka/elvis.git;branch=master;protocol=https"

S = "${WORKDIR}/git"
SRCREV = "cbf27d6ab7621a773539ff4a1c434f33c8f3e535"
PR = "r0"

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

