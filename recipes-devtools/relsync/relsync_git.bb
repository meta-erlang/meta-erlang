SUMMARY = "Relsync synchronizes the contents of a local Erlang/OTP release with a remote node"
DESCRIPTION = "Relsync synchronizes the contents of a local Erlang/OTP release with a remote node. It is similar to rsync in that it attempts to only copy files that have been added or changed, but also reloads changed .beam files and supports Erlang pre and post synchronization scripts." 
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

S = "${WORKDIR}/git"
SRCREV = "4f49df183fa4b5cff5f7afb4818d907b65d9ab37"
PV = "0.0.0-git${SRCPV}"
PR = "r0"

SRC_URI = "git://github.com/fhunleth/relsync;branch=master"

DEPENDS += "erlang-native"

do_install() {
        install -d 0755 ${D}/${bindir}
	install -m 0755 ${S}/relsync ${D}/${bindir}/relsync
}

FILES_${PN} = "${bindir}"
FILES_${PN}-dbg += "/.debug"

BBCLASSEXTEND = "native nativesdk"

