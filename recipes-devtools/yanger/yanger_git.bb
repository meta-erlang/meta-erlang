SUMMARY = "Extensible YANG validator"
DESCRIPTION = "Extensible YANG validator."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b6e74f10aa07de2cf633fab938ca24ba"

SRC_URI = "git://github.com/mbj4668/yanger.git;branch=master;protocol=https;destsuffix=yanger"

S = "${WORKDIR}/yanger"
SRCREV = "7f200aa475cacc05cbdef798c78a1fbeddfd1363"
PV .= "+git${SRCPV}"

DEPENDS = "libxml2 erlang erlang-native"

inherit pkgconfig

do_compile() {
    cd ${S}
    oe_runmake
}

do_install() {
    install -d 0755 ${D}/${bindir}
    install -m 0755 ${S}/bin/yanger ${D}/${bindir}/yanger
}

FILES:${PN} = "${bindir}/yanger"

BBCLASSEXTEND = "native nativesdk"

