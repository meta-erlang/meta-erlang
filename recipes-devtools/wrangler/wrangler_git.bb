SUMMARY = "Wrangler is an interactive refactoring tool for Erlang."

DESCRIPTION = "Wrangler is an interactive refactoring tool for Erlang, \
integrated into Visual Studio Code and other LSP-compliant editors as the \
Wrangler language server, as well as emacs and Eclipse."

HOMEPAGE = "https://refactoringtools.github.io/docs/wrangler/"

RECIPE_MAINTAINER = "João Henrique Ferreira de Freitas <joaohf@gmail.com>"

DEPENDS = "erlang-native"

#RDEPENDS:${PN} = "erlang erlang-compiler erlang-crypto erlang-xmerl erlang-ssl erlang-public-key erlang-asn1 erlang-inets erlang-os-mon"

LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://LICENCE;md5=6888ef3d1f67c84a0c390a9877854067"

SRC_URI = "git://github.com/RefactoringTools/wrangler;protocol=https;branch=master \
           file://0001-Handle-m4-local-macros.patch \
           file://0001-Fix-run-test-code.patch \
           file://0001-Add-header-guards-to-suffix_tree.h.patch" 

SRCREV = "5ae964bf708b38162561b81502933339919b3a70"
PR = "r0"

PV = "1.2+git${SRCPV}"

S = "${WORKDIR}/git"

PARALLEL_MAKE = ""

export ERL_COMPILER_OPTIONS="deterministic"

inherit autotools-brokensep

FILES:${PN} += " ${libdir}/*"

BBCLASSEXTEND = "nativesdk"
