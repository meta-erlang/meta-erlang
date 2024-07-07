include elixir.inc

SRCREV ?= "18bbfed45ef2698066436adae00ddf877644b55d"
PV = "1.18.0-dev+git${SRCPV}"
SRC_URI += "git://github.com/elixir-lang/elixir;branch=main;protocol=https"

PR = "r0"
