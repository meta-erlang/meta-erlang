SUMMARY = "Breadboard Programming for Erlang inspired by Node-RED"
DESCRIPTION = "An experiment to replace Node-REDs existing NodeJS backend with \
an Erlang equivalent that is 100% compatible to existing flow code."
HOMEPAGE = "https://github.com/gorenje/erlang-red"
SECTION = "libs"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=1ebbd3e34237af26da5dc08a4e440464"

RECIPE_MAINTAINER = "João Henrique Ferreira de Freitas <joaohf@gmail.com>"

SRC_URI = "git://github.com/gorenje/erlang-red.git;protocol=https;branch=main \
           file://erlang-red.service \
           file://erlang-red.init \
          "

SRCREV = "5bd1b518d06ed3addd2bee14e5094be608384319"

DEPENDS = "rebar3-native"

PV = "0.2.2"

inherit rebar3 erlang-version useradd update-rc.d systemd

# Due exerl plugin used by erlang-red, it's necessary to allow
# network access during compile phase. Because exerl will try to
# get dependencies (hex, mix, etc) when build starts.
do_compile[network] = "1"

# Avoid building QUIC for emqtt client
export BUILD_WITHOUT_QUIC = "1"

REBAR3_PROFILE = "prod"

# for erlexec, it's necessary to force the correct target include and library path
export ERL_LDFLAGS = "-L${ERL_INTERFACE_LIB_DIR}"
export ERL_CXXFLAGS = "-I${ERTS_INCLUDE_DIR} -I${ERL_INTERFACE_INCLUDE_DIR}"

INITSCRIPT_NAME = "erlang-red"
INITSCRIPT_PARAMS = "defaults"

SYSTEMD_SERVICE:${PN} = "erlang-red.service"

# erlang-red uses erlexec which requires a non-root user when running commands
USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "--system erlang-red"
USERADD_PARAM:${PN}  = "--system --create-home --home /var/lib/erlang-red -g erlang-red erlang-red"

export DIAGNOSTIC = "1"
do_install:append() {
    # remove .sh (bash) to avoid unnecessary dependency
    rm -f ${erlang_release}/lib/erlang_red-*/priv/node-red-frontend/retrieve.sh

    # Install systemd unit files
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${UNPACKDIR}/erlang-red.service ${D}${systemd_unitdir}/system
    fi

    # Install init.d
    if ${@bb.utils.contains('DISTRO_FEATURES','sysvinit','true','false',d)}; then
        install -Dm 0755 ${UNPACKDIR}/erlang-red.init ${D}/${sysconfdir}/init.d/erlang-red
    fi
}

# Until get erlexec fixed
INSANE_SKIP:${PN}-dbg += "buildpaths"
