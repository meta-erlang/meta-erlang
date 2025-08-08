SUMMARY = "Test automation framework with Expect style execution of commands"
DESCRIPTION = "Lux (LUcid eXpect scripting) is a test automation framework with \
Expect style execution of commands."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=076fb27498b643ae0c594eb233884675"

RECIPE_MAINTAINER = "João Henrique Ferreira de Freitas <joaohf@gmail.com>"

SRC_URI = "git://github.com/hawk/lux;protocol=https;branch=master"

PV = "3.0.0"
SRCREV = "2042deb9dabb754889648b023a9b8ebe3a8ce09c"

DEPENDS = "erlang-native"
RDEPENDS:${PN} = "erlang erlang-compiler erlang-inets erlang-xmerl erlang-runtime-tools"

export ERL_COMPILER_OPTIONS = "deterministic"

inherit autotools-brokensep

EXTRA_OECONF = ""

BBCLASSEXTEND = "nativesdk"

FILES:${PN}-examples = "${libdir}/${PN}/examples ${libdir}/${PN}/tutorial"
FILES:${PN}-dev += "${libdir}/${PN}/emacs"
FILES:${PN}-doc += "${libdir}/${PN}/lux.html ${libdir}/${PN}/*.md"

PACKAGE_BEFORE_PN = "${PN}-examples"
