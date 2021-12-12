DESCRIPTION = "A dynamic programming language and runtime environment, \
with built-in support for concurrency, distribution and fault tolerance"
SECTION = "devel/erlang"
PRIORITY = "optional"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ff253ad767462c46be284da12dda33e8"

BBCLASSEXTEND = "native nativesdk"

DEPENDS:append:class-target = " erlang-native ncurses zlib openssl"
DEPENDS:append:class-native = " ncurses-native zlib-native openssl-native"
DEPENDS:append:class-nativesdk = " erlang-native nativesdk-zlib nativesdk-openssl nativesdk-ncurses"


SRC_URI = "git://github.com/erlang/otp;branch=master;protocol=https \
    file://environment.d-erlang.sh.in"
SRC_URI += "file://fix-wx-configure.patch"
SRC_URI += "file://otp-0002-Remove-rpath.patch"

S = "${WORKDIR}/git"

PARALLEL_MAKE = ""

inherit autotools-brokensep

PACKAGECONFIG ??= ""
PACKAGECONFIG:class-native ??= ""
PACKAGECONFIG:class-nativesdk ??= ""
PACKAGECONFIG[odbc] = "--with-odbc,--without-odbc,libodbc"
PACKAGECONFIG[lttng] = "--with-dynamic-trace=lttng,--without-dynamic-trace,lttng-ust"
PACKAGECONFIG[wx] = "--with-wx,,wxwidgets"
PACKAGECONFIG[observer] = "--with-observer,--without-observer,wxwidgets,"
PACKAGECONFIG[sctp] = ",,,lksctp-tools"
PACKAGECONFIG[java] = ",--without-javac,,"
PACKAGECONFIG[pkgconfig] = "--enable-pkg-config,,,"

TARGET_CC_ARCH += "${LDFLAGS}"

EXTRA_OEMAKE = "BUILD_CC='${BUILD_CC}'"


# EXTRA_OECONF:append:arm = " --disable-smp-support --disable-hipe"
# EXTRA_OECONF:append:armeb = " --disable-smp-support --disable-hipe"
EXTRA_OECONF:append:mipsel = " --disable-smp-support --disable-hipe"
EXTRA_OECONF:append_sh3 = " --disable-smp-support --disable-hipe"
EXTRA_OECONF:append:sh4 = " --disable-smp-support --disable-hipe"

NATIVE_BIN = "${STAGING_LIBDIR_NATIVE}/erlang/bin"

EXTRA_OECONF:class-native ??= " \
    --with-ssl=${STAGING_DIR_NATIVE}/usr --with-ssl-zlib=${STAGING_DIR_NATIVE}/usr \
    "
EXTRA_OECONF:class-nativesdk ??= " \
    --with-ssl=${STAGING_DIR_NATIVE}/usr --with-ssl-zlib=${STAGING_DIR_NATIVE}/usr \
    "

CACHED_CONFIGUREVARS += "ac_cv_prog_javac_ver_1_2=no ac_cv_prog_javac_ver_1_5=no erl_xcomp_sysroot=${STAGING_DIR_TARGET}"
CACHED_CONFIGUREVARS:class-native += "ac_cv_prog_javac_ver_1_2=no ac_cv_prog_javac_ver_1_5=no erl_xcomp_sysroot=${STAGING_DIR_NATIVE}"
CACHED_CONFIGUREVARS:class-nativesdk += "ac_cv_prog_javac_ver_1_2=no ac_cv_prog_javac_ver_1_5=no erl_xcomp_sysroot=${STAGING_DIR_NATIVE}"

OTP_BUILD_CONFIGURE_OPTS ?= "autoconf"

# https://github.com/erlang/otp/issues/4821 setting EGREP to make AC_EGREP_CPP works
export EGREP = "egrep"

do_configure:class-target() {
    cd ${S}; ./otp_build ${OTP_BUILD_CONFIGURE_OPTS}; cd -

    . ${CONFIG_SITE}

    SHLIB_LD='${CC}' \
    oe_runconf

    sed -i -e 's|$(ERL_TOP)/bin/dialyzer|${NATIVE_BIN}/dialyzer --output_plt $@ -pa $(ERL_TOP)/lib/kernel/ebin -pa $(ERL_TOP)/lib/stdlib/ebin|' lib/dialyzer/src/Makefile
}

do_configure:class-native() {
    cd ${S}; ./otp_build ${OTP_BUILD_CONFIGURE_OPTS}
    TARGET=${HOST_SYS} \
    oe_runconf
}

do_configure:class-nativesdk() {
    cd ${S}; ./otp_build ${OTP_BUILD_CONFIGURE_OPTS}
    TARGET=${HOST_SYS} \
    oe_runconf
}


do_compile:prepend:class-native() {
    export TARGET=${HOST_SYS}
}

do_compile:prepend:class-nativesdk() {
    export TARGET=${HOST_SYS}
}

do_compile:class-target() {
    TARGET=${TARGET_SYS} \
    PATH=${NATIVE_BIN}:$PATH \
    oe_runmake
}

do_install:prepend:class-native() {
    export TARGET=${HOST_SYS}
}

do_install:prepend:class-nativesdk() {
    export TARGET=${HOST_SYS}

    sed -i -e 's,@ERTS_VERSION@,${ERTS_VERSION},g' \
        -e 's,@ERL_INTERFACE_VERSION@,${ERL_INTERFACE_VERSION},g' \
         ${WORKDIR}/environment.d-erlang.sh.in
    mkdir -p ${D}${SDKPATHNATIVE}/environment-setup.d
    install -m 644 ${WORKDIR}/environment.d-erlang.sh.in ${D}${SDKPATHNATIVE}/environment-setup.d/erlang.sh
}

do_install:class-target() {
    TARGET=${TARGET_SYS} \
    PATH=${NATIVE_BIN}:$PATH \
    oe_runmake 'DESTDIR=${D}' install
    for f in erl start
        do sed -i -e 's:ROOTDIR=.*:ROOTDIR=${libdir}/erlang:' \
            ${D}/${libdir}/erlang/erts-${ERTS_VERSION}/bin/$f ${D}/${libdir}/erlang/bin/$f
    done

    cp ${D}/${libdir}/erlang/erts-${ERTS_VERSION}/bin/dyn_erl ${D}/${libdir}/erlang/erts-${ERTS_VERSION}/bin/erl

    rm -f ${D}/${libdir}/erlang/Install

    # Actually wx is not suitable with erlang embedded
    rm -rf ${D}/${libdir}/erlang/lib/wx-*
    chown -R root:root ${D}${libdir}/erlang
}

do_install:prepend:class-native() {
    export TARGET=${HOST_SYS}
}

do_install:append:class-nativesdk() {
    rm -f ${D}/${libdir}/erlang/Install
    chown -R root:root ${D}${libdir}/erlang

    create_wrapper ${D}${bindir}/erl \
        FONTCONFIG_PATH=/etc/fonts \
        GTK_THEME=Adwaita
}

do_install:append:class-target() {
	# remove erlang code that does not need to be loaded by the erlang runtime
	rm -rf ${D}/${libdir}/erlang/lib/erts-${ERTS_VERSION}/ebin
}

FILES:${PN}:append:class-nativesdk = " ${SDKPATHNATIVE}/environment-setup.d/erlang.sh"
