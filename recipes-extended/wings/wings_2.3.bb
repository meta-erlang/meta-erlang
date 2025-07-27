SUMMARAY = "Wings3D is an advanced sub-division 3D modeller"

DESCRIPTION = "Wings3D is an advanced sub-division modeller inspired by the Nendo \
and Mirai modellers from Izware."

HOMEPAGE = "http://www.wings3d.com/"
NO_GENERIC_LICENSE[Wings] = "license.terms"
LICENSE = "Wings"
LIC_FILES_CHKSUM = "file://license.terms;md5=889fe8682ad9d17b3067710663f6436e \
                    file://unix/COPYING;md5=ea5bed2f60d357618ca161ad539f7c0a"

RECIPE_MAINTAINER = "João Henrique Ferreira de Freitas <joaohf@gmail.com>"

SRC_URI = "git://github.com/dgud/wings;protocol=https;branch=master;destsuffix=wings \
           file://0001-Release-unix-without-makeself.sh.patch \
           file://0004-Fix-triple-quote-warning.patch \
           file://0002-New-FBX-importer-and-exporter-imports-and-exports-ge.patch \
           file://0003-wpc_fbx_p-Fixes-to-transparency-and-material-names.patch \
           file://0001-x3d_import-Fixed-transparency-added-gzip-support.patch \
           "

PV .= "+git${SRCPV}"
SRCREV = "4d3e856e666202dcefbf0103a2f6638ae03f1129"

S = "${UNPACKDIR}/wings"

DEPENDS = "erlang erlang-native rebar3-native opencl-headers opencl-icd-loader"

RDEPENDS:${PN} = "erlang wxwidgets"

ERLANG_ERTS = "$(erl -version 2>&1 | gawk '{print $NF}' | tr -d '\n\r')"

do_compile[network] = "1"

do_compile () {
    cd ${S}
    make
}

do_install () {
    make unix
    install -m 0755 -d ${D}/${libdir}/wings
    
    # install on target at /usr/lib/wings
    cp -r ${S}/build/wings-*-linux/* ${D}/${libdir}/wings/

    # fix up erlang binaries with the correct arch
    install -m 0755 ${STAGING_LIBDIR}/erlang/erts-${ERLANG_ERTS}/bin/erl_child_setup ${D}/${libdir}/wings/bin/erl_child_setup
    install -m 0755 ${STAGING_LIBDIR}/erlang/erts-${ERLANG_ERTS}/bin/inet_gethost ${D}/${libdir}/wings/bin/inet_gethost
    install -m 0755 ${STAGING_LIBDIR}/erlang/erts-${ERLANG_ERTS}/bin/erlexec ${D}/${libdir}/wings/bin/erlexec
    install -m 0755 ${STAGING_LIBDIR}/erlang/erts-${ERLANG_ERTS}/bin/beam.smp ${D}/${libdir}/wings/bin/beam.smp

    # remove wings_convert script
    rm ${D}/${libdir}/wings/wings_convert
}

INSANE_SKIP:${PN} += "split-strip already-stripped ldflags"
