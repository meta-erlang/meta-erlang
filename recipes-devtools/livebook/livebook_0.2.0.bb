SUMMARY = "Interactive and collaborative code notebooks - made with Phoenix LiveView"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e23fadd6ceef8c618fc1c65191d846fa"

S = "${WORKDIR}/git"

SRCREV = "2de997cef5bd809ccea382fb09a54c946e07e3dc"
PV = "0.2.0-git${SRCPV}"
SRC_URI = "git://github.com/elixir-nx/livebook;branch=main;protocol=https \
           file://livebook.service \
           file://livebook.conf"

RDEPENDS:${PN} = "erlang erlang-modules elixir"

inherit mix systemd

do_install:append() {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${sysconfdir}
        install -m 0644 ${WORKDIR}/livebook.conf ${D}${sysconfdir}
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/livebook.service ${D}${systemd_unitdir}/system
    fi
}

SYSTEMD_SERVICE:${PN} = "livebook.service"

SYSTEMD_AUTO_ENABLE = "enable"
