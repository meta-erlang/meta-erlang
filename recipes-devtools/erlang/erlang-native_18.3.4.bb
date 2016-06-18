include erlang.inc
include erlang-${PV}.inc

inherit native

PR = "r0"

EXTRA_OECONF = "--with-ssl=${STAGING_DIR_NATIVE}"

CACHED_CONFIGUREVARS += "ac_cv_prog_javac_ver_1_2=no ac_cv_prog_javac_ver_1_5=no erl_xcomp_sysroot=${STAGING_DIR_NATIVE}"

do_configure() {
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

