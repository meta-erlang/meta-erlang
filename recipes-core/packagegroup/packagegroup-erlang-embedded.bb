# Copyright (C) 2015 João Henrique Ferreira de Freitas <joaohf@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Erlang Embedded"
LICENSE = "MIT"
PR = "r0"

inherit packagegroup

PACKAGES = "\
    ${PN} \
    ${PN}-erlang \
    "

RDEPENDS_${PN} = "\
    ${PN}-initscripts \
    ${PN}-erlang \
    "

RDEPENDS_${PN}-erlang = "\
    erlang \
    erlang-tools \
    "

