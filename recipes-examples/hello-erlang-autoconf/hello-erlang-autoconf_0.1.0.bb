SUMMARY = "Demonstrating how erlang autoconf based can be used with the Yocto project"
SECTION = "examples"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=62612d1c0b9e46d8296dd0097c07db91"

SRC_URI = "git://github.com/meta-erlang/hello-world;branch=master;subpath=${BPN};protocol=https"
PV = "0.1.0+git"
SRCREV = "d65050e302f7ea51e33b3209d9ed39a9f088fbc5"

DEPENDS = "erlang-native"

RDEPENDS:${PN} = "erlang-erts erlang-kernel erlang-stdlib erlang-epmd"

S = "${UNPACKDIR}/${BPN}"

inherit autotools update-rc.d 

export ERL_COMPILER_OPTIONS = "deterministic"

SRC_DIR = "${libdir}/erlang/lib/hello-*/src"
EBIN_DIR = "${libdir}/erlang/lib/hello-*/ebin"
CONF_DIR = "${sysconfdir}/hello.d"

do_install:append() {
     install -d ${D}${CONF_DIR}
     install -m 0755 ${S}/sys/hello.config ${D}${CONF_DIR}
     install -d ${D}${sysconfdir}/init.d
     install -m 0755 ${S}/sys/hello.otp.system ${D}${sysconfdir}/init.d
     install -d ${D}${bindir}
     install -m 0755 ${B}/sys/hello.start ${D}${bindir}
     install -m 0755 ${B}/sys/hello.stop ${D}${bindir}
}

FILES:${PN}  = "${EBIN_DIR}"
FILES:${PN} += "${CONF_DIR}"
FILES:${PN} += "${CONF_DIR}/hello.boot"
FILES:${PN} += "${sysconfdir}/init.d/hello.otp.system"
FILES:${PN} += "${bindir}/hello.start"
FILES:${PN} += "${bindir}/hello.stop"

# Add source code into its own -src package
PACKAGE_DEBUG_SPLIT_STYLE = "debug-without-src"

FILES:${PN}-src += "${SRC_DIR}"

CONFFILES:${PN} = "${CONF_DIR}/hello.config"

INITSCRIPT_NAME = "hello.otp.system"
INITSCRIPT_PARAMS = "defaults 75"
