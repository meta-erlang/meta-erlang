
inherit erlang pkgconfig hexpm

DEPENDS += "gleam-native rebar3-native"

RDEPENDS:${PN} = "erlang"

B ?= "${S}"

do_configure[network] = "1"

HEX_ORGANIZATION_TOKEN ?= ""
HEX_ORGANIZATION ?= ""

GLEAM_RELEASE_DIR = "${B}/build/erlang-shipment"

export ERL_COMPILER_OPTIONS = "deterministic"

gleam_do_configure() {
    gleam deps download
}

gleam_do_compile() {
    gleam build --target erlang
}

gleam_do_install() {
    gleam export erlang-shipment
    install -d ${erlang_release}

    cp -R ${GLEAM_RELEASE_DIR}/* ${erlang_release}/

    chown root:root -R ${erlang_release}
}

EXPORT_FUNCTIONS do_configure do_compile do_install
