# Copyright (C) 2015 João Henrique Ferreira de Freitas <joaohf@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Erlang Embedded Initscripts"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
PR = "r0"

inherit packagegroup

PACKAGES = "\
    ${PN} \
    "

VIRTUAL-RUNTIME_initscripts ?= ""
VIRTUAL-RUNTIME_init_manager ?= "erlinit"
VIRTUAL-RUNTIME_login_manager ?= ""
VIRTUAL-RUNTIME_syslog ?= ""
VIRTUAL-RUNTIME_dev_manager ?= ""
VIRTUAL-RUNTIME_keymaps ?= ""
VIRTUAL-RUNTIME_update-alternatives = ""
RDEPENDS_${PN} = "\
    base-files \
    ${VIRTUAL-RUNTIME_init_manager} \
    ${VIRTUAL-RUNTIME_initscripts} \
    ${VIRTUAL-RUNTIME_login_manager} \
    ${VIRTUAL-RUNTIME_syslog} \
    "

