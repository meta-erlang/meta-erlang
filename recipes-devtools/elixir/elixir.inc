DESCRIPTION = "Elixir is a dynamic, functional language designed for building \
scalable and maintainable applications."
SECTION = "devel/elixir"
PRIORITY = "optional"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e23fadd6ceef8c618fc1c65191d846fa"

DEPENDS += "erlang-native"
DEPENDS:append:class-native = " erlang-native"

RDEPENDS:${PN}-ptest += "make bash erlang-modules git"

SRC_URI += "\
    file://environment.d-elixir.sh \
    file://run-ptest"

PARALLEL_MAKE = ""
PARALLEL_MAKEINST = ""

PACKAGECONFIG ??= ""

inherit ptest

do_configure() {
    :
}

do_compile() {
    if ${@bb.utils.contains('DISTRO_FEATURES','ptest','false', 'true',d)}; then
        # Use erlc deterministic build, https://github.com/elixir-lang/elixir/issues/8986
        export ERL_COMPILER_OPTIONS="deterministic"
    fi
    oe_runmake    
}

do_install() {
    if ${@bb.utils.contains('DISTRO_FEATURES','ptest','false', 'true',d)}; then
        # Use erlc deterministic build, https://github.com/elixir-lang/elixir/issues/8986
        export ERL_COMPILER_OPTIONS="deterministic"
    fi
    oe_runmake install PREFIX=${D}${prefix}
}

do_install:prepend:class-nativesdk() {
    mkdir -p ${D}${SDKPATHNATIVE}/environment-setup.d
    install -m 644 ${UNPACKDIR}/environment.d-elixir.sh ${D}${SDKPATHNATIVE}/environment-setup.d/elixir.sh
}

do_install_ptest() {
    install -d ${D}${PTEST_PATH}
    cp -pR ${S} ${D}${PTEST_PATH}/elixir
    rm -rf ${D}${PTEST_PATH}/elixir/.git
    sed -i s:@PTEST_PATH@:${PTEST_PATH}:g ${D}${PTEST_PATH}/run-ptest
}

BBCLASSEXTEND = "native nativesdk"

PROVIDES += "${PN}-mix-doc ${PN}-mix-dbg ${PN}-mix-dev ${PN}-mix-staticdev ${PN}-mix \
             ${PN}-ex-unit-doc ${PN}-ex-unit-dbg ${PN}-ex-unit-dev ${PN}-ex-unit-staticdev ${PN}-ex-unit \
             ${PN}-eex-doc ${PN}-eex-dbg ${PN}-eex-dev ${PN}-eex-staticdev ${PN}-eex \
             ${PN}-doc ${PN}-dbg ${PN}-dev ${PN}-staticdev ${PN} \
             "

PACKAGE_BEFORE_PN = "${PN}-mix-doc ${PN}-mix-dbg ${PN}-mix-dev ${PN}-mix-staticdev ${PN}-mix \
             ${PN}-ex-unit-doc ${PN}-ex-unit-dbg ${PN}-ex-unit-dev ${PN}-ex-unit-staticdev ${PN}-ex-unit \
             ${PN}-eex-doc ${PN}-eex-dbg ${PN}-eex-dev ${PN}-eex-staticdev ${PN}-eex \
             ${PN}-modules ${PN}-modules-dev \
             "

ALLOW_EMPTY:${PN}-mix-doc = "1"
DESCRIPTION:${PN}-mix-doc = ""
RDEPENDS:${PN}-mix-doc = ""
FILES:${PN}-mix-doc = "${mandir}/man1/mix.1 "

ALLOW_EMPTY:${PN}-mix-dbg = "1"
DESCRIPTION:${PN}-mix-dbg = ""
RDEPENDS:${PN}-mix-dbg = ""
FILES:${PN}-mix-dbg = ""

ALLOW_EMPTY:${PN}-mix-dev = "1"
DESCRIPTION:${PN}-mix-dev = ""
RDEPENDS:${PN}-mix-dev = ""
FILES:${PN}-mix-dev = ""

ALLOW_EMPTY:${PN}-mix-staticdev = "1"
DESCRIPTION:${PN}-mix-staticdev = ""
RDEPENDS:${PN}-mix-staticdev = ""
FILES:${PN}-mix-staticdev = ""

ALLOW_EMPTY:${PN}-mix = "1"
DESCRIPTION:${PN}-mix = ""
RDEPENDS:${PN}-mix = ""
FILES:${PN}-mix = "${nonarch_libdir}/elixir/lib/mix ${nonarch_libdir}/elixir/bin/mix ${bindir}/mix "

ALLOW_EMPTY:${PN}-ex-unit-doc = "1"
DESCRIPTION:${PN}-ex-unit-doc = ""
RDEPENDS:${PN}-ex-unit-doc = ""
FILES:${PN}-ex-unit-doc = ""

ALLOW_EMPTY:${PN}-ex-unit-dbg = "1"
DESCRIPTION:${PN}-ex-unit-dbg = ""
RDEPENDS:${PN}-ex-unit-dbg = ""
FILES:${PN}-ex-unit-dbg = ""

ALLOW_EMPTY:${PN}-ex-unit-dev = "1"
DESCRIPTION:${PN}-ex-unit-dev = ""
RDEPENDS:${PN}-ex-unit-dev = ""
FILES:${PN}-ex-unit-dev = ""

