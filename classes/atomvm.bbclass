
DEPENDS += "erlang-native rebar3-native"

RDEPENDS:${PN} += "atomvm"

RCONFLICTS:${PN} += "atomvm-examples"

B ?= "${WORKDIR}/build"

# support REBAR_BASE_DIR for setting the base_dir
# https://github.com/erlang/rebar3/commit/9402857f5527e300bf28b7e3744bef3fc88f3379
export REBAR_BASE_DIR = "${B}"

# erlang system libs, target system was striped
INSANE_SKIP:${PN} += "already-stripped"

do_configure[network] = "1"

export ERL_COMPILER_OPTIONS="deterministic"

REBAR3_PROFILE ?= "default"

export REBAR_CACHE_DIR = "${UNPACKDIR}/rebar3-cache"

atomvm_do_configure() {
    # This is needed since the default is to be in ${B}. In fact all the task
    # which invoke rebar3 need its.
    cd ${S}
    if [ "${REBAR3_PROFILE}" ]; then
        REBAR3_AS="as ${REBAR3_PROFILE}"
    fi

    rebar3 ${REBAR3_AS} get-deps
}

atomvm_do_compile() {
    cd ${S}
    if [ "${REBAR3_PROFILE}" ]; then
        REBAR3_AS="as ${REBAR3_PROFILE}"
    fi

    rebar3 ${REBAR3_AS} packbeam -p -f -i
}

atomvm_do_install() {
    cd ${S}
    if [ "${REBAR3_PROFILE}" ]; then
        REBAR3_AS="as ${REBAR3_PROFILE}"
    fi

    install -d ${D}/${datadir}/${BPN}
    install -m 0644 ${B}/${REBAR3_PROFILE}/lib/*.avm ${D}/${datadir}/${BPN}
}

EXPORT_FUNCTIONS do_configure do_compile do_install
