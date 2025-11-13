DESCRIPTION = "A dynamic programming language and runtime environment, \
with built-in support for concurrency, distribution and fault tolerance"
SECTION = "devel/erlang"
PRIORITY = "optional"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ff253ad767462c46be284da12dda33e8"

BBCLASSEXTEND = "native nativesdk"

DEPENDS:append:class-target = " erlang-native openssl"
DEPENDS:append:class-native = " ncurses-native zlib-native openssl-native"
DEPENDS:append:class-nativesdk = " erlang-native nativesdk-zlib nativesdk-openssl nativesdk-ncurses"

RDEPENDS:${PN}-ptest += "bash erlang-modules openssh tmux"

SRC_URI = "git://github.com/erlang/otp;branch=master;protocol=https \
           file://environment.d-erlang.sh.in \
           file://run-ptest \
           file://otp-0002-Remove-rpath.patch \
           file://0001-erts-add-pkg-config-support-for-erts.patch \
           file://0002-erl_interface-Add-pkg-config-support-for-erl_interfa.patch \
           file://0001-wx-Enable-wx-cross-compilation.patch \
           file://0001-Fix-reproducibility-issue.patch \
           file://0001-Use-autoconf-2.72e.patch \
           file://0001-Fix-incompatible-pointer-types.patch \
           file://0002-Fix-implicit-int.patch \
           file://0003-Fix-incompatible-pointer-type-ErlDrvPort.patch \
           "

S = "${WORKDIR}/git"

inherit ptest autotools-brokensep pkgconfig

PACKAGECONFIG ?= "pkgconfig zlib termcap"
PACKAGECONFIG:class-native ?= "pkgconfig"
# For nativesdk, check if opengl is available before including: wx, observer, et, debugger
# as these application depends on wx and it will not be build without opengl support.
#
# reltool is also an erlang application which uses wx. However reltool does not depend
# on wx_object behavior, so, it's safe (no graphic interface available, only batch mode)
# to allow reltool for nativesdk. For the rest (observer, et and debugger) which use wx_object
# it is safer to remove them from nativesdk support.
PACKAGECONFIG:class-nativesdk ?= "pkgconfig termcap \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'wx observer et debugger', '', d)}"
PACKAGECONFIG[zlib] = "--disable-builtin-zlib,--enable-builtin-zlib,zlib"
PACKAGECONFIG[termcap] = "--with-termcap,--without-termcap,ncurses"
PACKAGECONFIG[odbc] = "--with-odbc,--without-odbc,libodbc"
PACKAGECONFIG[lttng] = "--with-dynamic-trace=lttng,--without-dynamic-trace,lttng-ust"
PACKAGECONFIG[wx] = "--with-wx,--without-wx,wxwidgets"
PACKAGECONFIG[observer] = "--with-observer,--without-observer,,"
PACKAGECONFIG[et] = "--with-et,--without-et,,"
PACKAGECONFIG[debugger] = "--with-debugger,--without-debugger,,"
PACKAGECONFIG[sctp] = ",,,lksctp-tools"
PACKAGECONFIG[java] = ",--without-javac,,"
PACKAGECONFIG[pkgconfig] = "--enable-pkg-config,,,"

RRECOMMENDS:${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'sctp', 'kernel-module-sctp', '', d)}"

TARGET_CC_ARCH += "${LDFLAGS}"

EXTRA_OEMAKE = "BUILD_CC='${BUILD_CC}'"

OTP_BUILD_CONFIGURE_OPTS = "update_configure --no-commit"

NATIVE_BIN = "${STAGING_LIBDIR_NATIVE}/erlang/bin"

EXTRA_OECONF:class-native ??= " \
    --with-ssl=${STAGING_DIR_NATIVE}/usr --with-ssl-zlib=${STAGING_DIR_NATIVE}/usr \
    "
EXTRA_OECONF:class-nativesdk ??= " \
    --with-ssl=${STAGING_DIR_NATIVE}/usr --with-ssl-zlib=${STAGING_DIR_NATIVE}/usr \
    "
EXTRA_OECONF:class-target ??= " --with-ssl-rpath=no --enable-deterministic-build"

CACHED_CONFIGUREVARS += "ac_cv_prog_javac_ver_1_2=no ac_cv_prog_javac_ver_1_5=no erl_xcomp_sysroot=${STAGING_DIR_TARGET} ac_cv_header_GL_gl_h=yes ac_cv_header_OpenGL_gl_h=yes"
CACHED_CONFIGUREVARS:class-native += "ac_cv_prog_javac_ver_1_2=no ac_cv_prog_javac_ver_1_5=no erl_xcomp_sysroot=${STAGING_DIR_NATIVE}"
CACHED_CONFIGUREVARS:class-nativesdk += "ac_cv_prog_javac_ver_1_2=no ac_cv_prog_javac_ver_1_5=no erl_xcomp_sysroot=${STAGING_DIR_NATIVE}"

OTP_BUILD_CONFIGURE_OPTS ?= "autoconf"

# https://github.com/erlang/otp/issues/4821 setting EGREP to make AC_EGREP_CPP works
export EGREP = "egrep"

export ERL_TOP = "${S}"

