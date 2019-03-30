require rebar3.inc

DEFAULT_PREFERENCE = "-1"
PV = "git${SRCPV}"

SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/erlang/rebar3;branch=master"
S = "${WORKDIR}/git"