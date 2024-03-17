SUMMARY = "Extensible YANG validator"
DESCRIPTION = "Extensible YANG validator."
LICENSE = "Apache-2.0"
RECIPE_MAINTAINER = "João Henrique Ferreira de Freitas <joaohf@gmail.com>"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b6e74f10aa07de2cf633fab938ca24ba"

SRC_URI = "git://github.com/mbj4668/yanger.git;branch=master;protocol=https;destsuffix=yanger \
    file://environment.d-yanger.sh \
    file://0001-Add-install-rule.patch \
    file://0001-Use-pkg-config-to-get-erl_erts.patch \
    file://yanger.sh"

S = "${WORKDIR}/yanger"
SRCREV = "019155d70a5b89a6a22a0985ca5a445412451892"
PV .= "+git${SRCPV}"

DEPENDS = "libxml2 erlang erlang-native"

inherit pkgconfig

#export ERL_COMPILER_OPTIONS="deterministic"

do_compile() {
    cd ${S}
    oe_runmake
}

do_install() {
    oe_runmake install DESTDIR=${D} PREFIX=${libdir}
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/yanger.sh ${D}${bindir}/yanger

    # install yanger wrapper to find the yanger installation path
    sed -i -e "s:%ROOTDIR%:${libdir}/yanger:g" ${D}${bindir}/yanger
}

do_install:append:class-nativesdk() {
    # install environment setup to load yang modules
    mkdir -p ${D}${SDKPATHNATIVE}/environment-setup.d 
    install -m 644 ${WORKDIR}/environment.d-yanger.sh ${D}${SDKPATHNATIVE}/environment-setup.d/yanger.sh
}

BBCLASSEXTEND = "native nativesdk"

FILES:${PN}:append:class-nativesdk = " ${SDKPATHNATIVE}/environment-setup.d/yanger.sh"
