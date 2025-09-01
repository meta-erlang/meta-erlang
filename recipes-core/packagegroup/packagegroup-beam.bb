# Copyright (C) 2015 João Henrique Ferreira de Freitas <joaohf@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Erlang/OTP and Elixir"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
PR = "r0"

inherit packagegroup

PACKAGE_ARCH = "${MACHINE_ARCH}"

PACKAGES = "\
    ${PN} \
    ${PN}-erlang \
    ${PN}-elixir \
    "

SUMMARY:${PN} = "Install Erlang/OTP and Elixir"
RDEPENDS:${PN} = "\
    ${PN}-erlang \
    ${PN}-elixir \
    "

SUMMARY:${PN}-erlang = "Full Erlang/OTP support"
RDEPENDS:${PN}-erlang = "\
    erlang \
    erlang-modules \
    "

SUMMARY:${PN}-elixir = "Full Elixir support"
RDEPENDS:${PN}-elixir = "\
    elixir \
    "