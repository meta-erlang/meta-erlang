SUMMARY = "Tool to convert Erlang cover data files into Cobertura XML reports"
DESCRIPTION = "A simple tool to convert exported Erlang cover data sets into Cobertura XML reports. The report could be then feed to the Jenkins Cobertura plug-in."
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6d36c043a022f90fea8faa1a4cc9bd4b"

SRC_URI = "git://github.com/covertool/covertool.git;branch=master;protocol=https"

S = "${WORKDIR}/git"
SRCREV = "aabf751302d275275438c90a0684763d988ed31d"
PV = "2.0.3+git${SRCPV}"

DEPENDS += "rebar3-native"

REBAR3_PROFILE = "default"

do_compile() {
    rebar3 as ${REBAR3_PROFILE} escriptize
}

do_install() {
    install -d 0755 ${D}/${bindir}
    install -m 0755 ${S}/_build/${REBAR3_PROFILE}/bin/covertool ${D}/${bindir}/covertool
}

FILES_${PN} = "${bindir}/covertool"
FILES_${PN}-dbg += "/.debug"

BBCLASSEXTEND = "native nativesdk"
