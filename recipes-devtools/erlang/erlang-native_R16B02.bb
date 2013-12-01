include erlang.inc

inherit native

PR = "r1"

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

SRC_URI[md5sum] = "ca63bcde0e5ae0f2df9457f97b3115a4"
SRC_URI[sha256sum] = "6ab8ad1df8185345554a4b80e10fd8be06c4f2b71b69dcfb8528352787b32f85"
