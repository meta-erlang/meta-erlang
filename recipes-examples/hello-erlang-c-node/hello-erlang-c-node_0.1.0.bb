SUMMARY = "Erlang C Node to run Lua scripts"
SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4b3b72801a2e72fc18b3e6202f72254e"

S = "${WORKDIR}/git"

SRCREV = "92cf2d50fd97d6bcc84043f8ad3d267e82ac7cba"
PV = "0.1.0+git${SRCPV}"
SRC_URI = "git://github.com/joaohf/erlang-lua;branch=master;protocol=https"

inherit rebar3 pkgconfig

DEPENDS += "erlang lua"

REBAR3_RELEASE_NAME = "erlang_lua"
REBAR3_PROFILE = "prod"
REBAR3_RELEASE = "${REBAR3_RELEASE_NAME}-0.1.0"
