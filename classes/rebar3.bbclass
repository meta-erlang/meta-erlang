
inherit erlang pkgconfig

DEPENDS += "rebar3-native gawk-native"

B = "${S}"

# erlang system libs, target system was striped
INSANE_SKIP_${PN} += "already-stripped"

REBAR3_PROFILE ?= ""
REBAR3_RELEASE_NAME ?= "${@make_release_name("${PN}")}"
REBAR3_RELEASE ?= "${REBAR3_RELEASE_NAME}-${@get_erlang_release("${PV}")}"

export REBAR3_TARGET_SYSTEM = "${STAGING_LIBDIR}/erlang"
export REBAR3_TARGET_INCLUDE_ERTS = "${REBAR3_TARGET_SYSTEM}"
export REBAR3_TARGET_SYSTEM_LIBS = "${REBAR3_TARGET_SYSTEM}/lib"

export REBAR_CACHE_DIR = "${WORKDIR}/rebar3-cache"

# rebar3 new cmake
export ERTS_INCLUDE_DIR = "${STAGING_LIBDIR}/erlang/usr/include"
export ERL_INTERFACE_INCLUDE_DIR = "${STAGING_LIBDIR}/erlang/usr/include"
export ERL_INTERFACE_LIB_DIR = "${STAGING_LIBDIR}/erlang/usr/lib"

export ERLANG_ERTS = "$(erl -version 2>&1 | gawk '{print $NF}' | tr -d '\n\r')"

def get_full_profile(p):
    profiles = p.split(',')
    return "+".join(profiles)

def get_erlang_release(v):
    import re
    m = re.match("^([0-9]+)\.([0-9]+)\.([0-9]+)", v)
    if m:
        return "%s.%s.%s" % (m.group(1), m.group(2), m.group(3))
    return v

def make_release_name(v):
    return v.replace("-", "_")

rebar3_do_configure() {
    if [ "${REBAR3_PROFILE}" ]; then
        REBAR3_AS="as ${REBAR3_PROFILE}"
    fi

    rebar3 ${REBAR3_AS} get-deps
}

rebar3_do_compile() {
    if [ "${REBAR3_PROFILE}" ]; then
        REBAR3_AS="as ${REBAR3_PROFILE}"
    fi

    rebar3 ${REBAR3_AS} compile
}

PROFILE = "${@get_full_profile("${REBAR3_PROFILE}")}"

rebar3_do_install() {
    if [ "${REBAR3_PROFILE}" ]; then
        REBAR3_AS="as ${REBAR3_PROFILE}"
    fi

    rebar3 ${REBAR3_AS} tar \
        --system_libs ${REBAR3_TARGET_SYSTEM_LIBS} \
        --include-erts ${REBAR3_TARGET_INCLUDE_ERTS} \
        -n ${REBAR3_RELEASE_NAME}

    install -d ${erlang_release}

    REBAR3_RELEASE_DIR="${B}/_build/${PROFILE}/rel/${REBAR3_RELEASE_NAME}"
    ERLANG_RELEASE_FILE="${REBAR3_RELEASE_DIR}/releases/${BPN}.rel"

    tar -zxf ${REBAR3_RELEASE_DIR}/${REBAR3_RELEASE}.tar.gz -C ${erlang_release}

    # Use escript full path. We will use embedded erts so it's safe to
    # use escript from there.
    if [ -f ${erlang_release}/bin/nodetool ]; then
        sed -i -e "s|^#!.*/usr/bin/env|#! ${base_erlang_release}/erts-${ERLANG_ERTS}/bin/escript|" ${erlang_release}/bin/nodetool
    fi
    if [ -f ${erlang_release}/bin/install_upgrade.escript ]; then
        sed -i -e "s|^#!.*/usr/bin/env|#! ${base_erlang_release}/erts-${ERLANG_ERTS}/bin/escript|" ${erlang_release}/bin/install_upgrade.escript
    fi
    for i in ${erlang_release}/erts-*/bin/nodetool ${erlang_release}/erts-*/bin/install_upgrade.escript; do
        if [ -f $i ]; then
            sed -i -e "s|^#!.*/usr/bin/env|#! ${base_erlang_release}/erts-${ERLANG_ERTS}/bin/escript|" $i
        fi
    done

    # remove any .src file
    for i in ${erlang_release}/erts-*/bin/*.src; do
        rm -f $i
    done

    chown root:root -R ${erlang_release}
}

EXPORT_FUNCTIONS do_configure do_compile do_install
