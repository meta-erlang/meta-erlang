require emqx.inc

SRCREV = "f45e876816a0b4c90cabacd8ac43a7df83dc0e37"
PV = "3.4.0"
PR = "r0"

SRC_URI += "\
    git://github.com/emqx/emqx-rel;branch=release-3.2 \
    "

REBAR3_PROFILE = "emqx"
REBAR3_RELEASE = "emqx-v4.0-alpha.2"
