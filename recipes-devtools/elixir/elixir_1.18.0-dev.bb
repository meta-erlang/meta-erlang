include elixir.inc

SRCREV ?= "be84111523cc18498b68949e4c2058eacb44fa00"
PV = "1.18.0-dev+git${SRCPV}"
SRC_URI += "git://github.com/elixir-lang/elixir;branch=main;protocol=https"

PR = "r0"
