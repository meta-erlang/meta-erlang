DESCRIPTION = "Erlang Port Mapper Daemon."
HOMEPAGE = "https://erlang.org/doc/man/epmd.html"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ff253ad767462c46be284da12dda33e8"
SECTION = "network"
PR = "r0"

SRC_URI = "git://github.com/erlang/otp;branch=master;protocol=https \
           file://0001-Use-autoconf-2.72e.patch \
           file://epmd.init \
           file://epmd.service \
           file://epmd.socket \
          "

S = "${WORKDIR}/git"

DEPENDS = "erlang-native ncurses zlib"

inherit autotools-brokensep

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"
PACKAGECONFIG[systemd] = "--enable-systemd,,systemd"

EXTRA_OECONF += "--without-wx"

TARGET_CC_ARCH += "${LDFLAGS}"

EXTRA_OEMAKE = "BUILD_CC='${BUILD_CC}'"

NATIVE_BIN = "${STAGING_LIBDIR_NATIVE}/erlang/bin"

CACHED_CONFIGUREVARS += "ac_cv_prog_javac_ver_1_2=no ac_cv_prog_javac_ver_1_5=no erl_xcomp_sysroot=${STAGING_DIR_TARGET} ac_cv_header_GL_gl_h=yes ac_cv_header_OpenGL_gl_h=yes"

OTP_BUILD_CONFIGURE_OPTS ?= "update_configure --no-commit"

do_configure() {
    cd ${S}; ./otp_build ${OTP_BUILD_CONFIGURE_OPTS}; cd -

    . ${CONFIG_SITE}

    SHLIB_LD='${CC}' \
    oe_runconf

    sed -i -e 's|$(ERL_TOP)/bin/dialyzer|${NATIVE_BIN}/dialyzer --output_plt $@ -pa $(ERL_TOP)/lib/kernel/ebin -pa $(ERL_TOP)/lib/stdlib/ebin|' lib/dialyzer/src/Makefile
}

do_compile() {
    cd ${S}/erts/epmd
    PATH=${NATIVE_BIN}:$PATH \    
    oe_runmake ERL_TOP=${S} TARGET=${TARGET_SYS}
}

do_install() {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${UNPACKDIR}/epmd.service ${D}${systemd_system_unitdir}
        install -m 0644 ${UNPACKDIR}/epmd.socket ${D}${systemd_system_unitdir}
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
        install -d ${D}${sysconfdir}/init.d
        install -m 0755 ${UNPACKDIR}/epmd.init ${D}${sysconfdir}/init.d/epmd
    fi

    # get the correct triple, as we are not calling any make install rule
    TARGET_SYS_GNU=`${S}/erts/autoconf/config.sub ${TARGET_SYS}`

    install -d ${D}/${sbindir}
    install -m 0755 ${S}/bin/${TARGET_SYS_GNU}/epmd ${D}/${sbindir}/epmd
}

inherit useradd update-rc.d systemd

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "--system epmd"
USERADD_PARAM:${PN}  = "--system --create-home --home /var/lib/epmd \
                        -g epmd epmd"

INITSCRIPT_NAME = "epmd"
INITSCRIPT_PARAMS = "defaults"

SYSTEMD_SERVICE:${PN} = "epmd.service epmd.socket"

FILES:${PN} += "${systemd_system_unitdir}"
