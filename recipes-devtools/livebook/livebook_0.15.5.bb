SUMMARY = "Interactive and collaborative code notebooks - made with Phoenix LiveView"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e23fadd6ceef8c618fc1c65191d846fa"

RECIPE_MAINTAINER = "João Henrique Ferreira de Freitas <joaohf@gmail.com>"

SRCREV = "a24aa5f153fc5da4d5b9a9a2a572404fa8fd686e"
PR = "r0"
SRC_URI = "git://github.com/livebook-dev/livebook;branch=v0.15;protocol=https \
           file://livebook.service \
           file://livebook.conf" 

SRC_URI:append:class-nativesdk = " \
           file://environment.d-livebook.sh \
           "

RDEPENDS:${PN} = "erlang erlang-modules elixir elixir-mix"

inherit mix systemd useradd

do_install:append() {
    install -d ${D}${sysconfdir}
    install -m 0644 ${UNPACKDIR}/livebook.conf ${D}${sysconfdir}
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${UNPACKDIR}/livebook.service ${D}${systemd_unitdir}/system
    fi
}

do_install:append:class-nativesdk () {
    install -d ${D}${SDKPATHNATIVE}/environment-setup.d
    install -m 644 ${UNPACKDIR}/environment.d-livebook.sh \
        ${D}${SDKPATHNATIVE}/environment-setup.d/livebook.sh

    sed -i -e 's|^RELEASE_ROOT=.*|RELEASE_ROOT=${LIVEBOOK_OE_RELEASE_ROOT}|' \
        ${erlang_release}/bin/livebook

    install -d ${D}${base_bindir}
    ln -s ../usr/lib/livebook/bin/livebook ${D}${base_bindir}/livebook
}

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "--system livebook"
USERADD_PARAM:${PN}  = "--system --create-home --home /var/lib/livebook -g livebook livebook"

SYSTEMD_SERVICE:${PN} = "livebook.service"

SYSTEMD_AUTO_ENABLE = "enable"

BBCLASSEXTEND = "nativesdk"

FILES:${PN}:append:class-nativesdk = " ${SDKPATHNATIVE}/environment-setup.d/livebook.sh"

# Some elixir modules includes a 'location' when using macros. For example: use Agent.
# That leads to some buildpaths included into elixir modules. Here, we are ignoring
# buildpaths for some packages. For all details see: https://github.com/meta-erlang/meta-erlang/issues/328
INSANE_SKIP:${PN} += "buildpaths"
