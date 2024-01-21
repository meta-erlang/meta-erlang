DESCRIPTION = "A document-oriented NoSQL database"
HOMEPAGE = "https://couchdb.apache.org/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1d6953f3b9af3e202ed075fc3019b801"

SRC_URI = " \
    ${APACHE_MIRROR}/${BPN}/source/${PV}/apache-couchdb-${PV}.tar.gz \
    file://couchdb.service \
    file://couchdb.init \
    file://0001-Remove-defensive-check-for-SpiderMonkey.patch \
"

PR = "r0"

inherit autotools-brokensep pkgconfig erlang systemd update-rc.d useradd

SPIDERMONKEY_VERSION ?= "91"

CONFIGUREOPTS = "--spidermonkey-version ${SPIDERMONKEY_VERSION} --disable-docs"
EXTRA_OECONF:remove = "--disable-static"

ERL_INTERFACE_VERSION = "`pkg-config --modversion erl_ei`"
export ERL_CFLAGS = "`pkg-config --cflags-only-I erl_ei` -I${STAGING_LIBDIR}/erlang/usr/include"
export ERL_EI_LIBDIR = "${STAGING_LIBDIR}/erlang/lib/erl_interface-${ERL_INTERFACE_VERSION}/lib/"

LDFLAGS_ += "-L${STAGING_LIBDIR}/erlang/lib/erl_interface-${ERL_INTERFACE_VERSION}/lib"

INITSCRIPT_NAME = "couchdb"
INITSCRIPT_PARAMS = "defaults"

SYSTEMD_SERVICE:${PN} = "couchdb.service"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "--system couchdb"
USERADD_PARAM:${PN}  = "--system --create-home --home /var/lib/couchdb -g couchdb couchdb"

S = "${WORKDIR}/apache-couchdb-${PV}"

do_configure:append() {
    sed -i "s:-I/usr/include/mozjs-${SPIDERMONKEY_VERSION}:-I${STAGING_INCDIR}/mozjs-${SPIDERMONKEY_VERSION}:g" ${B}/src/couch/rebar.config.script
    sed -i "s:-L/usr/local/lib:\$LDFLAGS:g" ${B}/src/couch/rebar.config.script

    # include target erts
    sed -i  "/{sys/ a {root_dir, \"${STAGING_LIBDIR}/erlang\"}," ${B}/rel/reltool.config
    # exclude development static libraries
    sed -i 's:"^erts.*/bin/(dialyzer|typer)":"^erts.*/bin/(dialyzer|typer)", "^erts.*/lib":g' ${B}/rel/reltool.config
}

do_install:append() {
    install -d ${D}/opt
    cp -r ${B}/rel/couchdb ${D}/opt/
    chown -R couchdb.couchdb ${D}/opt/couchdb

    # Remove tmp build path
    sed -i -e "s,${B}/rel/couchdb,/opt/couchdb,g" \
        ${D}/opt/couchdb/releases/RELEASES

    # Cleanup installed filed (there are some build output files there)
    rm -rf ${D}/opt/couchdb/lib/couch-${PV}/priv/couch_js
    rm -rf ${D}/opt/couchdb/lib/couch-${PV}/priv/couch_ejson_compare

    install -d ${D}/var/lib/couchdb

    # Install systemd unit files
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/couchdb.service ${D}${systemd_unitdir}/system
        sed -i -e 's,%bindir%,/opt/couchdb/bin,g' \
	       ${D}${systemd_unitdir}/system/couchdb.service
    fi

    # Install init.d
    if ${@bb.utils.contains('DISTRO_FEATURES','sysvinit','true','false',d)}; then
        install -Dm 0755 ${WORKDIR}/couchdb.init ${D}/${sysconfdir}/init.d/couchdb
    fi
}

FILES:${PN} += " \
    /etc \
    /opt/couchdb \
    /var/lib/couchdb \
"

DEPENDS += " \
    curl \
    erlang \
    erlang-native \
    icu \
    mozjs-91 \
    openssl \
    rebar-native \
"

INSANE_SKIP:${PN} = "already-stripped"
