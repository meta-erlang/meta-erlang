DESCRIPTION = "A dynamic programming language and runtime environment, \
with built-in support for concurrency, distribution and fault tolerance"
SECTION = "devel/erlang"
PRIORITY = "optional"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ff253ad767462c46be284da12dda33e8"

BBCLASSEXTEND = "native nativesdk"

DEPENDS:append:class-target = " erlang-native"
DEPENDS:append:class-nativesdk = " erlang-native"

RDEPENDS:${PN}-ptest += "bash erlang-modules openssh \
                         ${@bb.utils.contains("BBFILE_COLLECTIONS", "openembedded-layer", "tmux", "", d)}"

SRC_URI = "git://github.com/erlang/otp;branch=master;protocol=https \
           file://epmd.socket \
           file://epmd.service \
           file://epmd.init \
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

PTEST_BUILD_HOST_FILES = "Emakefile Makefile variables conf_vars"

# Default erlang applications based on class (target, native and nativesdk):
ERLANG_APPLICATIONS:remove:class-target = "odbc wx observer debugger et reltool jinterface megaco dialyzer emacs"
# wx is not useful for native build, but it is supported if enabled
ERLANG_APPLICATIONS:remove:class-native = "odbc wx observer debugger et jinterface megaco emacs"
# For nativesdk, check with opengl is available before including: wx, observer, et, debugger
# as these application depends on wx and it will not be build without opengl support.
#
# reltool is also an erlang application which uses wx. However reltool does not depend
# on wx_object behavior, so, it's safe (no graphic interface available, only batch mode)
# to allow reltool for nativesdk. For the rest (observer, et and debugger) which use wx_object
# it is safer to remove them from nativesdk support.
ERLANG_APPLICATIONS:remove:class-nativesdk = "odbc jinterface megaco \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', '', 'wx observer et debugger', d)}"

PACKAGECONFIG:class-target ?= "pkgconfig zlib termcap ${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)} ${ERLANG_APPLICATIONS}"
PACKAGECONFIG:class-native ?= "pkgconfig zlib termcap ${ERLANG_APPLICATIONS}"
PACKAGECONFIG:class-nativesdk ?= "pkgconfig zlib termcap ${ERLANG_APPLICATIONS}"

PACKAGECONFIG[zlib] = ",,zlib,,,builtin-zlib"
PACKAGECONFIG[builtin-zlib] = "--enable-builtin-zlib,--disable-builtin-zlib,,,,zlib"
PACKAGECONFIG[termcap] = "--with-termcap,--without-termcap,ncurses"
PACKAGECONFIG[lttng] = "--with-dynamic-trace=lttng,--without-dynamic-trace,lttng-ust"
PACKAGECONFIG[pkgconfig] = "--enable-pkg-config,,,"
PACKAGECONFIG[systemd] = "--enable-systemd,,systemd"
PACKAGECONFIG[static-drivers] = "--enable-static-drivers,--disable-static-drivers,,"
PACKAGECONFIG[static-nifs] = "--enable-static-nifs,--disable-static-nifs,,"

# Only depends on kernel-module-sctp for target build
LKSCTP_DEPS:class-target = "kernel-module-sctp"
# Host should support linux sctp kernel module
LKSCTP_DEPS:class-nativesdk = ""

PACKAGECONFIG[sctp] = "--enable-sctp,--disable-sctp,lksctp-tools,${LKSCTP_DEPS}"
PACKAGECONFIG[java] = ",--without-javac,,"

# Build debug emulator: https://www.erlang.org/doc/system/debugging.html#debug-emulator
PACKAGECONFIG[emu-type-debug] = ""

TARGET_CC_ARCH += "${LDFLAGS}"

EXTRA_OEMAKE = "BUILD_CC='${BUILD_CC}'"

OTP_BUILD_CONFIGURE_OPTS = "update_configure --no-commit"

NATIVE_BIN = "${STAGING_LIBDIR_NATIVE}/erlang/bin"

EXTRA_OECONF:class-native ??= " \
    ${@bb.utils.contains('PACKAGECONFIG', 'crypto', '--with-ssl=${STAGING_EXECPREFIXDIR} --with-ssl-zlib=${STAGING_EXECPREFIXDIR}', '', d)} \
    "
EXTRA_OECONF:class-nativesdk ??= " \
    ${@bb.utils.contains('PACKAGECONFIG', 'crypto', '--with-ssl=${STAGING_EXECPREFIXDIR} --with-ssl-zlib=${STAGING_EXECPREFIXDIR}', '', d)} \
    --enable-deterministic-build \
    "
EXTRA_OECONF:class-target ??= " \
    ${@bb.utils.contains('PACKAGECONFIG', 'crypto', '--with-ssl-rpath=no', '', d)} \
    --enable-deterministic-build"

# Due multilib wxwidgets, it's necessary to pass additional arguments to wx-config script.
WX_CONFIG = "'${STAGING_BINDIR_CROSS}/wx-config --prefix=${STAGING_EXECPREFIXDIR} --baselib=${baselib}'"

