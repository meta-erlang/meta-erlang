SUMMARY = "Test automation framework with Expect style execution of commands"
DESCRIPTION = "Lux (LUcid eXpect scripting) is a test automation framework with Expect style execution of commands. See [Expect][] for more info about the origin."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=efd539ca98d0bacb6f03446d3b1ddaa2"

SRC_URI = "git://github.com/hawk/lux;protocol=https;branch=master"

PV = "2.8.1+git${SRCPV}"
SRCREV = "3e72cd3474d2668199aec7d2549d852ffe65c8bf"

S = "${WORKDIR}/git"

DEPENDS = "erlang-native"
RDEPENDS:${PN} = "erlang"

export ERL_COMPILER_OPTIONS="deterministic"

inherit autotools-brokensep

EXTRA_OECONF = ""

BBCLASSEXTEND = "nativesdk"
