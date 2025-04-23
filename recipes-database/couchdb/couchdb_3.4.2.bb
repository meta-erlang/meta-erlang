DESCRIPTION = "CouchDB is a database that completely embraces the web. Store your data with JSON documents. \
               Access your documents with your web browser, via HTTP. Query, combine, and transform your documents \
               with JavaScript. CouchDB works well with modern web and mobile apps. You can distribute your data, \
               efficiently using CouchDB’s incremental replication. CouchDB supports master-master setups with automatic \
               conflict detection."
SUMMARY = "A document-oriented NoSQL database"
HOMEPAGE = "https://couchdb.apache.org/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5b7d1bad90e7a0de4d68a35df59116ca"

SRC_URI = "git://github.com/apache/couchdb;protocol=https;branch=3.4.x \
    file://0001-Fix-cross-compilation-environment-variables-and-use-.patch \
    file://couchdb.service \
    file://couchdb.init \
"

PV = "3.4.2+git${SRCPV}"
SRCREV = "9295a0aa9ca01ad2254b0c5e245008749c8fcf64"

S = "${WORKDIR}/git"

PR = "r0"

inherit autotools-brokensep pkgconfig erlang systemd update-rc.d useradd

CONFIGUREOPTS = "--js-engine=quickjs --disable-spidermonkey --disable-docs \
    --disable-fauxton \
    --rebar ${STAGING_BINDIR_NATIVE}/rebar \
    --rebar3 ${STAGING_BINDIR_NATIVE}/rebar3 \
    --erlfmt ${STAGING_BINDIR_NATIVE}/erlfmt \
"
EXTRA_OECONF:remove = "--disable-static"

export REBAR = "${STAGING_BINDIR_NATIVE}/rebar"
export REBAR3 = "${STAGING_BINDIR_NATIVE}/rebar3"
export ERLFMT = "${STAGING_BINDIR_NATIVE}/erlfmt"

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

export ERL_COMPILER_OPTIONS = "deterministic"

do_configure[network] = "1"

do_configure:append:class-target() {
    sed -i "s:-L/usr/local/lib:\$LDFLAGS:g" ${B}/src/couch/rebar.config.script

    # include target erts
    sed -i  "/{sys/ a {root_dir, \"${STAGING_LIBDIR}/erlang\"}," ${B}/rel/reltool.config
    # exclude development static libraries
    sed -i 's:"^erts.*/bin/(dialyzer|typer)":"^erts.*/bin/(dialyzer|typer)", "^erts.*/lib":g' ${B}/rel/reltool.config
}

do_install:append:class-target() {
    install -d ${D}/opt
    cp -r ${B}/rel/couchdb ${D}/opt/
    chown -R couchdb:couchdb ${D}/opt/couchdb

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
        install -m 0644 ${UNPACKDIR}/couchdb.service ${D}${systemd_unitdir}/system
        sed -i -e 's,%bindir%,/opt/couchdb/bin,g' \
	       ${D}${systemd_unitdir}/system/couchdb.service
    fi

    # Install init.d
    if ${@bb.utils.contains('DISTRO_FEATURES','sysvinit','true','false',d)}; then
        install -Dm 0755 ${UNPACKDIR}/couchdb.init ${D}/${sysconfdir}/init.d/couchdb
    fi

    # Cleanup, couchdb build is leaking something else
    find ${D}/opt -type f -name "couch_ejson_compare.[d,o]" -delete
}

do_configure:class-native() {
	:
}

do_compile:class-native() {
	oe_runmake -C ${S}/src/couch_quickjs/quickjs qjsc
}

do_install:class-native() {
    install -d ${D}/${bindir}
    install -m755 ${S}/src/couch_quickjs/quickjs/qjsc ${D}/${bindir}
}

FILES:${PN} += " \
    /etc \
    /opt/couchdb \
    /var/lib/couchdb \
"

DEPENDS:class-target += " \
    curl \
    erlang \
    erlang-native \
    icu \
    openssl \
    rebar-native \
    rebar3-native \
    erlfmt-native \
    couchdb-native \
"

INSANE_SKIP:${PN} = "already-stripped"

BBCLASSEXTEND = "native"