inherit mix-rebar3 cargo_common

# We don't want to use bitbakes vendoring
CARGO_DISABLE_BITBAKE_VENDORING = "1"

# the binary we will use
CARGO = "cargo"

# We need cargo to compile for the target
BASEDEPENDS:append = " cargo-native"

# Ensure we get the right rust variant
DEPENDS:append:class-target = " rust-native ${RUSTLIB_DEP}"
DEPENDS:append:class-nativesdk = " rust-native ${RUSTLIB_DEP}"
DEPENDS:append:class-native = " rust-native"

# In case something fails in the build process, give a bit more feedback on
# where the issue occured
export RUST_BACKTRACE = "1"

RUSTFLAGS ??= ""

export RUSTFLAGS
export RUST_TARGET = "${RUST_TARGET_SYS}"

do_configure:append() {
    mix_do_configure
}

do_compile:prepend() {
    oe_cargo_fix_env
    bbnote "cargo = $(which ${CARGO})"
    bbnote "rustc = $(which ${RUSTC})"
}