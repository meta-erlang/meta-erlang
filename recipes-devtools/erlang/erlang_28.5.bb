require erlang-${PV}-manifest.inc
include erlang.inc

PR = "r0"

# the 32-bit patches were not added for sanitizing
SRC_URI += "file://0001-Use-autoconf-2.73.patch \
            "

SRCREV = "f4506ee46d68694a1d23ca81c314092fd83e8f85"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/28.5:"

# add this to your mydistro.conf or local.conf
#ERLANG_VERSION = "28.5"
#ELIXIR_VERSION = "1.19.5"
#BBMASK:append = " ${@'/erlang_(?!' + d.getVar('ERLANG_VERSION').replace('.', '\.') + ')[^/]*\.bb$'}"
#BBMASK:append = " ${@'/elixir_(?!' + d.getVar('ELIXIR_VERSION').replace('.', '\.') + ')[^/]*\.bb$'}"
# for gleam, cf. README.md
