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
           "

# Remove -m64 parameter and bad host include paths
SRC_URI += "file://Makefile.c_src.cl"

PV .= "+git${SRCPV}"
SRCREV = "a5ca80d15e415baf1199a80a28a2b9f3a74d2db8"

S = "${UNPACKDIR}/wings"

inherit erlang-version

DEPENDS = "erlang erlang-native rebar3-native opencl-headers opencl-icd-loader"

RDEPENDS:${PN} = "erlang wxwidgets"

ERLANG_ERTS = "$(erl -version 2>&1 | gawk '{print $NF}' | tr -d '\n\r')"

do_compile[network] = "1"

# These two prefuncs are very ugly, but I could not find a better
# way to patch wings as it downloads external dependency during build time.
do_compile[prefuncs] += "do_fetch_wings_deps"
do_compile[prefuncs] += "do_patch_wings_cl"

do_fetch_wings_deps() {
    cd ${S}
    # clone external dependencies
    make _deps/cl _deps/libigl _deps/eigen
}

do_patch_wings_cl() {
    cd ${S}
    install -D ${UNPACKDIR}/Makefile.c_src.cl ${S}/_deps/cl/c_src/Makefile
}

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

    # Install target wx drivers, wings build is not able to install the correct target version
    WX_VERSION=${@get_erlang_application(d, "wx")}
    WX_PRIV=${STAGING_LIBDIR}/erlang/lib/$WX_VERSION/priv
    WX_DIR=${D}/${libdir}/wings/lib/$WX_VERSION/priv
    install -m 0644 $WX_PRIV/erl_gl.so $WX_DIR
    install -m 0644 $WX_PRIV/wxe_driver.so $WX_DIR

    # remove wings_convert script
    rm ${D}/${libdir}/wings/wings_convert
}

INSANE_SKIP:${PN} += "split-strip already-stripped ldflags"
