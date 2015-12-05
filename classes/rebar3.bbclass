
inherit erlang

DEPENDS += "rebar3-native"

B = "${S}"

# erlang system libs, target system was striped
INSANE_SKIP_${PN} += "already-stripped"

REBAR3_PROFILE ?= ""
REBAR3_RELEASE_NAME ?= "${BPN}-${@get_erlang_release("${PV}")}"

export REBAR3_TARGET_INCLUDE_ERTS = "${STAGING_LIBDIR}/erlang"
export REBAR3_TARGET_SYSTEM_LIBS = "${STAGING_LIBDIR}/erlang"

def get_full_profile(p):
    profiles = p.split(',')
    return "+".join(profiles)

def get_erlang_release(v):
    import re
    m = re.match("^([0-9]+)\.([0-9]+)\.([0-9]+)", v)
    return "%s.%s.%s" % (m.group(1), m.group(2), m.group(3))

rebar3_do_compile() {
    rebar3 compile
}

rebar3_do_install() {
    if [ "${REBAR3_PROFILE}" ]; then
        REBAR3_AS="as ${REBAR3_PROFILE}"
    fi

    rebar3 ${REBAR3_AS} release tar

    install -d ${erlang_release}

    REBAR3_RELEASE_DIR="${B}/_build/${@get_full_profile("${REBAR3_PROFILE}")}/rel/${BPN}"
    tar -zxf ${REBAR3_RELEASE_DIR}/${REBAR3_RELEASE_NAME}.tar.gz -C ${erlang_release}
}

EXPORT_FUNCTIONS do_compile do_install
