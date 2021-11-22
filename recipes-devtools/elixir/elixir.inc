DESCRIPTION = "Elixir is a dynamic, functional language designed for building \
scalable and maintainable applications."
SECTION = "devel/elixir"
PRIORITY = "optional"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e23fadd6ceef8c618fc1c65191d846fa"

DEPENDS += "erlang-native"
DEPENDS:append:class-native = " erlang-native"

SRC_URI = "git://github.com/elixir-lang/elixir;branch=master;protocol=https"

S = "${WORKDIR}/git"

PACKAGECONFIG ??= ""

do_configure() {
    :
}

do_compile() {
    oe_runmake
}

do_install() {
    oe_runmake install PREFIX=${D}${prefix}
}

BBCLASSEXTEND = "native nativesdk"

PROVIDES += "${PN}-mix-doc ${PN}-mix-dbg ${PN}-mix-dev ${PN}-mix-staticdev ${PN}-mix \
             ${PN}-ex-unit-doc ${PN}-ex-unit-dbg ${PN}-ex-unit-dev ${PN}-ex-unit-staticdev ${PN}-ex-unit \
             ${PN}-eex-doc ${PN}-eex-dbg ${PN}-eex-dev ${PN}-eex-staticdev ${PN}-eex \
             ${PN}-doc ${PN}-dbg ${PN}-dev ${PN}-staticdev ${PN} \
             "

PACKAGES = "${PN}-mix-doc ${PN}-mix-dbg ${PN}-mix-dev ${PN}-mix-staticdev ${PN}-mix \
             ${PN}-ex-unit-doc ${PN}-ex-unit-dbg ${PN}-ex-unit-dev ${PN}-ex-unit-staticdev ${PN}-ex-unit \
             ${PN}-eex-doc ${PN}-eex-dbg ${PN}-eex-dev ${PN}-eex-staticdev ${PN}-eex \
             ${PN}-doc ${PN}-dbg ${PN}-dev ${PN}-staticdev ${PN} ${PN}-modules ${PN}-modules-dev \
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
FILES:${PN}-mix = "${libdir}/elixir/lib/mix ${libdir}/elixir/bin/mix ${bindir}/mix "

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
FILES:${PN}-ex-unit = "${libdir}/elixir/lib/ex_unit "

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
FILES:${PN}-eex = "${libdir}/elixir/lib/eex "

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
FILES:${PN} = "${libdir}/elixir/lib/logger ${libdir}/elixir/lib/iex ${libdir}/elixir/lib/elixir ${libdir}/elixir/bin/iex ${libdir}/elixir/bin/elixirc ${libdir}/elixir/bin/elixir ${bindir}/elixir ${bindir}/elixirc ${bindir}/iex "

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