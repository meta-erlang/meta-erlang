SUMMARY = "Target packages for the Erlang/Elixir SDK"

inherit packagegroup

RDEPENDS:${PN} = " \
    erlang \
    erlang-modules-dev \
    elixir \
    elixir-modules-dev \
"