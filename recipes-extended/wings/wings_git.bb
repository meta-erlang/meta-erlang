SUMMARAY = "Wings3D is an advanced sub-division 3D modeller"

DESCRIPTION = "Wings3D is an advanced sub-division modeller inspired by the Nendo \
and Mirai modellers from Izware."

HOMEPAGE = "http://www.wings3d.com/"
LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://license.terms;md5=889fe8682ad9d17b3067710663f6436e \
                    file://unix/COPYING;md5=ea5bed2f60d357618ca161ad539f7c0a"

SRC_URI = "git://github.com/dgud/wings;protocol=https;branch=master;destsuffix=wings \
    file://0001-Release-unix-without-makeself.sh.patch"

# Modify these as desired
PV = "2.2.7+git${SRCPV}"
SRCREV = "a64f8f766cef0c197dc23d511764103d65ab376c"

S = "${WORKDIR}/wings"

DEPENDS = "erlang erlang-native rebar3-native opencl-headers opencl-icd-loader"

RDEPENDS:${PN} = "erlang wxwidgets"

ERLANG_ERTS = "$(erl -version 2>&1 | gawk '{print $NF}' | tr -d '\n\r')"

do_compile () {
    cd ${S}
    make
}

do_install () {
    make unix
    install -m 0755 -d ${D}/${libdir}/wings
    
    # install on target at /usr/lib/wings
    cp -r ${S}/build/wings-2.2.7.dirty-linux/* ${D}/${libdir}/wings/

    # fix up erlang binaries with the correct arch
    install -m 0755 ${STAGING_LIBDIR}/erlang/erts-${ERLANG_ERTS}/bin/erl_child_setup ${D}/${libdir}/wings/bin/erl_child_setup
    install -m 0755 ${STAGING_LIBDIR}/erlang/erts-${ERLANG_ERTS}/bin/inet_gethost ${D}/${libdir}/wings/bin/inet_gethost
    install -m 0755 ${STAGING_LIBDIR}/erlang/erts-${ERLANG_ERTS}/bin/erlexec ${D}/${libdir}/wings/bin/erlexec
    install -m 0755 ${STAGING_LIBDIR}/erlang/erts-${ERLANG_ERTS}/bin/beam.smp ${D}/${libdir}/wings/bin/beam.smp

    # hack: this is not correct, but works for demo purposes
    staging_wxdir=$(find ${STAGING_LIBDIR}/erlang/lib/wx-* -maxdepth 0)
    wxdir=$(find ${D}/${libdir}/wings/lib/wx-* -maxdepth 0)
    install -d 0755 ${wxdir}/priv
    for i in ${staging_wxdir}/priv/*; do
        install -m 0644 $i ${wxdir}/priv/
    done

    # remove wings_convert script
    rm ${D}/${libdir}/wings/wings_convert
}

INSANE_SKIP:${PN} += "split-strip already-stripped ldflags"
