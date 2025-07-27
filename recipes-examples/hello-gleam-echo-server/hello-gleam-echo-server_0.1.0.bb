SUMMARY = "An example Gleam web application "
SECTION = "examples"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENCE;md5=bd052113ed5b73a32ff7cf9f42c3265c"

SRCREV = "94f29a84dc82ed4e7878d4027fd27acacdb8be84"
PV = "0.1.0+git${SRCPV}"
SRC_URI = "git://github.com/gleam-lang/example-echo-server;branch=main;protocol=https \
           file://hello-gleam-echo-server.service"

inherit gleam systemd

do_install:append() {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${UNPACKDIR}/hello-gleam-echo-server.service ${D}${systemd_unitdir}/system
    fi
}

SYSTEMD_SERVICE:${PN} = "hello-gleam-echo-server.service"

SYSTEMD_AUTO_ENABLE = "disable"