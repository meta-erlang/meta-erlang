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

SRC_URI[md5sum] = "a5f78c1cf0eb7724de3a59babc1a28e5"
SRC_URI[sha256sum] = "297f2baaa65e5ed1c2492c500fe191bff852217ad57af5da2470a3218c9acadb"
