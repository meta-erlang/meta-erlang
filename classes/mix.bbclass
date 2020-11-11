
inherit erlang

DEPENDS += " rebar3-native elixir-native "

B = "${S}"

MIX_ENV ?= "prod"

# erlang system libs, target system was striped
INSANE_SKIP_${PN} += "already-stripped"

MIX_RELEASE_NAME="${@get_release_name("${PN}")}"
MIX_RELEASE_VERSION="${@get_release_version("${PV}")}"
MIX_RELEASE_DIR="${B}/_build/${MIX_ENV}"

export MIX_REBAR3="${WORKDIR}/recipe-sysroot-native/usr/bin/rebar3"

export MIX_TARGET_INCLUDE_ERTS = "${STAGING_LIBDIR}/erlang"

def get_release_name(pn):
    pn = pn.split("-")
    return "_".join(pn)

def get_release_version(pv):
    return pv.split("-")[0]

mix_do_configure() {
    mix local.hex --force
    mix deps.get
}

mix_do_compile() {
    MIX_ENV=${MIX_ENV} mix compile
}

mix_do_install() {
    MIX_ENV=${MIX_ENV} mix release --overwrite

    install -d ${erlang_release}

    tar -zxf ${MIX_RELEASE_DIR}/${MIX_RELEASE_NAME}-${MIX_RELEASE_VERSION}.tar.gz -C ${erlang_release}

    chown root:root -R ${erlang_release}    
}

EXPORT_FUNCTIONS do_configure do_compile do_install
