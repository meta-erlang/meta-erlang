DESCRIPTION = "Riak is a distributed, decentralized data storage system."
HOMEPAGE = "https://openriak.org/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

PR = "r0"

SRCREV = "d010c3d906d245488ef4a13ca9da89a1186f505a"

SRC_URI = " \
    git://github.com/OpenRiak/riak;branch=openriak-3.2;protocol=https \
    file://0002-Remove-bin-bash-dependency.patch \
    file://0003-Remove-observer-from-release.patch \
    file://0004-Remove-bin-bash-dependency-for-riak-admin.patch \
    file://0005-Include-system_libs-for-release.patch \
    file://riak.init \
    file://riak.service \
    file://99-riak-sysctl.conf \
"

# Patches for riak dependencies.
SRC_URI += " \
    file://0001-Switch-to-shared-snappy-library-link.patch;apply=no \
    file://rebar.config.eleveldb \
    file://Makefile.c_src.eleveldb"

inherit rebar3-brokensep useradd systemd update-rc.d features_check

REQUIRED_DISTRO_FEATURES = "pam"

INITSCRIPT_NAME = "riak"
INITSCRIPT_PARAMS = "defaults"
SYSTEMD_SERVICE:${PN} = "riak.service"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "--system riak"
USERADD_PARAM:${PN}  = "--system --create-home --home /var/lib/riak -g riak riak"

REBAR3_PROFILE = "rel"
REBAR3_RELEASE_NAME = "${PN}"

do_compile[network] = "1"

export ERL_COMPILER_OPTIONS = "deterministic"

do_compile:prepend() {
     install -D ${UNPACKDIR}/0001-Switch-to-shared-snappy-library-link.patch \
        ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/lib/eleveldb/c_src/0001-Switch-to-shared-snappy-library-link.patch
     install -D ${UNPACKDIR}/Makefile.c_src.eleveldb ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/lib/eleveldb/c_src/Makefile
     install -D ${UNPACKDIR}/rebar.config.eleveldb ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/lib/eleveldb/rebar.config
}

do_install:append() {
    # substitute bash to sh
    sed -i -e "s|^#!.*/bin/bash|#!/bin/sh|" ${erlang_release}/bin/hooks/builtin/wait_for_process
    
    # Install systemd unit files
    install -Dm 0644 ${UNPACKDIR}/riak.service ${D}${systemd_unitdir}/system/riak.service

    # Install init.d
    install -Dm 0755 ${UNPACKDIR}/riak.init ${D}/${sysconfdir}/init.d/riak

    install -d ${erlang_release}/log
    chown -R riak.riak ${erlang_release}/log

    chown -R riak.riak ${erlang_release}/data
    chown -R riak.riak ${erlang_release}/releases

    # TODO: check if it is really necessary
    chown -R riak.riak ${erlang_release}

    # Remove nonsense priv/zstd/lib folder
    rm -rf ${erlang_release}/lib/zstd-*/priv/zstd

    # riak needs some additional configuration for sysctl values
    install -d ${D}${sysconfdir}/sysctl.d/
    install -m 0644 ${UNPACKDIR}/99-riak-sysctl.conf ${D}${sysconfdir}/sysctl.d/
}

DEPENDS += " \
    libpam \
    openssl \
    snappy \
"

FILES:${PN} += " \
    /etc \
    /var/lib/riak \
"
