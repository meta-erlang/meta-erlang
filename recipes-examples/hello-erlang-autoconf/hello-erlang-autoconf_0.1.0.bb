SUMMARY = "Demonstrating how erlang autoconf based can be used with the Yocto project"
SECTION = "examples"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "git://github.com/meta-erlang/hello-world;branch=master;subpath=${BPN};protocol=https"
PV = "0.1.0+git"
SRCREV = "6c7f37489e79fb5953c100bb705ee89346ff4835"

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