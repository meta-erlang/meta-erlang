SUMMARY = "Test automation framework with Expect style execution of commands"
DESCRIPTION = "Lux (LUcid eXpect scripting) is a test automation framework with Expect style execution of commands. See [Expect][] for more info about the origin."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=21bbf7065a9eec106e1fa9d32381c9af"

SRC_URI = "git://github.com/hawk/lux;protocol=https;branch=master"

PV = "2.9.1"
SRCREV = "4452c53f8f4ac072e9396d882c18c876129feb41"

S = "${WORKDIR}/git"

DEPENDS = "erlang-native"
RDEPENDS:${PN} = "erlang"


inherit autotools-brokensep

EXTRA_OECONF = ""

BBCLASSEXTEND = "nativesdk"
