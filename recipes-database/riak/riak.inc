DESCRIPTION = "Riak is a distributed, decentralized data storage system."
HOMEPAGE = "http://www.riak.info/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

PR = "r0"

SRC_URI = " \
    git://github.com/basho/riak;branch=develop;protocol=https \
    file://0001-Use-rebar3-from-system.patch \
    file://0002-Remove-bin-bash-dependency.patch \
    file://0003-Remove-observer-from-release.patch \
    file://0004-Remove-bin-bash-dependency-for-riak-admin.patch \
    file://0005-Include-system_libs-for-release.patch \
    file://riak.init \
    file://riak.service \
"

# Patches for riak dependencies. Most of them to fix C build without 
# the pc (port compiler) pluggin.
SRC_URI += " \
    file://0001-Switch-to-shared-snappy-library-link.patch;apply=no \
    file://Makefile.c_src.canola \
    file://rebar.config.canola \
    file://rebar.config.eleveldb \
    file://Makefile.c_src.eleveldb \
    file://Makefile.c_src.lz4 \
    file://rebar.config.lz4 \
    file://Makefile.c_src.bitcask \
    file://rebar.config.bitcask \
    file://Makefile.c_src.riak_ensemble \
    file://rebar.config.riak_ensemble \
    file://Makefile.c_src.ebloom \
    file://rebar.config.ebloom"

inherit rebar3-brokensep useradd systemd update-rc.d

INITSCRIPT_NAME = "riak"
INITSCRIPT_PARAMS = "defaults"
SYSTEMD_SERVICE:${PN} = "riak.service"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "--system riak"
USERADD_PARAM:${PN}  = "--system --create-home --home /var/lib/riak -g riak riak"

S = "${WORKDIR}/git"

REBAR3_PROFILE = "rel"
REBAR3_RELEASE_NAME = "${PN}"

do_compile[network] = "1"

export ERL_COMPILER_OPTIONS="deterministic"

do_compile:prepend() {
     # ebloom
     install -D ${WORKDIR}/Makefile.c_src.ebloom ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/lib/ebloom/c_src/Makefile
     install -D ${WORKDIR}/rebar.config.ebloom ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/lib/ebloom/rebar.config    
     # bitcask
     install -D ${WORKDIR}/Makefile.c_src.bitcask ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/lib/bitcask/c_src/Makefile
     install -D ${WORKDIR}/rebar.config.bitcask ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/lib/bitcask/rebar.config
     # lz4
     install -D ${WORKDIR}/Makefile.c_src.lz4 ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/lib/lz4/c_src/Makefile
     install -D ${WORKDIR}/rebar.config.lz4 ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/lib/lz4/rebar.config
     # riak_ensemble
    install -D ${WORKDIR}/Makefile.c_src.riak_ensemble ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/lib/riak_ensemble/c_src/Makefile
    install -D ${WORKDIR}/rebar.config.riak_ensemble ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/lib/riak_ensemble/rebar.config
     # fix dependency: canola
     install -D ${WORKDIR}/Makefile.c_src.canola ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/lib/canola/c_src/Makefile
     install -D ${WORKDIR}/rebar.config.canola ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/lib/canola/rebar.config
     # fix dependency: eleveldb
     install -D ${WORKDIR}/0001-Switch-to-shared-snappy-library-link.patch ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/lib/eleveldb/c_src/0001-Switch-to-shared-snappy-library-link.patch
     install -D ${WORKDIR}/Makefile.c_src.eleveldb ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/lib/eleveldb/c_src/Makefile
     install -D ${WORKDIR}/rebar.config.eleveldb ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/lib/eleveldb/rebar.config
}

do_install:append() {
    # substitute bash to sh
    sed -i -e "s|^#!.*/bin/bash|#!/bin/sh|" ${erlang_release}/bin/hooks/builtin/wait_for_process
    
    # Install systemd unit files
    install -Dm 0644 ${WORKDIR}/riak.service ${D}${systemd_unitdir}/system/riak.service

    # Install init.d
    install -Dm 0755 ${WORKDIR}/riak.init ${D}/${sysconfdir}/init.d/riak

    install -d ${erlang_release}/log
    chown -R riak.riak ${erlang_release}/log

    chown -R riak.riak ${erlang_release}/data
    chown -R riak.riak ${erlang_release}/releases

    # TODO: check if it is really necessary
    chown -R riak.riak ${erlang_release}
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
