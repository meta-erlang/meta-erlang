require erlang-${PV}-manifest.inc
include erlang.inc

PR = "r0"

# the 32-bit patches were not added for sanitizing
SRC_URI += "file://0001-Use-autoconf-2.73.patch \
            file://0001-Skip-odbc-build-when-without-odbc-is-requested-by-us.patch \
            "

SRCREV = "550d7b7898706c7822362c42e7b93120c1d1f29a"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/29.0:"

# for version 29.0 you will need this:
#your-layer/recipes-devtools/erlang/erlang_%.bbappend
#do_install:append() {
#    rm -rf ${D}${libdir}/erlang/lib/odbc-*
#}

# add this to your mydistro.conf or local.conf
#ERLANG_VERSION = "29.0"
#ELIXIR_VERSION = "1.19.5"
#BBMASK:append = " ${@'/erlang_(?!' + d.getVar('ERLANG_VERSION').replace('.', '\.') + ')[^/]*\.bb$'}"
#BBMASK:append = " ${@'/elixir_(?!' + d.getVar('ELIXIR_VERSION').replace('.', '\.') + ')[^/]*\.bb$'}"
# for gleam, cf. README.md
