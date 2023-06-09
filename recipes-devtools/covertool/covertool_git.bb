SUMMARY = "Tool to convert Erlang cover data files into Cobertura XML reports"
DESCRIPTION = "A simple tool to convert exported Erlang cover data sets into Cobertura XML reports. The report could be then feed to the Jenkins Cobertura plug-in."
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6d36c043a022f90fea8faa1a4cc9bd4b"

SRC_URI = "git://github.com/covertool/covertool.git;branch=master;protocol=https"

S = "${WORKDIR}/git"
SRCREV = "79872cc7021e3a57e97cab0fcf5d53975f4822b7"
PV = "2.0.5+git${SRCPV}"

inherit rebar3

REBAR3_PROFILE = "default"

do_compile() {
    cd ${S}
    rebar3 as ${REBAR3_PROFILE} escriptize
}

do_install() {
    install -d 0755 ${D}/${bindir}
    install -m 0755 ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/bin/covertool ${D}/${bindir}/covertool
}

FILES:${PN} = "${bindir}/covertool"
FILES:${PN}-dbg += "/.debug"

BBCLASSEXTEND = "native nativesdk"
