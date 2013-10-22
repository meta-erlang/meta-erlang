include erlang.inc
DEPENDS += "erlang-native openssl"

PR = "r3"

TARGET_CC_ARCH += "${LDFLAGS}"

EXTRA_OEMAKE = "BUILD_CC='${BUILD_CC}'"

EXTRA_OECONF = "--with-ssl=${STAGING_DIR_HOST}${layout_exec_prefix}"

EXTRA_OECONF_append_arm = " --disable-smp-support --disable-hipe"
EXTRA_OECONF_append_armeb = " --disable-smp-support --disable-hipe"
EXTRA_OECONF_append_mipsel = " --disable-smp-support --disable-hipe"
EXTRA_OECONF_append_sh3 = " --disable-smp-support --disable-hipe"
EXTRA_OECONF_append_sh4 = " --disable-smp-support --disable-hipe"

NATIVE_BIN = "${TMPDIR}/work/erlang-native-${PV}-${PR}/otp_src_${UPSTREAM_VERSION}/bin"

do_configure() {

    cd ${S}/erts; autoreconf; cd -
    cd ${S}/lib/wx; autoreconf; cd -

    . ${CONFIG_SITE}

    ac_cv_prog_javac_ver_1_2=no \
    ac_cv_prog_javac_ver_1_5=no \
	SHLIB_LD='${CC}' \
    oe_runconf

    sed -i -e 's|$(ERL_TOP)/bin/dialyzer|${NATIVE_BIN}/dialyzer --output_plt $@ -pa $(ERL_TOP)/lib/kernel/ebin -pa $(ERL_TOP)/lib/stdlib/ebin|' lib/dialyzer/src/Makefile
}

do_compile() {
    TARGET=${TARGET_SYS} \
    PATH=${NATIVE_BIN}:$PATH \
    oe_runmake noboot
}

do_install() {
    TARGET=${TARGET_SYS} \
    PATH=${NATIVE_BIN}:$PATH \
    oe_runmake 'INSTALL_PREFIX=${D}' install

    for f in erl start
        do sed -i -e 's:ROOTDIR=.*:ROOTDIR=${libdir}/erlang:' \
        	${D}/${libdir}/erlang/erts-*/bin/$f ${D}/${libdir}/erlang/bin/$f
    done
}

PACKAGES =+ "${PN}-libs-dbg ${PN}-libs"

FILES_${PN}-staticdev += "${libdir}/*/*/*/*.a ${libdir}/*/*/*/*/*.a ${libdir}/*/*/*/*/*/*.a "

FILES_${PN}-libs-dbg += " ${libdir}/erlang/*/.debug ${libdir}/erlang/*/*/.debug ${libdir}/erlang/*/*/*/.debug ${libdir}/erlang/*/*/*/*/.debug ${libdir}/erlang/*/*/*/*/*/.debug "

SRC_URI[md5sum] = "dd6c2a4807551b4a8a536067bde31d73"
SRC_URI[sha256sum] = "5bc34fc34fc890f84bae7ff1f7c81fbec2c9aa28a0ef51a57d7a8192204d8aa2"
