SUMMARY = "ODBC library for Unix"
DESCRIPTION = "UnixODBC is an implementation of the Open DataBase Connectivity standard, \
a database abstraction layer that allows applications to be used with \
many different relational databases by way of a single library."
SECTION = "libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7b37bf80a3df5a65b355433ae36d206"

SRC_URI = "ftp://ftp.unixodbc.org/pub/unixODBC/unixODBC-2.3.4.tar.gz "
SRC_URI[md5sum] = "bd25d261ca1808c947cb687e2034be81"
SRC_URI[sha256sum] = "2e1509a96bb18d248bf08ead0d74804957304ff7c6f8b2e5965309c632421e39"

DEPENDS = "libtool readline"

inherit autotools-brokensep

S = "${WORKDIR}/unixODBC-${PV}" 

EXTRA_OECONF += "\
                 --disable-gui \
                "

do_configure() {
    make -f Makefile.svn
    oe_runconf
}

do_compile_prepend() {
    mkdir -p ${S}/libltdl
    cp ${STAGING_LIBDIR}/libltdl* ${S}/libltdl
}

BBCLASSEXTEND = "native"

