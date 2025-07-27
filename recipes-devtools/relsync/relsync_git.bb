SUMMARY = "Relsync synchronizes the contents of a local Erlang/OTP release with a remote node"
DESCRIPTION = "Relsync synchronizes the contents of a local Erlang/OTP release with a remote node. It is similar to rsync in that it attempts to only copy files that have been added or changed, but also reloads changed .beam files and supports Erlang pre and post synchronization scripts." 
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRCREV = "1ba101eff091ce79f2437c95f50cca5d2ab62426"
PV = "0.1.0+git${SRCPV}"
PR = "r2"

SRC_URI = "git://github.com/joaohf/relsync;branch=master;protocol=https"

inherit rebar3

REBAR3_PROFILE = "release"

do_compile() {
    cd ${S}
    rebar3 as ${REBAR3_PROFILE} escriptize
}

do_install() {
    install -d 0755 ${D}/${bindir}
    install -m 0755 ${REBAR_BASE_DIR}/${REBAR3_PROFILE}/bin/relsync ${D}/${bindir}/relsync
}

FILES:${PN} = "${bindir}"
FILES:${PN}-dbg += "/.debug"

BBCLASSEXTEND = "native nativesdk"

