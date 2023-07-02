inherit mix

do_compile[network] = "1"

# see https://hexdocs.pm/phoenix/releases.html
do_compile:append() {
    mix assets.deploy
}
