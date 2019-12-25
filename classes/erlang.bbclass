
DEPENDS += "erlang erlang-native"

base_erlang_release = "${libdir_native}/${BPN}"
erlang_release = "${D}/${base_erlang_release}"

ALLOW_EMPTY_${PN}-staticdev = "1"
DESCRIPTION_${PN}-staticdev = ""
RDEPENDS_${PN}-staticdev = ""
FILES_${PN}-staticdev = "\
    ${base_erlang_release}/lib/*/lib/*.a \
    ${base_erlang_release}/lib/*/priv/*.a \
    "

ALLOW_EMPTY_${PN}-dev = "1"
DESCRIPTION_${PN}-dev = ""
RDEPENDS_${PN}-dev = ""
FILES_${PN}-dev = "\
    ${base_erlang_release}/erts-*/include \
    ${base_erlang_release}/lib/*/include/* \
    ${base_erlang_release}/lib/*/c_src/* \
    ${base_erlang_release}/lib/*/src/* \
    ${base_erlang_release}/erts*/src \
    "

ALLOW_EMPTY_${PN}-dbg = "1"
DESCRIPTION_${PN}-dbg = ""
RDEPENDS_${PN}-dbg = ""
FILES_${PN}-dbg = "\
    ${base_erlang_release}/lib/*/bin/.debug \
    ${base_erlang_release}/lib/*/lib/.debug \
    ${base_erlang_release}/lib/*/priv/lib/.debug \
    ${base_erlang_release}/lib/*/priv/obj/.debug \
    ${base_erlang_release}/lib/*/priv/bin/.debug \
    /usr/src/debug \
    "

ALLOW_EMPTY_${PN}-erts-staticdev = "1"
DESCRIPTION_${PN}-erts-staticdev = ""
RDEPENDS_${PN}-erts-staticdev = ""
FILES_${PN}-erts-staticdev = "\
    ${base_erlang_release}/erts-*/lib \
    "

ALLOW_EMPTY_${PN}-erts-doc = "1"
DESCRIPTION_${PN}-erts-doc = ""
RDEPENDS_${PN}-erts-doc = ""
FILES_${PN}-erts-doc = "\
    ${base_erlang_release}/erts-*/doc \
    ${base_erlang_release}/erts-*/man \
    "

ALLOW_EMPTY_${PN}-erts = "1"
DESCRIPTION_${PN}-erts = ""
RDEPENDS_${PN}-erts = ""
FILES_${PN}-erts = "\
    ${base_erlang_release}/erts-*/bin/beam.smp \
    ${base_erlang_release}/erts-*/bin/dyn_erl \
    ${base_erlang_release}/erts-*/bin/epmd \
    ${base_erlang_release}/erts-*/bin/erl \
    ${base_erlang_release}/erts-*/bin/erl_child_setup \
    ${base_erlang_release}/erts-*/bin/erlexec \
    ${base_erlang_release}/erts-*/bin/escript \
    ${base_erlang_release}/erts-*/bin/heart \
    ${base_erlang_release}/erts-*/bin/inet_gethost \
    ${base_erlang_release}/erts-*/bin/run_erl \
    ${base_erlang_release}/erts-*/bin/to_erl \
    ${base_erlang_release}/erts-*/bin/start \
    ${base_erlang_release}/erts-*/bin/start_erl \
    "

ALLOW_EMPTY_${PN}-dev-tools = "1"
DESCRIPTION_${PN}-dev-tools = ""
RDEPENDS_${PN}-dev-tools = ""
FILES_${PN}-dev-tools = "\
    ${base_erlang_release}/erts-*/bin/ct_run \
    ${base_erlang_release}/erts-*/bin/dialyzer \
    ${base_erlang_release}/erts-*/bin/erlc \
    ${base_erlang_release}/erts-*/bin/typer \
    "

ALLOW_EMPTY_${PN} = "1"
DESCRIPTION_${PN} = ""
RDEPENDS_${PN} = "${PN}-erts"
FILES_${PN} = "\
    ${base_erlang_release}/* \
    "

PACKAGES =+ "${PN}-erts ${PN}-erts-staticdev ${PN}-erts-doc ${PN}-dev-tools"