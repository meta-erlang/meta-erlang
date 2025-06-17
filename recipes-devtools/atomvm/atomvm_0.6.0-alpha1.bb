SUMMARY = "Tiny Erlang VM"
DESCRIPTION = "Brings Erlang, Elixir and other functional languages to really small systems. \
AtomVM implements from scratch a minimal Erlang VM that supports a subset of ErlangVM features \
and that is able to run unmodified BEAM binaries on really small systems like MCUs."
HOMEPAGE = "https://atomvm.org/"

LICENSE = "Apache-2.0 & LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://LICENSES/LGPL-2.1-or-later.txt;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "git://github.com/atomvm/AtomVM;protocol=https;branch=master \
           file://0001-Remove-PackBEAM-dependency-rules.patch"

# Modify these as desired
PV = "0.6.0-alpha1+git"
SRCREV = "c239678426f97cc50eea8261f679f1490b355b3a"

S = "${WORKDIR}/git"

# NOTE: unable to map the following CMake package dependencies: Dialyzer Sphinx Graphviz Elixir
# NOTE: the following library dependencies are unknown, ignoring: REQUIRED
#       (this is based on recipes that have previously been built and packaged)
DEPENDS = "zlib openssl gperf-native erlang-native elixir-native packbeam-native"

inherit cmake

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = "-DAVM_BUILD_RUNTIME_ONLY=OFF -DAVM_DISABLE_PACKBEAM_BUILD=ON"

do_install:append() {
    install -d ${D}/${datadir}/${BPN}/examples/erlang
    install -d ${D}/${datadir}/${BPN}/examples/elixir
    install -m 0644 ${B}/examples/elixir/*.avm ${D}/${datadir}/${BPN}/examples/elixir
    install -m 0644 ${B}/examples/erlang/*.avm ${D}/${datadir}/${BPN}/examples/erlang
}

PACKAGES =+ "${PN}-examples"
FILES:${PN}-examples = "${datadir}/${BPN}/examples/"
RDEPENDS:${PN}-examples += "${PN}"

# don't complain that some examples haval build patchs
INSANE_SKIP:${PN}-examples += "buildpaths"
