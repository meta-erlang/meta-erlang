include erlang.inc
include erlang-${PV}.inc
DEPENDS += "erlang-native openssl"

require erlang-${PV}-manifest.inc

PR = "r0"

FILESEXTRAPATHS_prepend := "${THISDIR}/files/23:"

PACKAGECONFIG ??= ""

PACKAGECONFIG[odbc] = "--with-odbc,--without-odbc,libodbc"
PACKAGECONFIG[lttng] = "--with-dynamic-trace=lttng,--without-dynamic-trace,lttng-ust"
PACKAGECONFIG[wx] = "--with-wx --with-observer,--without-wx --without-observer,wxwidgets,"
PACKAGECONFIG[sctp] = ",,,lksctp-tools"

TARGET_CC_ARCH += "${LDFLAGS}"

EXTRA_OEMAKE = "BUILD_CC='${BUILD_CC}'"

# EXTRA_OECONF_append_arm = " --disable-smp-support --disable-hipe"
# EXTRA_OECONF_append_armeb = " --disable-smp-support --disable-hipe"
EXTRA_OECONF_append_mipsel = " --disable-smp-support --disable-hipe"
EXTRA_OECONF_append_sh3 = " --disable-smp-support --disable-hipe"
EXTRA_OECONF_append_sh4 = " --disable-smp-support --disable-hipe"

NATIVE_BIN = "${STAGING_LIBDIR_NATIVE}/erlang/bin"

CACHED_CONFIGUREVARS += "ac_cv_prog_javac_ver_1_2=no ac_cv_prog_javac_ver_1_5=no erl_xcomp_sysroot=${STAGING_DIR_TARGET}"

do_configure() {
    cd ${S}; ./otp_build autoconf; cd -
    cd ${S}/erts; autoreconf; cd -

    . ${CONFIG_SITE}

    SHLIB_LD='${CC}' \
    oe_runconf

    sed -i -e 's|$(ERL_TOP)/bin/dialyzer|${NATIVE_BIN}/dialyzer --output_plt $@ -pa $(ERL_TOP)/lib/kernel/ebin -pa $(ERL_TOP)/lib/stdlib/ebin|' lib/dialyzer/src/Makefile
}

do_compile() {
    TARGET=${TARGET_SYS} \
    PATH=${NATIVE_BIN}:$PATH \
    oe_runmake
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
    chown -R root:root ${D}${libdir}/erlang
}

