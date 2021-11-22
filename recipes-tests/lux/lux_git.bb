SUMMARY = "Test automation framework with Expect style execution of commands"
DESCRIPTION = "Lux (LUcid eXpect scripting) is a test automation framework with Expect style execution of commands. See [Expect][] for more info about the origin."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c6aa54a157ba92156983bf01e1eb7728"

SRC_URI = "git://github.com/hawk/lux;protocol=https;branch=master"

PV = "2.6+git${SRCPV}"
SRCREV = "ec04d30d335bc522eb67880035d1d5de2013e34f"

S = "${WORKDIR}/git"

DEPENDS = "erlang-native"
RDEPENDS:${PN} = "erlang"


inherit autotools-brokensep

EXTRA_OECONF = ""

BBCLASSEXTEND = "nativesdk"
