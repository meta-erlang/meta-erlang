DESCRIPTION = "A document-oriented NoSQL database"
HOMEPAGE = "https://couchdb.apache.org/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1d6953f3b9af3e202ed075fc3019b801"

SRC_URI = " \
    https://downloads.apache.org/couchdb/source/${PV}/apache-couchdb-${PV}.tar.gz \
    file://couchdb.service \
    file://couchdb.init \
"

PR = "r1"

inherit autotools-brokensep erlang systemd update-rc.d useradd

# couchdb supports: 1.8.5, 60, 68
SPIDERMONKEY_VERSION ?= "60"

CONFIGUREOPTS = "--with-curl --spidermonkey-version ${SPIDERMONKEY_VERSION} --disable-docs"
EXTRA_OECONF_remove = "--disable-static"

ERL_INTERFACE_VERSION = "`pkg-config --modversion erl_ei`"
export ERL_CFLAGS = "`pkg-config --cflags-only-I erl_ei` -I${STAGING_LIBDIR}/erlang/usr/include"
export ERL_EI_LIBDIR = "${STAGING_LIBDIR}/erlang/lib/erl_interface-${ERL_INTERFACE_VERSION}/lib/"

LDFLAGS_ += "-L${STAGING_LIBDIR}/erlang/lib/erl_interface-${ERL_INTERFACE_VERSION}/lib"

INITSCRIPT_NAME = "couchdb"
INITSCRIPT_PARAMS = "defaults"

SYSTEMD_SERVICE_${PN} = "couchdb.service"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "--system couchdb"
USERADD_PARAM_${PN}  = "--system --create-home --home /var/lib/couchdb -g couchdb couchdb"

S = "${WORKDIR}/apache-couchdb-${PV}"

do_configure_append() {
    sed -i "s:-I/usr/include/mozjs-${SPIDERMONKEY_VERSION}:-I${STAGING_INCDIR}/mozjs-${SPIDERMONKEY_VERSION}:g" ${B}/src/couch/rebar.config.script
    sed -i "s:-L/usr/local/lib:\$LDFLAGS:g" ${B}/src/couch/rebar.config.script

    # include target erts
    sed -i  "/{sys/ a {root_dir, \"${STAGING_LIBDIR}/erlang\"}," ${B}/rel/reltool.config
    # exclude development static libraries
    sed -i 's:"^erts.*/bin/(dialyzer|typer)":"^erts.*/bin/(dialyzer|typer)", "^erts.*/lib":g' ${B}/rel/reltool.config
}

do_install_append() {
    install -d ${D}/opt
    cp -r ${B}/rel/couchdb ${D}/opt/
    chown -R couchdb.couchdb ${D}/opt/couchdb

    install -d ${D}/var/lib/couchdb
    install -d ${D}/var/volatile/log/couchdb

    # Install systemd unit files
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/couchdb.service ${D}${systemd_unitdir}/system
    sed -i -e 's,%bindir%,/opt/couchdb/bin,g' \
	       ${D}${systemd_unitdir}/system/couchdb.service

    # Install init.d
    install -Dm 0755 ${WORKDIR}/couchdb.init ${D}/${sysconfdir}/init.d/couchdb
}

FILES_${PN} += " \
    /etc \
    /opt/couchdb \
    /var/lib/couchdb \
    /var/volatile/log/couchdb \
"

DEPENDS += " \
    curl \
    erlang \
    erlang-native \
    icu \
    mozjs \
    openssl \
    rebar-native \
"

INSANE_SKIP_${PN} = "already-stripped"
