include erlang.inc
include erlang-${PV}.inc

inherit nativesdk

require erlang-${PV}-manifest.inc

DEPENDS = "erlang-native openssl ncurses"

RDEPENDS_${PN} = "nativesdk-ncurses nativesdk-erlang-modules"

EXTRA_OECONF = "--with-ssl=${STAGING_DIR_NATIVE}"

PR = "r0"

CACHED_CONFIGUREVARS += "ac_cv_prog_javac_ver_1_2=no ac_cv_prog_javac_ver_1_5=no erl_xcomp_sysroot=${STAGING_DIR_HOST}"

do_configure() {

    rm -rf ${S}/lib/wx

    cd ${S}; ./otp_build autoconf
    TARGET=${HOST_SYS} \
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

