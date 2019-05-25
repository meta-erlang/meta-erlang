DESCRIPTION = "Elixir is a dynamic, functional language designed for building \
scalable and maintainable applications."
SECTION = "devel/elixir"
PRIORITY = "optional"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e23fadd6ceef8c618fc1c65191d846fa"

DEPENDS += "erlang-native"
DEPENDS_append_class-native = " erlang-native"

SRC_URI = "git://github.com/elixir-lang/elixir;branch=master"

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
             ${PN}-doc ${PN}-dbg ${PN}-dev ${PN}-staticdev ${PN} \
             "

ALLOW_EMPTY_${PN}-mix-doc = "1"
DESCRIPTION_${PN}-mix-doc = ""
RDEPENDS_${PN}-mix-doc = ""
FILES_${PN}-mix-doc = "${mandir}/man1/mix.1 "

ALLOW_EMPTY_${PN}-mix-dbg = "1"
DESCRIPTION_${PN}-mix-dbg = ""
RDEPENDS_${PN}-mix-dbg = ""
FILES_${PN}-mix-dbg = ""

ALLOW_EMPTY_${PN}-mix-dev = "1"
DESCRIPTION_${PN}-mix-dev = ""
RDEPENDS_${PN}-mix-dev = ""
FILES_${PN}-mix-dev = ""

ALLOW_EMPTY_${PN}-mix-staticdev = "1"
DESCRIPTION_${PN}-mix-staticdev = ""
RDEPENDS_${PN}-mix-staticdev = ""
FILES_${PN}-mix-staticdev = ""

ALLOW_EMPTY_${PN}-mix = "1"
DESCRIPTION_${PN}-mix = ""
RDEPENDS_${PN}-mix = ""
FILES_${PN}-mix = "${libdir}/elixir/lib/mix ${libdir}/elixir/bin/mix ${bindir}/mix "

ALLOW_EMPTY_${PN}-ex-unit-doc = "1"
DESCRIPTION_${PN}-ex-unit-doc = ""
RDEPENDS_${PN}-ex-unit-doc = ""
FILES_${PN}-ex-unit-doc = ""

ALLOW_EMPTY_${PN}-ex-unit-dbg = "1"
DESCRIPTION_${PN}-ex-unit-dbg = ""
RDEPENDS_${PN}-ex-unit-dbg = ""
FILES_${PN}-ex-unit-dbg = ""

ALLOW_EMPTY_${PN}-ex-unit-dev = "1"
DESCRIPTION_${PN}-ex-unit-dev = ""
RDEPENDS_${PN}-ex-unit-dev = ""
FILES_${PN}-ex-unit-dev = ""

ALLOW_EMPTY_${PN}-ex-unit-staticdev = "1"
DESCRIPTION_${PN}-ex-unit-staticdev = ""
RDEPENDS_${PN}-ex-unit-staticdev = ""
FILES_${PN}-ex-unit-staticdev = ""

ALLOW_EMPTY_${PN}-ex-unit = "1"
DESCRIPTION_${PN}-ex-unit = ""
RDEPENDS_${PN}-ex-unit = ""
FILES_${PN}-ex-unit = "${libdir}/elixir/lib/ex_unit "

ALLOW_EMPTY_${PN}-eex-doc = "1"
DESCRIPTION_${PN}-eex-doc = ""
RDEPENDS_${PN}-eex-doc = ""
FILES_${PN}-eex-doc = ""

ALLOW_EMPTY_${PN}-eex-dbg = "1"
DESCRIPTION_${PN}-eex-dbg = ""
RDEPENDS_${PN}-eex-dbg = ""
FILES_${PN}-eex-dbg = ""

ALLOW_EMPTY_${PN}-eex-dev = "1"
DESCRIPTION_${PN}-eex-dev = ""
RDEPENDS_${PN}-eex-dev = ""
FILES_${PN}-eex-dev = ""

ALLOW_EMPTY_${PN}-eex-staticdev = "1"
DESCRIPTION_${PN}-eex-staticdev = ""
RDEPENDS_${PN}-eex-staticdev = ""
FILES_${PN}-eex-staticdev = ""

ALLOW_EMPTY_${PN}-eex = "1"
DESCRIPTION_${PN}-eex = ""
RDEPENDS_${PN}-eex = ""
FILES_${PN}-eex = "${libdir}/elixir/lib/eex "

ALLOW_EMPTY_${PN}-doc = "1"
DESCRIPTION_${PN}-doc = ""
RDEPENDS_${PN}-doc = ""
FILES_${PN}-doc = "${mandir}/man1/elixir.1 ${mandir}/man1/elixirc.1 ${mandir}/man1/iex.1 "

ALLOW_EMPTY_${PN}-dbg = "1"
DESCRIPTION_${PN}-dbg = ""
RDEPENDS_${PN}-dbg = ""
FILES_${PN}-dbg = ""

ALLOW_EMPTY_${PN}-dev = "1"
DESCRIPTION_${PN}-dev = ""
RDEPENDS_${PN}-dev = ""
FILES_${PN}-dev = ""

ALLOW_EMPTY_${PN}-staticdev = "1"
DESCRIPTION_${PN}-staticdev = ""
RDEPENDS_${PN}-staticdev = ""
FILES_${PN}-staticdev = ""

ALLOW_EMPTY_${PN} = "1"
DESCRIPTION_${PN} = ""
RDEPENDS_${PN} = "erlang erlang-compiler"
RDEPENDS_${PN}_class-native = "erlang"
FILES_${PN} = "${libdir}/elixir/lib/logger ${libdir}/elixir/lib/iex ${libdir}/elixir/lib/elixir ${libdir}/elixir/bin/iex ${libdir}/elixir/bin/elixirc ${libdir}/elixir/bin/elixir ${bindir}/elixir ${bindir}/elixirc ${bindir}/iex "
