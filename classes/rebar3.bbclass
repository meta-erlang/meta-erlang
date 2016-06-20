
inherit erlang

DEPENDS += "rebar3-native"

B = "${S}"

# erlang system libs, target system was striped
INSANE_SKIP_${PN} += "already-stripped"

REBAR3_PROFILE ?= ""
REBAR3_RELEASE_NAME ?= "${BPN}-${@get_erlang_release("${PV}")}"

export REBAR3_TARGET_INCLUDE_ERTS = "${STAGING_LIBDIR}/erlang"
export REBAR3_TARGET_SYSTEM_LIBS = "${STAGING_LIBDIR}/erlang"

export ERLANG_ERTS = "${@get_erlang_erts("${REBAR3_TARGET_SYSTEM_LIBS}")}"

def get_full_profile(p):
    profiles = p.split(',')
    return "+".join(profiles)

def get_erlang_release(v):
    import re
    m = re.match("^([0-9]+)\.([0-9]+)\.([0-9]+)", v)
    return "%s.%s.%s" % (m.group(1), m.group(2), m.group(3))

def get_erlang_erts(systemlibspath):
   ldir = os.listdir(systemlibspath)
   for dir in ldir:
       if dir.startswith("erts"):
           return dir.split('-')[1]
   return ""

rebar3_do_compile() {
    rebar3 compile
}

PROFILE = "${@get_full_profile("${REBAR3_PROFILE}")}"

rebar3_do_install() {
    if [ "${REBAR3_PROFILE}" ]; then
        REBAR3_AS="as ${REBAR3_PROFILE}"
    fi

    rebar3 ${REBAR3_AS} release tar

    install -d ${erlang_release}

    REBAR3_RELEASE_DIR="${B}/_build/${PROFILE}/rel/${BPN}"
    ERLANG_RELEASE_FILE="${REBAR3_RELEASE_DIR}/releases/${BPN}.rel"

    tar -zxf ${REBAR3_RELEASE_DIR}/${REBAR3_RELEASE_NAME}.tar.gz -C ${erlang_release}

    # Use escript full path. We will use embedded erts so it's safe to
    # use escript from there.
    sed -i -e "s|^#!.*/usr/bin/env|#! ${base_erlang_release}/erts-${ERLANG_ERTS}/bin/escript|" ${erlang_release}/bin/nodetool
    sed -i -e "s|^#!.*/usr/bin/env|#! ${base_erlang_release}/erts-${ERLANG_ERTS}/bin/escript|" ${erlang_release}/bin/install_upgrade.escript
    for i in ${erlang_release}/erts-*/bin/nodetool ${erlang_release}/erts-*/bin/install_upgrade.escript; do
        sed -i -e "s|^#!.*/usr/bin/env|#! ${base_erlang_release}/erts-${ERLANG_ERTS}/bin/escript|" $i
    done

    chown root:root -R ${erlang_release}
}

EXPORT_FUNCTIONS do_compile do_install
