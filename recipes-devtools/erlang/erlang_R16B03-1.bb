include erlang.inc
DEPENDS += "erlang-native openssl"

require erlang-${PV}-manifest.inc

PR = "r0"

TARGET_CC_ARCH += "${LDFLAGS}"

EXTRA_OEMAKE = "BUILD_CC='${BUILD_CC}'"

EXTRA_OECONF_append_arm = " --disable-smp-support --disable-hipe"
EXTRA_OECONF_append_armeb = " --disable-smp-support --disable-hipe"
EXTRA_OECONF_append_mipsel = " --disable-smp-support --disable-hipe"
EXTRA_OECONF_append_sh3 = " --disable-smp-support --disable-hipe"
EXTRA_OECONF_append_sh4 = " --disable-smp-support --disable-hipe"

NATIVE_BIN = "${STAGING_LIBDIR_NATIVE}/erlang/bin"

do_configure() {

    cd ${S}/erts; autoreconf; cd -
    cd ${S}/lib/wx; autoreconf; cd -

    touch ${S}/lib/wx/SKIP
    touch ${S}/lib/odbc/SKIP

    . ${CONFIG_SITE}

    ac_cv_prog_javac_ver_1_2=no \
    ac_cv_prog_javac_ver_1_5=no \
    SHLIB_LD='${CC}' \
    erl_xcomp_sysroot=${STAGING_DIR_HOST} \
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

    rm -f ${D}/${libdir}/erlang/Install

    # Actually wx is not suitable with erlang embedded
    rm -rf ${D}/${libdir}/erlang/lib/wx-*
}

SRC_URI[md5sum] = "e5ece977375197338c1b93b3d88514f8"
SRC_URI[sha256sum] = "17ce53459bc5ceb34fc2da412e15ac8c23835a15fbd84e62c8d1852704747ee7"