ALLOW_EMPTY:${PN}-ex-unit-staticdev = "1"
DESCRIPTION:${PN}-ex-unit-staticdev = ""
RDEPENDS:${PN}-ex-unit-staticdev = ""
FILES:${PN}-ex-unit-staticdev = ""

ALLOW_EMPTY:${PN}-ex-unit = "1"
DESCRIPTION:${PN}-ex-unit = ""
RDEPENDS:${PN}-ex-unit = ""
FILES:${PN}-ex-unit = "${nonarch_libdir}/elixir/lib/ex_unit "

ALLOW_EMPTY:${PN}-eex-doc = "1"
DESCRIPTION:${PN}-eex-doc = ""
RDEPENDS:${PN}-eex-doc = ""
FILES:${PN}-eex-doc = ""

ALLOW_EMPTY:${PN}-eex-dbg = "1"
DESCRIPTION:${PN}-eex-dbg = ""
RDEPENDS:${PN}-eex-dbg = ""
FILES:${PN}-eex-dbg = ""

ALLOW_EMPTY:${PN}-eex-dev = "1"
DESCRIPTION:${PN}-eex-dev = ""
RDEPENDS:${PN}-eex-dev = ""
FILES:${PN}-eex-dev = ""

ALLOW_EMPTY:${PN}-eex-staticdev = "1"
DESCRIPTION:${PN}-eex-staticdev = ""
RDEPENDS:${PN}-eex-staticdev = ""
FILES:${PN}-eex-staticdev = ""

ALLOW_EMPTY:${PN}-eex = "1"
DESCRIPTION:${PN}-eex = ""
RDEPENDS:${PN}-eex = ""
FILES:${PN}-eex = "${nonarch_libdir}/elixir/lib/eex "

ALLOW_EMPTY:${PN}-doc = "1"
DESCRIPTION:${PN}-doc = ""
RDEPENDS:${PN}-doc = ""
FILES:${PN}-doc = "${mandir}/man1/elixir.1 ${mandir}/man1/elixirc.1 ${mandir}/man1/iex.1 "

ALLOW_EMPTY:${PN}-dbg = "1"
DESCRIPTION:${PN}-dbg = ""
RDEPENDS:${PN}-dbg = ""
FILES:${PN}-dbg = ""

ALLOW_EMPTY:${PN}-dev = "1"
DESCRIPTION:${PN}-dev = ""
RDEPENDS:${PN}-dev = ""
FILES:${PN}-dev = ""

ALLOW_EMPTY:${PN}-staticdev = "1"
DESCRIPTION:${PN}-staticdev = ""
RDEPENDS:${PN}-staticdev = ""
FILES:${PN}-staticdev = ""

ALLOW_EMPTY:${PN} = "1"
DESCRIPTION:${PN} = ""
RDEPENDS:${PN} = "erlang erlang-compiler"
RDEPENDS:${PN}:class-native = "erlang"
FILES:${PN} = "${nonarch_libdir}/elixir/lib/logger ${nonarch_libdir}/elixir/lib/iex ${nonarch_libdir}/elixir/lib/elixir ${nonarch_libdir}/elixir/bin/iex ${nonarch_libdir}/elixir/bin/elixirc ${nonarch_libdir}/elixir/bin/elixir ${bindir}/elixir ${bindir}/elixirc ${bindir}/iex "

DESCRIPTION:${PN}-modules = "All Elixir modules"
RDEPENDS:${PN}-modules = "${PN}-mix ${PN}-ex-unit ${PN}-eex ${PN}"
ALLOW_EMPTY:${PN}-modules = "1"

DESCRIPTION:${PN}-modules-dev = "All Elixir modules for developers"
RDEPENDS:${PN}-modules-dev = "${PN}-modules ${PN}-mix-doc ${PN}-mix-dev ${PN}-mix-staticdev \
            ${PN}-ex-unit-doc ${PN}-ex-unit-dev ${PN}-ex-unit-staticdev \
            ${PN}-eex-doc ${PN}-eex-dev ${PN}-eex-staticdev \
            ${PN}-doc ${PN}-dev ${PN}-staticdev \
            "
ALLOW_EMPTY:${PN}-modules-dev = "1"

FILES:${PN}:append:class-nativesdk = " ${SDKPATHNATIVE}/environment-setup.d/elixir.sh"

# Some elixir modules includes a 'location' when using macros. For example: use Agent.
# That leads to some buildpaths included into elixir modules. Here, we are ignoring
# buildpaths for some packages. For all details see: https://github.com/meta-erlang/meta-erlang/issues/328

# for iex and logger which is packaged together with elixir
INSANE_SKIP:${PN} += "buildpaths"
INSANE_SKIP:${PN}-ex-unit += "buildpaths"
INSANE_SKIP:${PN}-mix += "buildpaths"

# Disabled in order to run ptest
INSANE_SKIP:${PN}-eex += "${@bb.utils.contains('DISTRO_FEATURES', 'ptest', 'buildpaths', '', d)}"
INSANE_SKIP:${PN}-ptest += "${@bb.utils.contains('DISTRO_FEATURES', 'ptest', 'buildpaths', '', d)}"
