
inherit erlang

DEPENDS += "elixir-native"

B = "${S}"

MIX_ENV ?= "prod"

# erlang system libs, target system was striped
INSANE_SKIP:${PN} += "already-stripped"

do_configure[network] = "1"

MIX_RELEASE_NAME="${@get_release_name("${PN}")}"
MIX_RELEASE_VERSION="${@get_release_version("${PV}")}"
MIX_RELEASE_DIR="${B}/_build/${MIX_ENV}"

export MIX_HOME = "${WORKDIR}/mix"
export MIX_ARCHIVES = "${MIX_HOME}/archives"

ERLANG_ERTS_VERSION = "$(erl -version 2>&1 | gawk '{print $NF}' | tr -d '\n\r')"

def get_release_name(pn):
    pn = pn.split("-")
    return "_".join(pn)

def get_release_version(pv):
    return pv.split("-")[0]

mix_do_configure() {
    # fetch rebar if the user has not called mix-rebar3.bbclass
    if [ -z "$MIX_REBAR3" ]; then
        mix local.rebar --force
    fi
    mix local.hex --force
}

mix_do_compile() {
    mix deps.get
    MIX_ENV=${MIX_ENV} mix compile
}

mix_do_install() {
    install -d ${erlang_release}

    MIX_TARGET_INCLUDE_ERTS="${STAGING_LIBDIR}/erlang/erts-${ERLANG_ERTS_VERSION}" \
    MIX_ENV=${MIX_ENV} mix release --overwrite --path ${erlang_release}

    chown root:root -R ${erlang_release}    
}

EXPORT_FUNCTIONS do_configure do_compile do_install
