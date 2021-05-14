# Export MIX_REBAR3 environment variable, which forces the use of
# meta-erlang rebar3 version

inherit mix

DEPENDS += "rebar3-native"

export MIX_REBAR3 ?= "${WORKDIR}/recipe-sysroot-native/usr/bin/rebar3"
