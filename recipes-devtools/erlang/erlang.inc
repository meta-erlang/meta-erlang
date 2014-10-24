DESCRIPTION = "A dynamic programming language and runtime environment, \
with built-in support for concurrency, distribution and fault tolerance"
SECTION = "devel/erlang"
DEPENDS = "ncurses zlib"
PRIORITY = "optional"
LICENSE = "ErlPL-1.1"
LIC_FILES_CHKSUM = "file://EPLICENCE;md5=09f9063ea35bc5bd124df2fda1d9d2c7"

SRC_URI = "http://www.erlang.org/download/otp_src_${PV}.tar.gz"
SRC_URI += "file://fix-wx-configure.patch"
SRC_URI += "file://erlang-fix-build-issue-in-Yocto.patch"

S = "${WORKDIR}/otp_src_${PV}"

PARALLEL_MAKE = ""

inherit autotools-brokensep
