SUMMARY = "Interactive and collaborative code notebooks - made with Phoenix LiveView"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e23fadd6ceef8c618fc1c65191d846fa"

RECIPE_MAINTAINER = "João Henrique Ferreira de Freitas <joaohf@gmail.com>"

S = "${WORKDIR}/git"

SRCREV = "060ac4b8263c550d05607b75e065a7929b0bf658"
PV = "0.10.0+git${SRCPV}"
PR = "r0"
SRC_URI = "git://github.com/livebook-dev/livebook;branch=v0.10;protocol=https \
           file://livebook.service \
           file://livebook.conf" 

RDEPENDS:${PN} = "erlang erlang-modules elixir elixir-mix"

inherit mix systemd useradd

do_install:append() {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${sysconfdir}
        install -m 0644 ${WORKDIR}/livebook.conf ${D}${sysconfdir}
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/livebook.service ${D}${systemd_unitdir}/system
    fi
}

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "--system livebook"
USERADD_PARAM:${PN}  = "--system --create-home --home /var/lib/livebook -g livebook livebook"

SYSTEMD_SERVICE:${PN} = "livebook.service"

SYSTEMD_AUTO_ENABLE = "enable"
