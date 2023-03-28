SUMMARY = "The Erlang Language Server"
DESCRIPTION = "An Erlang server implementing Microsoft's Language Server Protocol 3.15." 
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7f313310d7132582da56ae0c7feeb2d2"

SRC_URI = "git://github.com/erlang-ls/erlang_ls;branch=main;protocol=https"

SRCREV = "b151f9d9a4a1dde1118ae2822226500e2fa69125"

S = "${WORKDIR}/git"

PR = "r0"

inherit rebar3

DEPENDS += "erlang-native"

do_compile () {
    make
}

do_install() {
    PREFIX=${D}/${prefix} make -e install
}

FILES_${PN} = "${bindir}/erlang_ls ${bindir}/els_dap"
FILES_${PN}-dbg += "/.debug"

BBCLASSEXTEND = "native nativesdk"
