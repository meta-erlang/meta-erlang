SUMMARY = "The Erlang Language Server"
DESCRIPTION = "An Erlang server implementing Microsoft's Language Server Protocol 3.15." 
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7f313310d7132582da56ae0c7feeb2d2"

RECIPE_MAINTAINER = "João Henrique Ferreira de Freitas <joaohf@gmail.com>"

SRC_URI = "git://github.com/erlang-ls/erlang_ls;branch=main;protocol=https"

SRCREV = "63b18395ac313cc4a975b30c54749ab172c6848c"

S = "${WORKDIR}/git"

inherit rebar3-brokensep

export ERL_COMPILER_OPTIONS="deterministic"

DEPENDS += "erlang-native"

do_compile () {
    make
}

do_install() {
    PREFIX=${D}/${prefix} make -e install
}

FILES:${PN} = "${bindir}/erlang_ls ${bindir}/els_dap"
FILES:${PN}-dbg += "/.debug"

BBCLASSEXTEND = "native nativesdk"
