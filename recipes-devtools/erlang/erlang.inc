DESCRIPTION = "A dynamic programming language and runtime environment, \
with built-in support for concurrency, distribution and fault tolerance"
SECTION = "devel/erlang"
DEPENDS = "ncurses zlib"
PRIORITY = "optional"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ff253ad767462c46be284da12dda33e8"

SRC_URI = "https://github.com/erlang/otp/archive/OTP-${PV}.tar.gz"
SRC_URI += "file://fix-wx-configure.patch"
SRC_URI += "file://erlang-fix-build-issue-in-Yocto.patch"

S = "${WORKDIR}/otp-OTP-${PV}"

PARALLEL_MAKE = ""

inherit autotools-brokensep

do_install_append() {
	# remove erlang code that does not need to be loaded by the erlang runtime
	rm -rf ${D}/${libdir}/erlang/lib/erts-${ERTS_VERSION}/ebin
}
