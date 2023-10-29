SUMMARY = "PackBEAM"
DESCRIPTION = "A tool for aggregating beam and other file types into a single file that is used as the \
code base for an AtomVM application."
HOMEPAGE = "https://www.atomvm.net/"

LICENSE = "Apache-2.0 & LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://LICENSES/LGPL-2.1-or-later.txt;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "git://github.com/atomvm/AtomVM;protocol=https;branch=master"

BBCLASSEXTEND = "native nativesdk"

# Modify these as desired
PV = "0.6.0-alpha1+git"
SRCREV = "c239678426f97cc50eea8261f679f1490b355b3a"

S = "${WORKDIR}/git"

# NOTE: unable to map the following CMake package dependencies: Dialyzer Sphinx Graphviz Elixir
# NOTE: the following library dependencies are unknown, ignoring: REQUIRED
#       (this is based on recipes that have previously been built and packaged)
DEPENDS = "zlib openssl gperf-native"

inherit cmake

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = "-DAVM_BUILD_RUNTIME_ONLY=ON"
OECMAKE_TARGET_COMPILE = "PackBEAM"

do_install() {
    install -d ${D}/${bindir}
    install -m 0755 ${B}/tools/packbeam/PackBEAM ${D}/${bindir}
}
