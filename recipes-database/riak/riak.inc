DESCRIPTION = "Riak is a distributed, decentralized data storage system."
HOMEPAGE = "http://www.riak.info/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

SRC_URI = " \
    git://github.com/basho/riak;branch=develop-3.0;protocol=https \
    file://Makefile.c_src.canola \
    file://rebar.config.canola \
    file://Makefile.c_src.syslog \
    file://rebar.config.syslog \
    file://0001-Fix-release-number.patch \
    file://0001-Remove-bin-bash-dependency.patch \
    file://rebar.config.eleveldb \
    file://Makefile.c_src.eleveldb \
    file://Makefile.c_src.lz4 \
    file://rebar.config.lz4 \
    file://Makefile.c_src.bitcask \
    file://rebar.config.bitcask \
    file://Makefile.c_src.riak_ensemble \
    file://rebar.config.riak_ensemble \
    file://Makefile.c_src.ebloom \
    file://rebar.config.ebloom \
    file://build_deps.sh.eleveldb \
    file://0001-Switch-to-shared-snappy-library-link.patch;apply=no \
    file://riak.init \
    file://riak.service \
"

inherit rebar3 useradd systemd update-rc.d

INITSCRIPT_NAME = "riak"
INITSCRIPT_PARAMS = "defaults"

SYSTEMD_SERVICE_${PN} = "riak.service"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "--system riak"
USERADD_PARAM_${PN}  = "--system --create-home --home /var/lib/riak -g riak riak"

S = "${WORKDIR}/git"

REBAR3_PROFILE = "rel"
REBAR3_RELEASE_NAME = "riak"

do_compile_prepend() {
    # ebloom
    install -D ${WORKDIR}/Makefile.c_src.ebloom ${S}/_build/${REBAR3_PROFILE}/lib/ebloom/c_src/Makefile
    install -D ${WORKDIR}/rebar.config.ebloom ${S}/_build/${REBAR3_PROFILE}/lib/ebloom/rebar.config    
    # bitcask
    install -D ${WORKDIR}/Makefile.c_src.bitcask ${S}/_build/${REBAR3_PROFILE}/lib/bitcask/c_src/Makefile
    install -D ${WORKDIR}/rebar.config.bitcask ${S}/_build/${REBAR3_PROFILE}/lib/bitcask/rebar.config
    # lz4
    install -D ${WORKDIR}/Makefile.c_src.lz4 ${S}/_build/${REBAR3_PROFILE}/lib/lz4/c_src/Makefile
    install -D ${WORKDIR}/rebar.config.lz4 ${S}/_build/${REBAR3_PROFILE}/lib/lz4/rebar.config
    # riak_ensemble
    install -D ${WORKDIR}/Makefile.c_src.riak_ensemble ${S}/_build/${REBAR3_PROFILE}/lib/riak_ensemble/c_src/Makefile
    install -D ${WORKDIR}/rebar.config.riak_ensemble ${S}/_build/${REBAR3_PROFILE}/lib/riak_ensemble/rebar.config
    # fix dependency: canola
    install -D ${WORKDIR}/Makefile.c_src.canola ${S}/_build/${REBAR3_PROFILE}/lib/canola/c_src/Makefile
    install -D ${WORKDIR}/rebar.config.canola ${S}/_build/${REBAR3_PROFILE}/lib/canola/rebar.config
    # fix dependency: syslog
    install -D ${WORKDIR}/Makefile.c_src.syslog ${S}/_build/${REBAR3_PROFILE}/lib/syslog/c_src/Makefile
    install -D ${WORKDIR}/rebar.config.syslog ${S}/_build/${REBAR3_PROFILE}/lib/syslog/rebar.config
    # fix dependency: eleveldb
    install -D ${WORKDIR}/0001-Switch-to-shared-snappy-library-link.patch ${S}/_build/${REBAR3_PROFILE}/lib/eleveldb/c_src/0001-Switch-to-shared-snappy-library-link.patch
    install -D ${WORKDIR}/build_deps.sh.eleveldb ${S}/_build/${REBAR3_PROFILE}/lib/eleveldb/c_src/build_deps.sh
    install -D ${WORKDIR}/Makefile.c_src.eleveldb ${S}/_build/${REBAR3_PROFILE}/lib/eleveldb/c_src/Makefile
    install -D ${WORKDIR}/rebar.config.eleveldb ${S}/_build/${REBAR3_PROFILE}/lib/eleveldb/rebar.config
}

do_install_append() {
    # substitute bash to sh
    sed -i -e "s|^#!.*/bin/bash|#!/bin/sh|" ${erlang_release}/bin/hooks/builtin/wait_for_process
    
    # use od with busybox options
    sed -i -e "s@    od -t x.*@    dd count=1 bs=4 if=/dev/urandom 2> /dev/null | od -x  | head -n1 | awk '{print \$2\$3}'@" ${erlang_release}/bin/riak

    # Install systemd unit files
    install -Dm 0644 ${WORKDIR}/riak.service ${D}${systemd_unitdir}/system/riak.service

    # Install init.d
    install -Dm 0755 ${WORKDIR}/riak.init ${D}/${sysconfdir}/init.d/riak

    install -d ${erlang_release}/log
    chown -R riak.riak ${erlang_release}/log

    chown -R riak.riak ${erlang_release}/data
    chown -R riak.riak ${erlang_release}/releases
}

DEPENDS += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'pam', 'libpam', '', d)} \
    openssl \
    snappy \
"

FILES_${PN} += " \
    /etc \
    /var/lib/riak \
"