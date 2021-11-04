inherit mix

DEPENDS += "nodejs-native"

# see https://hexdocs.pm/phoenix/releases.html
do_compile:append() {
    npm install --prefix ./assets
    npm run deploy --prefix ./assets
    mix phx.digest
}