SUMMARY = "A minimal http server written in Nitrogen"
SECTION = "examples"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4072a65404e353b02008ca87f23e6002"

S = "${UNPACKDIR}/${BPN}"

SRCREV = "af243edf45ac88d370510541aafec32d832b97b8"
PV = "0.1.0+git${SRCPV}"
SRC_URI = "git://github.com/meta-erlang/hello-world;branch=master;subpath=${BPN};protocol=https \
           file://Makefile.c_src.bcrypt"

inherit rebar3

REBAR3_PROFILE = "prod"
REBAR3_RELEASE = "${REBAR3_RELEASE_NAME}-0.1.0"

RDEPENDS:${PN} += "erlang-erl-interface"

DEBUG_PREFIX_MAP += "\
    -ffile-prefix-map=${REBAR_BASE_DIR}/${REBAR3_PROFILE}/lib/bcrypt/c_src=${TARGET_DBGSRC_DIR} \
    "

# This is how we can `patch` files that were fetched by rebar3_do_configure()
do_compile:prepend() {
    install -D ${UNPACKDIR}/Makefile.c_src.bcrypt ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/lib/bcrypt/c_src/Makefile
}
