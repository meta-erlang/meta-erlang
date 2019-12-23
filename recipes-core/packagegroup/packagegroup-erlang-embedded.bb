# Copyright (C) 2015 João Henrique Ferreira de Freitas <joaohf@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Erlang Embedded"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
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

