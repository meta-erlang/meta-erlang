SUMMARY = "Breadboard Programming for Erlang inspired by Node-RED"
DESCRIPTION = "An experiment to replace Node-REDs existing NodeJS backend with \
an Erlang equivalent that is 100% compatible to existing flow code."
HOMEPAGE = "https://github.com/gorenje/erlang-red"
SECTION = "libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.ap2;md5=86d3f3a95c324c9479bd8986968f4327"

RECIPE_MAINTAINER = "João Henrique Ferreira de Freitas <joaohf@gmail.com>"

SRC_URI = "git://github.com/gorenje/erlang-red.git;protocol=https;branch=main \
           file://erlang-red.service \
           file://erlang-red.init \
          "

SRCREV = "3ffc53d1762982354d06fed513eed2dac54982fb"

DEPENDS = "rebar3-native"

PV = "0.3.4"

inherit rebar3 erlang-version useradd update-rc.d systemd

export MIX_HOME = "${UNPACKDIR}/mix"
export MIX_ARCHIVES = "${MIX_HOME}/archives"
export MIX_REBAR3 = "${UNPACKDIR}/recipe-sysroot-native/usr/bin/rebar3"

# Due exerl plugin used by erlang-red, it's necessary to allow
# network access during compile phase. Because exerl will try to
# get dependencies (hex, mix, etc) when build starts.
do_compile[network] = "1"

# Avoid building QUIC for emqtt client
export BUILD_WITHOUT_QUIC = "1"

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

FILES:${PN}-testflows = "${libdir}/erlang-red/lib/erlang_red-*/priv/testflows"

PACKAGE_BEFORE_PN = "${PN}-testflows"

# Until get erlexec fixed
INSANE_SKIP:${PN}-dbg += "buildpaths"

# Ignore buildpaths due  https://github.com/dashbitco/nimble_csv/issues/90
INSANE_SKIP:${PN} += "buildpaths"
