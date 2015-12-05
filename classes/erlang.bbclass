
DEPENDS += "erlang erlang-native"

base_erlang_release = "${libdir_native}/${BPN}"
erlang_release = "${D}/${base_erlang_release}"

ALLOW_EMPTY_${PN}-staticdev="1"
DESCRIPTION_${PN}-staticdev=""
RDEPENDS_${PN}-staticdev=""
FILES_${PN}-staticdev="${base_erlang_release}/erts-*/lib/*.a ${base_erlang_release}/erts-*/lib/internal/*.a ${base_erlang_release}/lib/*/lib/*.a ${base_erlang_release}/lib/*/priv/*.a "

ALLOW_EMPTY_${PN}-dev="1"
DESCRIPTION_${PN}-dev=""
RDEPENDS_${PN}-dev=""
FILES_${PN}-dev="${base_erlang_release}/erts-*/include ${base_erlang_release}/lib/*/include/* ${base_erlang_release}/erts*/src"

ALLOW_EMPTY_${PN}-dbg="1"
DESCRIPTION_${PN}-dbg=""
RDEPENDS_${PN}-dbg=""
FILES_${PN}-dbg="${base_erlang_release}/lib/*/bin/.debug ${base_erlang_release}/lib/*/lib/.debug ${base_erlang_release}/lib/*/priv/lib/.debug ${base_erlang_release}/lib/*/priv/obj/.debug ${base_erlang_release}/lib/*/priv/bin/.debug "

ALLOW_EMPTY_${PN}="1"
DESCRIPTION_${PN}=""
RDEPENDS_${PN}=""
FILES_${PN}="${base_erlang_release}/* "
