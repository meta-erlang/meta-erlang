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

SRC_URI[md5sum] = "c330150913556a0fe73e57a441cb6375"
SRC_URI[sha256sum] = "6133b3410681a5c934e54c76eee1825f96dead8d6a12c31a64f6e160daf0bb06"
