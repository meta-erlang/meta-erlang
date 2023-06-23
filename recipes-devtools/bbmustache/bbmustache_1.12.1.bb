SUMMARY = "Binary pattern match Based Mustache template engine for Erlang/OTP"
DESCRIPTION = "Binary pattern match Based Mustache template engine for Erlang/OTP."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c8bd59ddfba7a079b8ea1531f0692a49"

SRC_URI = "git://github.com//soranoba/bbmustache.git;branch=master;protocol=https"

S = "${WORKDIR}/git"
SRCREV = "7631da97b0c4de54753257e126cb6f983d0c4b83"
PV .= "+git${SRCPV}"

inherit rebar3

REBAR3_PROFILE = "dev"

do_compile() {
    cd ${S}
    rebar3 as ${REBAR3_PROFILE} escriptize
}

do_install() {
    install -d 0755 ${D}/${bindir}
    install -m 0755 ${B}/${REBAR3_PROFILE}/bin/bbmustache ${D}/${bindir}/bbmustache
}

FILES:${PN} = "${bindir}/bbmustache"
FILES:${PN}-dbg += "/.debug"

BBCLASSEXTEND = "native nativesdk"