do_configure:class-target() {
    cd ${S}; ./otp_build ${OTP_BUILD_CONFIGURE_OPTS}; cd -

    . ${CONFIG_SITE}

    SHLIB_LD='${CC}' \
    oe_runconf

    # Remove well know flags which have build host patch
    sed -i -e 's@[^ ]*-fmacro-prefix-map=[^ "]*@@g' \
        -e 's@[^ ]*-fdebug-prefix-map=[^ "]*@@g' \
        -e 's@[^ ]*-I[^ "]*@@g' \
        ${S}/erts/${TARGET_SYS}*/config.h

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

do_compile:append:class-nativesdk() {
    oe_runmake docs DOC_TARGETS=chunks
}

do_compile:class-target() {
    TARGET=${TARGET_SYS} \
    PATH=${NATIVE_BIN}:$PATH \
    oe_runmake

    oe_runmake docs DOC_TARGETS=chunks
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
    oe_runmake 'DESTDIR=${D}' install-docs DOC_TARGETS=chunks

    cp ${D}/${libdir}/erlang/erts-${ERTS_VERSION}/bin/dyn_erl ${D}/${libdir}/erlang/erts-${ERTS_VERSION}/bin/erl

    rm -f ${D}/${libdir}/erlang/Install

    chown -R root:root ${D}${libdir}/erlang
}

do_install:prepend:class-native() {
    export TARGET=${HOST_SYS}
}

do_install:append:class-native() {
    create_wrapper ${D}${bindir}/erl \
	OPENSSL_MODULES=${libdir}/ossl-modules
}

do_install:append:class-nativesdk() {
    oe_runmake 'DESTDIR=${D}' install-docs DOC_TARGETS=chunks

    rm -f ${D}/${libdir}/erlang/Install
    chown -R root:root ${D}${libdir}/erlang

    create_wrapper ${D}${bindir}/erl \
        FONTCONFIG_PATH=/etc/fonts \
        GTK_THEME=Adwaita \
	OPENSSL_MODULES=${libdir}/ossl-modules
}

do_install:append:class-target() {
    # remove erlang code that does not need to be loaded by the erlang runtime
    rm -rf ${D}/${libdir}/erlang/lib/erts-${ERTS_VERSION}/ebin
}

do_configure_ptest() {
    xcomp=${WORKDIR}/erl-xcomp-oe.conf
    echo 'erl_xcomp_build=${BUILD_SYS}' > $xcomp
    echo 'erl_xcomp_host=${TARGET_SYS}' >> $xcomp
    echo 'erl_xcomp_configure_flags="--without-wx --enable-deterministic-build"' >> $xcomp
    echo 'erl_xcomp_sysroot=${STAGING_DIR_TARGET}' >> $xcomp
    echo 'export CC="${TARGET_PREFIX}gcc ${TARGET_CC_ARCH} --sysroot=${STAGING_DIR_TARGET}"' >> $xcomp
    echo 'export CXX="${TARGET_PREFIX}g++ ${TARGET_CC_ARCH} --sysroot=${STAGING_DIR_TARGET}"' >> $xcomp
    echo 'export CPP="${TARGET_PREFIX}gcc -E ${TARGET_CC_ARCH} --sysroot=${STAGING_DIR_TARGET}"' >> $xcomp
    echo 'export AS="${TARGET_PREFIX}as ${TARGET_AS_ARCH}"' >> $xcomp
    echo 'export LD="${TARGET_PREFIX}ld ${TARGET_LD_ARCH} ${STAGING_DIR_TARGET}"' >> $xcomp
    echo 'export GDB=${TARGET_PREFIX}gdb' >> $xcomp
    echo 'export STRIP=${TARGET_PREFIX}strip' >> $xcomp
    echo 'export RANLIB=${TARGET_PREFIX}ranlib' >> $xcomp
    echo 'export OBJCOPY=${TARGET_PREFIX}objcopy' >> $xcomp
    echo 'export OBJDUMP=${TARGET_PREFIX}objdump' >> $xcomp
    echo 'export READELF=${TARGET_PREFIX}readelf' >> $xcomp
    echo 'export AR=${TARGET_PREFIX}ar' >> $xcomp
    echo 'export NM=${TARGET_PREFIX}nm' >> $xcomp
    echo 'export M4=m4' >> $xcomp
    echo 'export TARGET_PREFIX=${TARGET_PREFIX}' >> $xcomp
    echo 'export CONFIGURE_FLAGS="--target=${TARGET_SYS} --host=${TARGET_SYS} --build=${SDK_ARCH}-linux --with-libtool-sysroot=${STAGING_DIR_TARGET}"' >> $xcomp
    echo 'export CFLAGS="${TARGET_CFLAGS}"' >> $xcomp
    echo 'export CXXFLAGS="${TARGET_CXXFLAGS}"' >> $xcomp
    echo 'export LDFLAGS="${TARGET_LDFLAGS}"' >> $xcomp
    echo 'export CPPFLAGS="${TARGET_CPPFLAGS}"' >> $xcomp
    echo 'export KCFLAGS="--sysroot=${STAGING_DIR_TARGET}"' >> $xcomp
    echo 'export ARCH=${ARCH}' >> $xcomp
    echo 'export CROSS_COMPILE=${TARGET_PREFIX}' >> $xcomp
}

do_compile_ptest() {
    # need a full erlang build before call release_tests
    oe_runmake release_tests
    cd ${S}/release/tests/test_server
    erl -noshell -eval 'ts:install([{xcomp, "${WORKDIR}/erl-xcomp-oe.conf"}])' -s ts compile_testcases -s init stop
}

do_install_ptest() {
    install -d ${D}${PTEST_PATH}
    cp -r ${S}/release/tests ${D}${PTEST_PATH}
    find ${D}${PTEST_PATH} -name '*.o' -delete
}

FILES:${PN}:append:class-nativesdk = " ${SDKPATHNATIVE}/environment-setup.d/erlang.sh"
