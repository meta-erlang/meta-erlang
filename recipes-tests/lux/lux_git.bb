SUMMARY = "Test automation framework with Expect style execution of commands"
DESCRIPTION = "Lux (LUcid eXpect scripting) is a test automation framework with Expect style execution of commands. See [Expect][] for more info about the origin."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c6aa54a157ba92156983bf01e1eb7728"

SRC_URI = "git://github.com/hawk/lux;protocol=https"

PV = "2.5.1+git${SRCPV}"
SRCREV = "5526e5a73a2388669283c22ea429f63a26fe07e6"

S = "${WORKDIR}/git"

DEPENDS = "erlang-native"
RDEPENDS_${PN} = "erlang"


inherit autotools-brokensep

EXTRA_OECONF = ""

BBCLASSEXTEND = "nativesdk"
