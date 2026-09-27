SUMMARY = "A friendly language for building type-safe, scalable systems!"
DESCRIPTION = "Gleam is a type safe and scalable language for the Erlang virtual machine and JavaScript runtimes."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENCE;md5=86d3f3a95c324c9479bd8986968f4327"

RECIPE_MAINTAINER = "João Henrique Ferreira de Freitas <joaohf@gmail.com>"

SRC_URI = "git://github.com/gleam-lang/gleam;protocol=https;nobranch=1"

SRCREV = "4a83802ca33a8a96227a1b332768725f232f9779"

inherit cargo cargo-update-recipe-crates

require ${BPN}-crates.inc

BBCLASSEXTEND = "native nativesdk"
