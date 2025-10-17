require erlang-${PV}-manifest.inc
include erlang.inc

PR = "r0"

SRC_URI += "file://0001-Enable-x32-support-for-crypto-configure.patch \
            file://0002-Enable-the-correct-ifdef-branch-when-x32-is-enabled.patch \
            file://0001-erts-fix-static-function-prototypes.patch \
            file://0001-snmp-add-deterministic-option-when-available-for-asn.patch \
            "

SRCREV = "34316363201de07437e14e79ef559fde7becb0ee"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/28:"
