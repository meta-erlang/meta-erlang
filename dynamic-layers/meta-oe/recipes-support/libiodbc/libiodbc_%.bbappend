FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-Remove-link-against-static-library.patch"

EXTRA_OECONF:remove = "--includedir=/usr/include/iodbc"
EXTRA_OECONF += "--includedir=${includedir}"

BBCLASSEXTEND += "nativesdk"
