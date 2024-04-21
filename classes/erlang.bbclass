
DEPENDS += "erlang erlang-native"

base_erlang_release = "${libdir}/${BPN}"
erlang_release = "${D}/${base_erlang_release}"

ALLOW_EMPTY:${PN}-staticdev = "1"
DESCRIPTION:${PN}-staticdev = ""
RDEPENDS:${PN}-staticdev = ""
FILES:${PN}-staticdev = "\
    ${base_erlang_release}/lib/*/lib/*.a \
    ${base_erlang_release}/lib/*/priv/*.a \
    "

ALLOW_EMPTY:${PN}-dev = "1"
DESCRIPTION:${PN}-dev = ""
RDEPENDS:${PN}-dev = ""
FILES:${PN}-dev = "\
    ${base_erlang_release}/erts-*/include \
    ${base_erlang_release}/lib/*/include/* \
    ${base_erlang_release}/lib/*/c_src/* \
    ${base_erlang_release}/lib/*/src/* \
    ${base_erlang_release}/lib/*/priv/obj \
    ${base_erlang_release}/erts*/src \
    "

ALLOW_EMPTY:${PN}-dbg = "1"
DESCRIPTION:${PN}-dbg = ""
RDEPENDS:${PN}-dbg = ""
FILES:${PN}-dbg = "\
    ${base_erlang_release}/lib/*/bin/.debug \
    ${base_erlang_release}/lib/*/lib/.debug \
    ${base_erlang_release}/lib/*/priv/lib/.debug \
    ${base_erlang_release}/lib/*/priv/obj/.debug \
    ${base_erlang_release}/lib/*/priv/bin/.debug \
    /usr/src/debug \
    "

ALLOW_EMPTY:${PN}-erts-staticdev = "1"
DESCRIPTION:${PN}-erts-staticdev = ""
RDEPENDS:${PN}-erts-staticdev = ""
FILES:${PN}-erts-staticdev = "\
    ${base_erlang_release}/erts-*/lib \
    "

ALLOW_EMPTY:${PN}-erts-doc = "1"
DESCRIPTION:${PN}-erts-doc = ""
RDEPENDS:${PN}-erts-doc = ""
FILES:${PN}-erts-doc = "\
    ${base_erlang_release}/erts-*/doc \
    ${base_erlang_release}/erts-*/man \
    "

ALLOW_EMPTY:${PN}-erts = "1"
DESCRIPTION:${PN}-erts = ""
RDEPENDS:${PN}-erts = ""
FILES:${PN}-erts = "\
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

ALLOW_EMPTY:${PN}-dev-tools = "1"
DESCRIPTION:${PN}-dev-tools = ""
RDEPENDS:${PN}-dev-tools = ""
FILES:${PN}-dev-tools = "\
    ${base_erlang_release}/erts-*/bin/ct_run \
    ${base_erlang_release}/erts-*/bin/dialyzer \
    ${base_erlang_release}/erts-*/bin/erlc \
    ${base_erlang_release}/erts-*/bin/typer \
    "

ALLOW_EMPTY:${PN} = "1"
DESCRIPTION:${PN} = ""
RDEPENDS:${PN} += "${PN}-erts"
FILES:${PN} += "\
    ${base_erlang_release}/* \
    "

PACKAGES =+ "${PN}-erts ${PN}-erts-staticdev ${PN}-erts-doc ${PN}-dev-tools"