CACHED_CONFIGUREVARS += "ac_cv_prog_javac_ver_1_2=no ac_cv_prog_javac_ver_1_5=no erl_xcomp_sysroot=${STAGING_DIR_TARGET} ac_cv_header_GL_gl_h=yes ac_cv_header_OpenGL_gl_h=yes"
CACHED_CONFIGUREVARS:class-native += "ac_cv_prog_javac_ver_1_2=no \
    ac_cv_prog_javac_ver_1_5=no \
    erl_xcomp_sysroot=${STAGING_DIR_NATIVE} \
    ${@bb.utils.contains("BBFILE_COLLECTIONS", "openembedded-layer", "ac_cv_path_WX_CONFIG_PATH=${WX_CONFIG}", "", d)} \
    "
CACHED_CONFIGUREVARS:class-nativesdk += "ac_cv_prog_javac_ver_1_2=no \
    ac_cv_prog_javac_ver_1_5=no \
    erl_xcomp_sysroot=${STAGING_DIR_NATIVE} \
    ${@bb.utils.contains("BBFILE_COLLECTIONS", "openembedded-layer", "ac_cv_path_WX_CONFIG_PATH=${WX_CONFIG}", "", d)} \
    "

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

    if ${@bb.utils.contains('PACKAGECONFIG', 'emu-type-debug', 'true', 'false', d)}; then
        TARGET=${TARGET_SYS} \
        PATH=${NATIVE_BIN}:$PATH \
        oe_runmake -C erts/emulator debug
    fi
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

    if ${@bb.utils.contains('PACKAGECONFIG', 'emu-type-debug', 'true', 'false', d)}; then
        cp ${S}/bin/${TARGET_SYS}-gnu/beam.debug* ${D}/${libdir}/erlang/erts-${ERTS_VERSION}/bin/
        cp ${S}/bin/${TARGET_SYS}-gnu/erl_child_setup.debug ${D}/${libdir}/erlang/erts-${ERTS_VERSION}/bin/
    fi

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
    # remove erlang code that does not need to be loaded by the erlang runtime [1].
    # it is clear that some preload modules exists inside erts. So, those respective beam
    # modules don't need to be installed.
    # 1: https://github.com/erlang/otp/blob/master/HOWTO/DEVELOPMENT.md#preloaded-and-primary-bootstrap
    rm -rf ${D}/${libdir}/erlang/lib/erts-${ERTS_VERSION}/ebin/*.beam

    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/epmd.service ${D}${systemd_system_unitdir}
        install -m 0644 ${WORKDIR}/epmd.socket ${D}${systemd_system_unitdir}
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
        install -d ${D}${sysconfdir}/init.d
        install -m 0755 ${WORKDIR}/epmd.init ${D}${sysconfdir}/init.d/epmd
    fi

    # only install dialyzer if it is present in PACKAGECONFIG
    if ${@bb.utils.contains('PACKAGECONFIG', 'dialyzer', 'false', 'true', d)}; then
        for bin in typer dialyzer; do
            rm -f ${D}${libdir}/erlang/erts-${ERTS_VERSION}/bin/$bin
            rm -f ${D}${libdir}/erlang/bin/$bin
            rm -f ${D}${bindir}/$bin
        done
    fi
}

do_install:append() {
    # remove unnecessary installed files
    rm -rf ${D}/${libdir}/erlang/misc
    rm -rf ${D}/${libdir}/erlang/erts-${ERTS_VERSION}/info

    # avoid prefix-map, breaks reproducible builds
    sed -i -e 's@[^ ]*-ffile-prefix-map=[^ "]*@@g' \
        -e 's@[^ ]*-fmacro-prefix-map=[^ "]*@@g' \
        -e 's@[^ ]*-fdebug-prefix-map=[^ "]*@@g' \
        -e 's@[^ ]*-I[^ "]*@@g' ${D}/${libdir}/erlang/lib/erl_interface-*/src/eidefs.mk
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
    sed -i s:@PTEST_PATH@:${PTEST_PATH}:g ${D}${PTEST_PATH}/run-ptest
}

FILES:${PN}:append:class-nativesdk = " ${SDKPATHNATIVE}/environment-setup.d/erlang.sh"

# Erlang tests ignores +deterministic flag because it may depends on additional
# module meta data. https://github.com/erlang/otp/issues/8602
# It's safer to keep this behaviour and skip buildpaths for erlang-ptest package.
# For additional details see: https://github.com/erlang/otp/commit/da001d9d75f2cac4ebc577d362a21ebff58080c6
INSANE_SKIP:${PN}-ptest += "buildpaths"

# For epmd support
inherit useradd update-rc.d systemd

USERADD_PACKAGES = "${PN}-epmd"
GROUPADD_PARAM:${PN}-epmd = "--system epmd"
USERADD_PARAM:${PN}-epmd  = "--system --create-home --home /var/lib/epmd \
                             -g epmd epmd"

INITSCRIPT_PACKAGES = "${PN}-epmd"
INITSCRIPT_NAME:${PN}-epmd = "epmd"
INITSCRIPT_PARAMS:${PN}-epmd = "defaults"

SYSTEMD_PACKAGES = "${PN}-epmd"
SYSTEMD_SERVICE:${PN}-epmd = "epmd.service epmd.socket"
SYSTEMD_AUTO_ENABLE:${PN}-epmd = "enable"
