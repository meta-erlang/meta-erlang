include erlang.inc

inherit nativesdk

require erlang-${PV}-manifest.inc

DEPENDS = "erlang-native openssl ncurses"

RDEPENDS_${PN} = "nativesdk-ncurses nativesdk-erlang-modules"

PR = "r0"

do_configure() {

    rm -rf ${S}/lib/wx

    TARGET=${HOST_SYS} \
    ac_cv_prog_javac_ver_1_2=no \
    ac_cv_prog_javac_ver_1_5=no \
    erl_xcomp_sysroot=${STAGING_DIR_HOST}${SDKPATHNATIVE} \
    oe_runconf
}

do_compile_prepend() {
    export TARGET=${HOST_SYS}
}

do_install_prepend() {
    export TARGET=${HOST_SYS}
}

do_install_append() {
    rm -f ${D}/${libdir}/erlang/Install
}

SRC_URI[md5sum] = "e5ece977375197338c1b93b3d88514f8"
SRC_URI[sha256sum] = "17ce53459bc5ceb34fc2da412e15ac8c23835a15fbd84e62c8d1852704747ee7"
