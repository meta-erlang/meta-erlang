include erlang.inc

inherit native

PR = "r0"

EXTRA_OECONF = '--without-ssl'

do_configure() {
    TARGET=${HOST_SYS} \
    ac_cv_prog_javac_ver_1_2=no \
    ac_cv_prog_javac_ver_1_5=no \
	oe_runconf
}

do_compile_prepend() {
    export TARGET=${HOST_SYS}
}

do_install_prepend() {
    export TARGET=${HOST_SYS}
}

SRC_URI[md5sum] = "a5f78c1cf0eb7724de3a59babc1a28e5"
SRC_URI[sha256sum] = "297f2baaa65e5ed1c2492c500fe191bff852217ad57af5da2470a3218c9acadb"
