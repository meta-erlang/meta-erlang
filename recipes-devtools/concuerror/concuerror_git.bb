SUMMARY = "Concuerror is a stateless model checking tool for Erlang programs"
DESCRIPTION = "Concuerror is a stateless model checking tool for Erlang programs. It can be used to systematically test programs for concurrency errors, detect and report errors that only occur on few, specific schedulings or verify their absence."
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e93694aa036c6db13d9595526cea51a7"

SRC_URI = "git://github.com/parapluu/Concuerror.git;branch=master;protocol=https"

S = "${WORKDIR}/git"
SRCREV = "cbc5200e0b43bcef54a84d19000c5b945a50be59"
PV = "0.21.0+git${SRCPV}"

inherit rebar3

do_compile() {
    cd ${S}
    make
}

do_install() {
    install -d 0755 ${D}/${bindir}
    install -m 0755 ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/bin/concuerror ${D}/${bindir}/concuerror
}

FILES:${PN} = "${bindir}/concuerror"
FILES:${PN}-dbg += "/.debug"

BBCLASSEXTEND = "native nativesdk"

