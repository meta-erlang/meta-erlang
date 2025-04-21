SUMMARY = "ExDoc is a tool to generate documentation for Erlang and Elixir projects."
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e4071154021ff55c25a0b6ed90b1b0cb"

RECIPE_MAINTAINER = "João Henrique Ferreira de Freitas <joaohf@gmail.com>"

S = "${WORKDIR}/git"

SRCREV = "0ab3d49585fe13f1be38465d936aa4e73db6f4c1"
PR = "r0"
SRC_URI = "git://github.com/elixir-lang/ex_doc;branch=main;protocol=https" 

RDEPENDS:${PN} = "erlang elixir"

inherit mix-rebar3

do_compile() {
    MIX_ENV=${MIX_ENV} mix escript.build
}

do_install() {
    install -d 0755 ${D}/${bindir}
    install -m 0755 ex_doc ${D}/${bindir}/ex_doc
}

BBCLASSEXTEND = "native nativesdk"
