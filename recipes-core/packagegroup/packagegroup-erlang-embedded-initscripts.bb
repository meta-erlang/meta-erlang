# Copyright (C) 2015 João Henrique Ferreira de Freitas <joaohf@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Erlang Embedded Initscripts"
LICENSE = "MIT"
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
    ${VIRTUAL-RUNTIME_init_manager} \
    ${VIRTUAL-RUNTIME_initscripts} \
    ${VIRTUAL-RUNTIME_login_manager} \
    ${VIRTUAL-RUNTIME_syslog} \
    "

