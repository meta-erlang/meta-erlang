require emqx.inc

SRCREV = "7b860cf9ece2406e5e92f46755d307e50d5a8214"
PV = "4.0.0"
PR = "r0"

SRC_URI += "\
    git://github.com/emqx/emqx-rel;branch=release-4.0 \
    "

REBAR3_PROFILE = "emqx"
REBAR3_RELEASE = "emqx-v4.0-beta.4"
