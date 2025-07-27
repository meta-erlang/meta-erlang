SUMMARY = "A friendly language for building type-safe, scalable systems!"
DESCRIPTION = "Gleam is a type safe and scalable language for the Erlang virtual machine and JavaScript runtimes."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENCE;md5=d2b9bba205c2072b8fd35e19f0868aed \
                    file://test/hello_world/LICENSE;md5=6c4ffa971dc0a63c7f0a73336601680b"

RECIPE_MAINTAINER = "João Henrique Ferreira de Freitas <joaohf@gmail.com>"

SRC_URI = "git://github.com/gleam-lang/gleam;protocol=https;nobranch=1"

SRCREV = "5d13c2212ddda78c725fb358efb777c14176cd83"

inherit cargo cargo-update-recipe-crates

# from Cargo.lock
SRC_URI += " \
    crate://crates.io/addr2line/0.22.0 \
    crate://crates.io/adler/1.0.2 \
    crate://crates.io/aead/0.5.2 \
    crate://crates.io/age/0.11.1 \
    crate://crates.io/age-core/0.11.0 \
    crate://crates.io/aho-corasick/1.1.3 \
    crate://crates.io/anstream/0.6.14 \
    crate://crates.io/anstyle/1.0.8 \
    crate://crates.io/anstyle-parse/0.2.4 \
    crate://crates.io/anstyle-query/1.1.0 \
    crate://crates.io/anstyle-wincon/3.0.3 \
    crate://crates.io/anyhow/1.0.86 \
    crate://crates.io/arc-swap/1.7.1 \
    crate://crates.io/askama/0.12.1 \
    crate://crates.io/askama_derive/0.12.5 \
    crate://crates.io/askama_escape/0.10.3 \
    crate://crates.io/askama_parser/0.2.1 \
    crate://crates.io/asn1-rs/0.5.2 \
    crate://crates.io/asn1-rs-derive/0.4.0 \
    crate://crates.io/asn1-rs-impl/0.1.0 \
    crate://crates.io/async-trait/0.1.83 \
    crate://crates.io/autocfg/1.3.0 \
    crate://crates.io/backtrace/0.3.73 \
    crate://crates.io/base16/0.2.1 \
    crate://crates.io/base64/0.13.1 \
    crate://crates.io/base64/0.21.7 \
    crate://crates.io/base64/0.22.0 \
    crate://crates.io/basic-toml/0.1.9 \
    crate://crates.io/bech32/0.9.1 \
    crate://crates.io/bimap/0.6.3 \
    crate://crates.io/bincode/1.3.3 \
    crate://crates.io/bitflags/1.3.2 \
    crate://crates.io/bitflags/2.5.0 \
    crate://crates.io/bitmaps/2.1.0 \
    crate://crates.io/block-buffer/0.10.4 \
    crate://crates.io/bstr/1.9.1 \
    crate://crates.io/bumpalo/3.15.4 \
    crate://crates.io/bytes/1.10.0 \
    crate://crates.io/camino/1.1.9 \
    crate://crates.io/capnp/0.20.3 \
    crate://crates.io/capnpc/0.20.1 \
    crate://crates.io/cc/1.2.16 \
    crate://crates.io/cfg-if/1.0.0 \
    crate://crates.io/cfg_aliases/0.2.1 \
    crate://crates.io/chacha20/0.9.1 \
    crate://crates.io/chacha20poly1305/0.10.1 \
    crate://crates.io/cipher/0.4.4 \
    crate://crates.io/clap/4.5.16 \
    crate://crates.io/clap_builder/4.5.15 \
    crate://crates.io/clap_derive/4.5.13 \
    crate://crates.io/clap_lex/0.7.0 \
    crate://crates.io/codespan-reporting/0.11.1 \
    crate://crates.io/colorchoice/1.0.0 \
    crate://crates.io/console/0.15.8 \
    crate://crates.io/console_error_panic_hook/0.1.7 \
    crate://crates.io/cookie-factory/0.3.3 \
    crate://crates.io/cpufeatures/0.2.12 \
    crate://crates.io/crc32fast/1.4.0 \
    crate://crates.io/crossbeam-channel/0.5.12 \
    crate://crates.io/crossbeam-deque/0.8.5 \
    crate://crates.io/crossbeam-epoch/0.9.18 \
    crate://crates.io/crossbeam-utils/0.8.19 \
    crate://crates.io/crypto-common/0.1.6 \
    crate://crates.io/ctrlc/3.4.5 \
    crate://crates.io/curve25519-dalek/4.1.3 \
    crate://crates.io/curve25519-dalek-derive/0.1.1 \
    crate://crates.io/dashmap/6.1.0 \
    crate://crates.io/data-encoding/2.5.0 \
    crate://crates.io/dbus/0.9.7 \
    crate://crates.io/debug-ignore/1.0.5 \
    crate://crates.io/der-parser/8.2.0 \
    crate://crates.io/deranged/0.3.11 \
    crate://crates.io/diff/0.1.13 \
    crate://crates.io/digest/0.10.7 \
    crate://crates.io/dirs-next/2.0.0 \
    crate://crates.io/dirs-sys-next/0.1.2 \
    crate://crates.io/displaydoc/0.2.4 \
    crate://crates.io/ecow/0.2.3 \
    crate://crates.io/either/1.10.0 \
    crate://crates.io/embedded-io/0.6.1 \
    crate://crates.io/encode_unicode/0.3.6 \
    crate://crates.io/endian-type/0.1.2 \
    crate://crates.io/equivalent/1.0.1 \
    crate://crates.io/errno/0.3.8 \
    crate://crates.io/fastrand/2.0.2 \
    crate://crates.io/fiat-crypto/0.2.9 \
    crate://crates.io/filetime/0.2.23 \
    crate://crates.io/find-crate/0.6.3 \
    crate://crates.io/fixedbitset/0.4.2 \
    crate://crates.io/flate2/1.0.30 \
    crate://crates.io/fluent/0.16.1 \
    crate://crates.io/fluent-bundle/0.15.3 \
    crate://crates.io/fluent-langneg/0.13.0 \
    crate://crates.io/fluent-syntax/0.11.1 \
    crate://crates.io/fnv/1.0.7 \
    crate://crates.io/form_urlencoded/1.2.1 \
    crate://crates.io/fs_extra/1.3.0 \
    crate://crates.io/fslock/0.2.1 \
    crate://crates.io/futures/0.3.31 \
    crate://crates.io/futures-channel/0.3.31 \
    crate://crates.io/futures-core/0.3.31 \
    crate://crates.io/futures-executor/0.3.31 \
    crate://crates.io/futures-io/0.3.31 \
    crate://crates.io/futures-macro/0.3.31 \
    crate://crates.io/futures-sink/0.3.31 \
    crate://crates.io/futures-task/0.3.31 \
    crate://crates.io/futures-util/0.3.31 \
    crate://crates.io/generic-array/0.14.7 \
    crate://crates.io/getrandom/0.2.15 \
    crate://crates.io/gimli/0.29.0 \
    crate://crates.io/globset/0.4.16 \
    crate://crates.io/hashbrown/0.14.3 \
    crate://crates.io/heck/0.5.0 \
    crate://crates.io/hermit-abi/0.3.9 \
    crate://crates.io/hexpm/3.3.0 \
    crate://crates.io/hkdf/0.12.4 \
    crate://crates.io/hmac/0.12.1 \
    crate://crates.io/hostname/0.4.0 \
    crate://crates.io/http/1.2.0 \
    crate://crates.io/http-auth-basic/0.3.3 \
    crate://crates.io/http-body/1.0.0 \
    crate://crates.io/http-body-util/0.1.1 \
    crate://crates.io/httparse/1.8.0 \
    crate://crates.io/humansize/2.1.3 \
    crate://crates.io/hyper/1.5.2 \
    crate://crates.io/hyper-rustls/0.27.2 \
    crate://crates.io/hyper-util/0.1.10 \
    crate://crates.io/i18n-config/0.4.7 \
    crate://crates.io/i18n-embed/0.15.2 \
    crate://crates.io/i18n-embed-fl/0.9.2 \
    crate://crates.io/i18n-embed-impl/0.8.4 \
    crate://crates.io/icu_collections/1.5.0 \
    crate://crates.io/icu_locid/1.5.0 \
    crate://crates.io/icu_locid_transform/1.5.0 \
    crate://crates.io/icu_locid_transform_data/1.5.0 \
    crate://crates.io/icu_normalizer/1.5.0 \
    crate://crates.io/icu_normalizer_data/1.5.0 \
    crate://crates.io/icu_properties/1.5.1 \
    crate://crates.io/icu_properties_data/1.5.0 \
    crate://crates.io/icu_provider/1.5.0 \
    crate://crates.io/icu_provider_macros/1.5.0 \
    crate://crates.io/id-arena/2.2.1 \
    crate://crates.io/idna/1.0.3 \
    crate://crates.io/idna_adapter/1.2.0 \
    crate://crates.io/ignore/0.4.22 \
    crate://crates.io/im/15.1.0 \
    crate://crates.io/indexmap/2.2.6 \
    crate://crates.io/inout/0.1.3 \
    crate://crates.io/insta/1.41.1 \
    crate://crates.io/intl-memoizer/0.5.2 \
    crate://crates.io/intl_pluralrules/7.0.2 \
    crate://crates.io/io_tee/0.1.1 \
    crate://crates.io/ipnet/2.9.0 \
    crate://crates.io/is_terminal_polyfill/1.70.0 \
    crate://crates.io/itertools/0.13.0 \
    crate://crates.io/itoa/1.0.11 \
    crate://crates.io/js-sys/0.3.69 \
    crate://crates.io/lazy_static/1.4.0 \
    crate://crates.io/libc/0.2.155 \
    crate://crates.io/libdbus-sys/0.2.5 \
    crate://crates.io/libm/0.2.8 \
    crate://crates.io/libredox/0.1.3 \
    crate://crates.io/linked-hash-map/0.5.6 \
    crate://crates.io/linux-raw-sys/0.4.13 \
    crate://crates.io/litemap/0.7.4 \
    crate://crates.io/lock_api/0.4.12 \
    crate://crates.io/log/0.4.22 \
    crate://crates.io/lsp-server/0.7.7 \
    crate://crates.io/lsp-types/0.95.1 \
    crate://crates.io/matchers/0.1.0 \
    crate://crates.io/memchr/2.7.2 \
    crate://crates.io/mime/0.3.17 \
    crate://crates.io/mime_guess/2.0.4 \
    crate://crates.io/minimal-lexical/0.2.1 \
    crate://crates.io/miniz_oxide/0.7.2 \
    crate://crates.io/mio/1.0.1 \
    crate://crates.io/multimap/0.10.0 \
    crate://crates.io/nibble_vec/0.1.0 \
    crate://crates.io/nix/0.29.0 \
    crate://crates.io/nom/7.1.3 \
    crate://crates.io/normpath/1.2.0 \
    crate://crates.io/nu-ansi-term/0.46.0 \
    crate://crates.io/num-bigint/0.4.6 \
    crate://crates.io/num-conv/0.1.0 \
    crate://crates.io/num-integer/0.1.46 \
    crate://crates.io/num-traits/0.2.19 \
    crate://crates.io/object/0.36.1 \
    crate://crates.io/oid-registry/0.6.1 \
    crate://crates.io/once_cell/1.19.0 \
    crate://crates.io/opaque-debug/0.3.1 \
    crate://crates.io/opener/0.7.2 \
    crate://crates.io/overload/0.1.1 \
    crate://crates.io/parking_lot/0.12.3 \
    crate://crates.io/parking_lot_core/0.9.10 \
    crate://crates.io/pathdiff/0.2.3 \
    crate://crates.io/pbkdf2/0.12.2 \
    crate://crates.io/percent-encoding/2.3.1 \
    crate://crates.io/petgraph/0.6.5 \
    crate://crates.io/pin-project/1.1.5 \
    crate://crates.io/pin-project-internal/1.1.5 \
    crate://crates.io/pin-project-lite/0.2.14 \
    crate://crates.io/pin-utils/0.1.0 \
    crate://crates.io/pkg-config/0.3.30 \
    crate://crates.io/poly1305/0.8.0 \
    crate://crates.io/powerfmt/0.2.0 \
    crate://crates.io/ppv-lite86/0.2.17 \
    crate://crates.io/pretty_assertions/1.4.1 \
    crate://crates.io/prettyplease/0.2.17 \
    crate://crates.io/proc-macro-error-attr2/2.0.0 \
    crate://crates.io/proc-macro-error2/2.0.1 \
    crate://crates.io/proc-macro2/1.0.79 \
    crate://crates.io/prost/0.13.5 \
    crate://crates.io/prost-build/0.13.5 \
    crate://crates.io/prost-derive/0.13.5 \
    crate://crates.io/prost-types/0.13.5 \
    crate://crates.io/pubgrub/0.2.1 \
    crate://crates.io/pulldown-cmark/0.13.0 \
    crate://crates.io/pulldown-cmark-escape/0.11.0 \
    crate://crates.io/quinn/0.11.2 \
    crate://crates.io/quinn-proto/0.11.8 \
    crate://crates.io/quinn-udp/0.5.4 \
    crate://crates.io/quote/1.0.35 \
    crate://crates.io/radix_trie/0.2.1 \
    crate://crates.io/rand/0.8.5 \
    crate://crates.io/rand_chacha/0.3.1 \
    crate://crates.io/rand_core/0.6.4 \
    crate://crates.io/rand_xoshiro/0.6.0 \
    crate://crates.io/redox_syscall/0.4.1 \
    crate://crates.io/redox_syscall/0.5.7 \
    crate://crates.io/redox_users/0.4.5 \
    crate://crates.io/regex/1.10.5 \
    crate://crates.io/regex-automata/0.1.10 \
    crate://crates.io/regex-automata/0.4.6 \
    crate://crates.io/regex-syntax/0.6.29 \
    crate://crates.io/regex-syntax/0.8.3 \
    crate://crates.io/reqwest/0.12.12 \
    crate://crates.io/ring/0.17.13 \
    crate://crates.io/rpassword/7.3.1 \
    crate://crates.io/rtoolbox/0.0.2 \
    crate://crates.io/rust-embed/8.5.0 \
    crate://crates.io/rust-embed-impl/8.5.0 \
    crate://crates.io/rust-embed-utils/8.5.0 \
    crate://crates.io/rustc-demangle/0.1.24 \
    crate://crates.io/rustc-hash/1.1.0 \
    crate://crates.io/rustc-hash/2.0.0 \
    crate://crates.io/rustc_version/0.4.1 \
    crate://crates.io/rusticata-macros/4.1.0 \
    crate://crates.io/rustix/0.38.32 \
    crate://crates.io/rustls/0.23.7 \
    crate://crates.io/rustls-pemfile/2.1.2 \
    crate://crates.io/rustls-pki-types/1.4.1 \
    crate://crates.io/rustls-webpki/0.102.2 \
    crate://crates.io/rustversion/1.0.14 \
    crate://crates.io/ryu/1.0.17 \
    crate://crates.io/salsa20/0.10.2 \
    crate://crates.io/same-file/1.0.6 \
    crate://crates.io/scoped-tls/1.0.1 \
    crate://crates.io/scopeguard/1.2.0 \
    crate://crates.io/scrypt/0.11.0 \
    crate://crates.io/secrecy/0.10.3 \
    crate://crates.io/self_cell/0.10.3 \
    crate://crates.io/self_cell/1.0.4 \
    crate://crates.io/semver/1.0.23 \
    crate://crates.io/serde/1.0.210 \
    crate://crates.io/serde-wasm-bindgen/0.6.5 \
    crate://crates.io/serde_derive/1.0.210 \
    crate://crates.io/serde_json/1.0.138 \
    crate://crates.io/serde_repr/0.1.18 \
    crate://crates.io/serde_urlencoded/0.7.1 \
    crate://crates.io/sha2/0.10.8 \
    crate://crates.io/sharded-slab/0.1.7 \
    crate://crates.io/shlex/1.3.0 \
    crate://crates.io/similar/2.5.0 \
    crate://crates.io/sized-chunks/0.6.5 \
    crate://crates.io/slab/0.4.9 \
    crate://crates.io/smallvec/1.13.2 \
    crate://crates.io/socket2/0.5.6 \
    crate://crates.io/spdx/0.10.8 \
    crate://crates.io/stable_deref_trait/1.2.0 \
    crate://crates.io/static_vcruntime/2.0.0 \
    crate://crates.io/strsim/0.11.1 \
    crate://crates.io/strum/0.26.3 \
    crate://crates.io/strum_macros/0.26.4 \
    crate://crates.io/subtle/2.5.0 \
    crate://crates.io/syn/1.0.109 \
    crate://crates.io/syn/2.0.58 \
    crate://crates.io/sync_wrapper/1.0.1 \
    crate://crates.io/synstructure/0.12.6 \
    crate://crates.io/synstructure/0.13.1 \
    crate://crates.io/tar/0.4.43 \
    crate://crates.io/tempfile/3.12.0 \
    crate://crates.io/termcolor/1.4.1 \
    crate://crates.io/thiserror/1.0.65 \
    crate://crates.io/thiserror-impl/1.0.65 \
    crate://crates.io/thread_local/1.1.8 \
    crate://crates.io/time/0.3.36 \
    crate://crates.io/time-core/0.1.2 \
    crate://crates.io/time-macros/0.2.18 \
    crate://crates.io/tinystr/0.7.6 \
    crate://crates.io/tinyvec/1.6.0 \
    crate://crates.io/tinyvec_macros/0.1.1 \
    crate://crates.io/tokio/1.42.1 \
    crate://crates.io/tokio-rustls/0.26.0 \
    crate://crates.io/toml/0.5.11 \
    crate://crates.io/toml_datetime/0.6.8 \
    crate://crates.io/toml_edit/0.22.20 \
    crate://crates.io/tower/0.5.2 \
    crate://crates.io/tower-layer/0.3.3 \
    crate://crates.io/tower-service/0.3.3 \
    crate://crates.io/tracing/0.1.40 \
    crate://crates.io/tracing-attributes/0.1.27 \
    crate://crates.io/tracing-core/0.1.32 \
    crate://crates.io/tracing-log/0.2.0 \
    crate://crates.io/tracing-subscriber/0.3.18 \
    crate://crates.io/tracing-wasm/0.2.1 \
    crate://crates.io/try-lock/0.2.5 \
    crate://crates.io/type-map/0.5.0 \
    crate://crates.io/typenum/1.17.0 \
    crate://crates.io/unic-langid/0.9.5 \
    crate://crates.io/unic-langid-impl/0.9.5 \
    crate://crates.io/unicase/2.7.0 \
    crate://crates.io/unicode-ident/1.0.12 \
    crate://crates.io/unicode-segmentation/1.12.0 \
    crate://crates.io/unicode-width/0.1.11 \
    crate://crates.io/unicode-xid/0.2.4 \
    crate://crates.io/universal-hash/0.5.1 \
    crate://crates.io/untrusted/0.9.0 \
    crate://crates.io/url/2.5.4 \
    crate://crates.io/utf16_iter/1.0.5 \
    crate://crates.io/utf8_iter/1.0.4 \
    crate://crates.io/utf8parse/0.2.1 \
    crate://crates.io/valuable/0.1.0 \
    crate://crates.io/vec1/1.12.1 \
    crate://crates.io/version_check/0.9.4 \
    crate://crates.io/walkdir/2.5.0 \
    crate://crates.io/want/0.3.1 \
    crate://crates.io/wasi/0.11.0+wasi-snapshot-preview1 \
    crate://crates.io/wasm-bindgen/0.2.99 \
    crate://crates.io/wasm-bindgen-backend/0.2.99 \
    crate://crates.io/wasm-bindgen-futures/0.4.42 \
    crate://crates.io/wasm-bindgen-macro/0.2.99 \
    crate://crates.io/wasm-bindgen-macro-support/0.2.99 \
    crate://crates.io/wasm-bindgen-shared/0.2.99 \
    crate://crates.io/wasm-bindgen-test/0.3.42 \
    crate://crates.io/wasm-bindgen-test-macro/0.3.42 \
    crate://crates.io/web-sys/0.3.69 \
    crate://crates.io/webpki-roots/0.26.1 \
    crate://crates.io/winapi/0.3.9 \
    crate://crates.io/winapi-i686-pc-windows-gnu/0.4.0 \
    crate://crates.io/winapi-util/0.1.6 \
    crate://crates.io/winapi-x86_64-pc-windows-gnu/0.4.0 \
    crate://crates.io/windows/0.52.0 \
    crate://crates.io/windows-core/0.52.0 \
    crate://crates.io/windows-registry/0.2.0 \
    crate://crates.io/windows-result/0.2.0 \
    crate://crates.io/windows-strings/0.1.0 \
    crate://crates.io/windows-sys/0.48.0 \
    crate://crates.io/windows-sys/0.52.0 \
    crate://crates.io/windows-sys/0.59.0 \
    crate://crates.io/windows-targets/0.48.5 \
    crate://crates.io/windows-targets/0.52.6 \
    crate://crates.io/windows_aarch64_gnullvm/0.48.5 \
    crate://crates.io/windows_aarch64_gnullvm/0.52.6 \
    crate://crates.io/windows_aarch64_msvc/0.48.5 \
    crate://crates.io/windows_aarch64_msvc/0.52.6 \
    crate://crates.io/windows_i686_gnu/0.48.5 \
    crate://crates.io/windows_i686_gnu/0.52.6 \
    crate://crates.io/windows_i686_gnullvm/0.52.6 \
    crate://crates.io/windows_i686_msvc/0.48.5 \
    crate://crates.io/windows_i686_msvc/0.52.6 \
    crate://crates.io/windows_x86_64_gnu/0.48.5 \
    crate://crates.io/windows_x86_64_gnu/0.52.6 \
    crate://crates.io/windows_x86_64_gnullvm/0.48.5 \
    crate://crates.io/windows_x86_64_gnullvm/0.52.6 \
    crate://crates.io/windows_x86_64_msvc/0.48.5 \
    crate://crates.io/windows_x86_64_msvc/0.52.6 \
    crate://crates.io/winnow/0.6.26 \
    crate://crates.io/write16/1.0.0 \
    crate://crates.io/writeable/0.5.5 \
    crate://crates.io/x25519-dalek/2.0.1 \
    crate://crates.io/x509-parser/0.15.1 \
    crate://crates.io/xattr/1.3.1 \
    crate://crates.io/xxhash-rust/0.8.15 \
    crate://crates.io/yansi/1.0.1 \
    crate://crates.io/yoke/0.7.5 \
    crate://crates.io/yoke-derive/0.7.5 \
    crate://crates.io/zerofrom/0.1.5 \
    crate://crates.io/zerofrom-derive/0.1.5 \
    crate://crates.io/zeroize/1.7.0 \
    crate://crates.io/zeroize_derive/1.4.2 \
    crate://crates.io/zerovec/0.10.4 \
    crate://crates.io/zerovec-derive/0.10.3 \
"

SRC_URI[addr2line-0.22.0.sha256sum] = "6e4503c46a5c0c7844e948c9a4d6acd9f50cccb4de1c48eb9e291ea17470c678"
SRC_URI[adler-1.0.2.sha256sum] = "f26201604c87b1e01bd3d98f8d5d9a8fcbb815e8cedb41ffccbeb4bf593a35fe"
SRC_URI[aead-0.5.2.sha256sum] = "d122413f284cf2d62fb1b7db97e02edb8cda96d769b16e443a4f6195e35662b0"
SRC_URI[age-0.11.1.sha256sum] = "57fc171f4874fa10887e47088f81a55fcf030cd421aa31ec2b370cafebcc608a"
SRC_URI[age-core-0.11.0.sha256sum] = "e2bf6a89c984ca9d850913ece2da39e1d200563b0a94b002b253beee4c5acf99"
SRC_URI[aho-corasick-1.1.3.sha256sum] = "8e60d3430d3a69478ad0993f19238d2df97c507009a52b3c10addcd7f6bcb916"
SRC_URI[anstream-0.6.14.sha256sum] = "418c75fa768af9c03be99d17643f93f79bbba589895012a80e3452a19ddda15b"
SRC_URI[anstyle-1.0.8.sha256sum] = "1bec1de6f59aedf83baf9ff929c98f2ad654b97c9510f4e70cf6f661d49fd5b1"
SRC_URI[anstyle-parse-0.2.4.sha256sum] = "c03a11a9034d92058ceb6ee011ce58af4a9bf61491aa7e1e59ecd24bd40d22d4"
SRC_URI[anstyle-query-1.1.0.sha256sum] = "ad186efb764318d35165f1758e7dcef3b10628e26d41a44bc5550652e6804391"
SRC_URI[anstyle-wincon-3.0.3.sha256sum] = "61a38449feb7068f52bb06c12759005cf459ee52bb4adc1d5a7c4322d716fb19"
SRC_URI[anyhow-1.0.86.sha256sum] = "b3d1d046238990b9cf5bcde22a3fb3584ee5cf65fb2765f454ed428c7a0063da"
SRC_URI[arc-swap-1.7.1.sha256sum] = "69f7f8c3906b62b754cd5326047894316021dcfe5a194c8ea52bdd94934a3457"
SRC_URI[askama-0.12.1.sha256sum] = "b79091df18a97caea757e28cd2d5fda49c6cd4bd01ddffd7ff01ace0c0ad2c28"
SRC_URI[askama_derive-0.12.5.sha256sum] = "19fe8d6cb13c4714962c072ea496f3392015f0989b1a2847bb4b2d9effd71d83"
SRC_URI[askama_escape-0.10.3.sha256sum] = "619743e34b5ba4e9703bba34deac3427c72507c7159f5fd030aea8cac0cfe341"
SRC_URI[askama_parser-0.2.1.sha256sum] = "acb1161c6b64d1c3d83108213c2a2533a342ac225aabd0bda218278c2ddb00c0"
SRC_URI[asn1-rs-0.5.2.sha256sum] = "7f6fd5ddaf0351dff5b8da21b2fb4ff8e08ddd02857f0bf69c47639106c0fff0"
SRC_URI[asn1-rs-derive-0.4.0.sha256sum] = "726535892e8eae7e70657b4c8ea93d26b8553afb1ce617caee529ef96d7dee6c"
SRC_URI[asn1-rs-impl-0.1.0.sha256sum] = "2777730b2039ac0f95f093556e61b6d26cebed5393ca6f152717777cec3a42ed"
SRC_URI[async-trait-0.1.83.sha256sum] = "721cae7de5c34fbb2acd27e21e6d2cf7b886dce0c27388d46c4e6c47ea4318dd"
SRC_URI[autocfg-1.3.0.sha256sum] = "0c4b4d0bd25bd0b74681c0ad21497610ce1b7c91b1022cd21c80c6fbdd9476b0"
SRC_URI[backtrace-0.3.73.sha256sum] = "5cc23269a4f8976d0a4d2e7109211a419fe30e8d88d677cd60b6bc79c5732e0a"
SRC_URI[base16-0.2.1.sha256sum] = "d27c3610c36aee21ce8ac510e6224498de4228ad772a171ed65643a24693a5a8"
SRC_URI[base64-0.13.1.sha256sum] = "9e1b586273c5702936fe7b7d6896644d8be71e6314cfe09d3167c95f712589e8"
SRC_URI[base64-0.21.7.sha256sum] = "9d297deb1925b89f2ccc13d7635fa0714f12c87adce1c75356b39ca9b7178567"
SRC_URI[base64-0.22.0.sha256sum] = "9475866fec1451be56a3c2400fd081ff546538961565ccb5b7142cbd22bc7a51"
SRC_URI[basic-toml-0.1.9.sha256sum] = "823388e228f614e9558c6804262db37960ec8821856535f5c3f59913140558f8"
SRC_URI[bech32-0.9.1.sha256sum] = "d86b93f97252c47b41663388e6d155714a9d0c398b99f1005cbc5f978b29f445"
SRC_URI[bimap-0.6.3.sha256sum] = "230c5f1ca6a325a32553f8640d31ac9b49f2411e901e427570154868b46da4f7"
SRC_URI[bincode-1.3.3.sha256sum] = "b1f45e9417d87227c7a56d22e471c6206462cba514c7590c09aff4cf6d1ddcad"
SRC_URI[bitflags-1.3.2.sha256sum] = "bef38d45163c2f1dde094a7dfd33ccf595c92905c8f8f4fdc18d06fb1037718a"
SRC_URI[bitflags-2.5.0.sha256sum] = "cf4b9d6a944f767f8e5e0db018570623c85f3d925ac718db4e06d0187adb21c1"
SRC_URI[bitmaps-2.1.0.sha256sum] = "031043d04099746d8db04daf1fa424b2bc8bd69d92b25962dcde24da39ab64a2"
SRC_URI[block-buffer-0.10.4.sha256sum] = "3078c7629b62d3f0439517fa394996acacc5cbc91c5a20d8c658e77abd503a71"
SRC_URI[bstr-1.9.1.sha256sum] = "05efc5cfd9110c8416e471df0e96702d58690178e206e61b7173706673c93706"
SRC_URI[bumpalo-3.15.4.sha256sum] = "7ff69b9dd49fd426c69a0db9fc04dd934cdb6645ff000864d98f7e2af8830eaa"
SRC_URI[bytes-1.10.0.sha256sum] = "f61dac84819c6588b558454b194026eb1f09c293b9036ae9b159e74e73ab6cf9"
SRC_URI[camino-1.1.9.sha256sum] = "8b96ec4966b5813e2c0507c1f86115c8c5abaadc3980879c3424042a02fd1ad3"
SRC_URI[capnp-0.20.3.sha256sum] = "bce4e2d41c16cf9188f47ca4d59fdcdca1f33705af211bdb41f0afbd3442f8b5"
SRC_URI[capnpc-0.20.1.sha256sum] = "1aa3d5f01e69ed11656d2c7c47bf34327ea9bfb5c85c7de787fcd7b6c5e45b61"
SRC_URI[cc-1.2.16.sha256sum] = "be714c154be609ec7f5dad223a33bf1482fff90472de28f7362806e6d4832b8c"
SRC_URI[cfg-if-1.0.0.sha256sum] = "baf1de4339761588bc0619e3cbc0120ee582ebb74b53b4efbf79117bd2da40fd"
SRC_URI[cfg_aliases-0.2.1.sha256sum] = "613afe47fcd5fac7ccf1db93babcb082c5994d996f20b8b159f2ad1658eb5724"
SRC_URI[chacha20-0.9.1.sha256sum] = "c3613f74bd2eac03dad61bd53dbe620703d4371614fe0bc3b9f04dd36fe4e818"
SRC_URI[chacha20poly1305-0.10.1.sha256sum] = "10cd79432192d1c0f4e1a0fef9527696cc039165d729fb41b3f4f4f354c2dc35"
SRC_URI[cipher-0.4.4.sha256sum] = "773f3b9af64447d2ce9850330c473515014aa235e6a783b02db81ff39e4a3dad"
SRC_URI[clap-4.5.16.sha256sum] = "ed6719fffa43d0d87e5fd8caeab59be1554fb028cd30edc88fc4369b17971019"
SRC_URI[clap_builder-4.5.15.sha256sum] = "216aec2b177652e3846684cbfe25c9964d18ec45234f0f5da5157b207ed1aab6"
SRC_URI[clap_derive-4.5.13.sha256sum] = "501d359d5f3dcaf6ecdeee48833ae73ec6e42723a1e52419c79abf9507eec0a0"
SRC_URI[clap_lex-0.7.0.sha256sum] = "98cc8fbded0c607b7ba9dd60cd98df59af97e84d24e49c8557331cfc26d301ce"
SRC_URI[codespan-reporting-0.11.1.sha256sum] = "3538270d33cc669650c4b093848450d380def10c331d38c768e34cac80576e6e"
SRC_URI[colorchoice-1.0.0.sha256sum] = "acbf1af155f9b9ef647e42cdc158db4b64a1b61f743629225fde6f3e0be2a7c7"
SRC_URI[console-0.15.8.sha256sum] = "0e1f83fc076bd6dd27517eacdf25fef6c4dfe5f1d7448bafaaf3a26f13b5e4eb"
SRC_URI[console_error_panic_hook-0.1.7.sha256sum] = "a06aeb73f470f66dcdbf7223caeebb85984942f22f1adb2a088cf9668146bbbc"
SRC_URI[cookie-factory-0.3.3.sha256sum] = "9885fa71e26b8ab7855e2ec7cae6e9b380edff76cd052e07c683a0319d51b3a2"
SRC_URI[cpufeatures-0.2.12.sha256sum] = "53fe5e26ff1b7aef8bca9c6080520cfb8d9333c7568e1829cef191a9723e5504"
SRC_URI[crc32fast-1.4.0.sha256sum] = "b3855a8a784b474f333699ef2bbca9db2c4a1f6d9088a90a2d25b1eb53111eaa"
SRC_URI[crossbeam-channel-0.5.12.sha256sum] = "ab3db02a9c5b5121e1e42fbdb1aeb65f5e02624cc58c43f2884c6ccac0b82f95"
SRC_URI[crossbeam-deque-0.8.5.sha256sum] = "613f8cc01fe9cf1a3eb3d7f488fd2fa8388403e97039e2f73692932e291a770d"
SRC_URI[crossbeam-epoch-0.9.18.sha256sum] = "5b82ac4a3c2ca9c3460964f020e1402edd5753411d7737aa39c3714ad1b5420e"
SRC_URI[crossbeam-utils-0.8.19.sha256sum] = "248e3bacc7dc6baa3b21e405ee045c3047101a49145e7e9eca583ab4c2ca5345"
SRC_URI[crypto-common-0.1.6.sha256sum] = "1bfb12502f3fc46cca1bb51ac28df9d618d813cdc3d2f25b9fe775a34af26bb3"
SRC_URI[ctrlc-3.4.5.sha256sum] = "90eeab0aa92f3f9b4e87f258c72b139c207d251f9cbc1080a0086b86a8870dd3"
SRC_URI[curve25519-dalek-4.1.3.sha256sum] = "97fb8b7c4503de7d6ae7b42ab72a5a59857b4c937ec27a3d4539dba95b5ab2be"
SRC_URI[curve25519-dalek-derive-0.1.1.sha256sum] = "f46882e17999c6cc590af592290432be3bce0428cb0d5f8b6715e4dc7b383eb3"
SRC_URI[dashmap-6.1.0.sha256sum] = "5041cc499144891f3790297212f32a74fb938e5136a14943f338ef9e0ae276cf"
SRC_URI[data-encoding-2.5.0.sha256sum] = "7e962a19be5cfc3f3bf6dd8f61eb50107f356ad6270fbb3ed41476571db78be5"
SRC_URI[dbus-0.9.7.sha256sum] = "1bb21987b9fb1613058ba3843121dd18b163b254d8a6e797e144cbac14d96d1b"
SRC_URI[debug-ignore-1.0.5.sha256sum] = "ffe7ed1d93f4553003e20b629abe9085e1e81b1429520f897f8f8860bc6dfc21"
SRC_URI[der-parser-8.2.0.sha256sum] = "dbd676fbbab537128ef0278adb5576cf363cff6aa22a7b24effe97347cfab61e"
SRC_URI[deranged-0.3.11.sha256sum] = "b42b6fa04a440b495c8b04d0e71b707c585f83cb9cb28cf8cd0d976c315e31b4"
SRC_URI[diff-0.1.13.sha256sum] = "56254986775e3233ffa9c4d7d3faaf6d36a2c09d30b20687e9f88bc8bafc16c8"
SRC_URI[digest-0.10.7.sha256sum] = "9ed9a281f7bc9b7576e61468ba615a66a5c8cfdff42420a70aa82701a3b1e292"
SRC_URI[dirs-next-2.0.0.sha256sum] = "b98cf8ebf19c3d1b223e151f99a4f9f0690dca41414773390fc824184ac833e1"
SRC_URI[dirs-sys-next-0.1.2.sha256sum] = "4ebda144c4fe02d1f7ea1a7d9641b6fc6b580adcfa024ae48797ecdeb6825b4d"
SRC_URI[displaydoc-0.2.4.sha256sum] = "487585f4d0c6655fe74905e2504d8ad6908e4db67f744eb140876906c2f3175d"
SRC_URI[ecow-0.2.3.sha256sum] = "e42fc0a93992b20c58b99e59d61eaf1635a25bfbe49e4275c34ba0aee98119ba"
SRC_URI[either-1.10.0.sha256sum] = "11157ac094ffbdde99aa67b23417ebdd801842852b500e395a45a9c0aac03e4a"
SRC_URI[embedded-io-0.6.1.sha256sum] = "edd0f118536f44f5ccd48bcb8b111bdc3de888b58c74639dfb034a357d0f206d"
SRC_URI[encode_unicode-0.3.6.sha256sum] = "a357d28ed41a50f9c765dbfe56cbc04a64e53e5fc58ba79fbc34c10ef3df831f"
SRC_URI[endian-type-0.1.2.sha256sum] = "c34f04666d835ff5d62e058c3995147c06f42fe86ff053337632bca83e42702d"
SRC_URI[equivalent-1.0.1.sha256sum] = "5443807d6dff69373d433ab9ef5378ad8df50ca6298caf15de6e52e24aaf54d5"
SRC_URI[errno-0.3.8.sha256sum] = "a258e46cdc063eb8519c00b9fc845fc47bcfca4130e2f08e88665ceda8474245"
SRC_URI[fastrand-2.0.2.sha256sum] = "658bd65b1cf4c852a3cc96f18a8ce7b5640f6b703f905c7d74532294c2a63984"
SRC_URI[fiat-crypto-0.2.9.sha256sum] = "28dea519a9695b9977216879a3ebfddf92f1c08c05d984f8996aecd6ecdc811d"
SRC_URI[filetime-0.2.23.sha256sum] = "1ee447700ac8aa0b2f2bd7bc4462ad686ba06baa6727ac149a2d6277f0d240fd"
SRC_URI[find-crate-0.6.3.sha256sum] = "59a98bbaacea1c0eb6a0876280051b892eb73594fd90cf3b20e9c817029c57d2"
SRC_URI[fixedbitset-0.4.2.sha256sum] = "0ce7134b9999ecaf8bcd65542e436736ef32ddca1b3e06094cb6ec5755203b80"
SRC_URI[flate2-1.0.30.sha256sum] = "5f54427cfd1c7829e2a139fcefea601bf088ebca651d2bf53ebc600eac295dae"
SRC_URI[fluent-0.16.1.sha256sum] = "bb74634707bebd0ce645a981148e8fb8c7bccd4c33c652aeffd28bf2f96d555a"
SRC_URI[fluent-bundle-0.15.3.sha256sum] = "7fe0a21ee80050c678013f82edf4b705fe2f26f1f9877593d13198612503f493"
SRC_URI[fluent-langneg-0.13.0.sha256sum] = "2c4ad0989667548f06ccd0e306ed56b61bd4d35458d54df5ec7587c0e8ed5e94"
SRC_URI[fluent-syntax-0.11.1.sha256sum] = "2a530c4694a6a8d528794ee9bbd8ba0122e779629ac908d15ad5a7ae7763a33d"
SRC_URI[fnv-1.0.7.sha256sum] = "3f9eec918d3f24069decb9af1554cad7c880e2da24a9afd88aca000531ab82c1"
SRC_URI[form_urlencoded-1.2.1.sha256sum] = "e13624c2627564efccf4934284bdd98cbaa14e79b0b5a141218e507b3a823456"
SRC_URI[fs_extra-1.3.0.sha256sum] = "42703706b716c37f96a77aea830392ad231f44c9e9a67872fa5548707e11b11c"
SRC_URI[fslock-0.2.1.sha256sum] = "04412b8935272e3a9bae6f48c7bfff74c2911f60525404edfdd28e49884c3bfb"
SRC_URI[futures-0.3.31.sha256sum] = "65bc07b1a8bc7c85c5f2e110c476c7389b4554ba72af57d8445ea63a576b0876"
SRC_URI[futures-channel-0.3.31.sha256sum] = "2dff15bf788c671c1934e366d07e30c1814a8ef514e1af724a602e8a2fbe1b10"
SRC_URI[futures-core-0.3.31.sha256sum] = "05f29059c0c2090612e8d742178b0580d2dc940c837851ad723096f87af6663e"
SRC_URI[futures-executor-0.3.31.sha256sum] = "1e28d1d997f585e54aebc3f97d39e72338912123a67330d723fdbb564d646c9f"
SRC_URI[futures-io-0.3.31.sha256sum] = "9e5c1b78ca4aae1ac06c48a526a655760685149f0d465d21f37abfe57ce075c6"
SRC_URI[futures-macro-0.3.31.sha256sum] = "162ee34ebcb7c64a8abebc059ce0fee27c2262618d7b60ed8faf72fef13c3650"
SRC_URI[futures-sink-0.3.31.sha256sum] = "e575fab7d1e0dcb8d0c7bcf9a63ee213816ab51902e6d244a95819acacf1d4f7"
SRC_URI[futures-task-0.3.31.sha256sum] = "f90f7dce0722e95104fcb095585910c0977252f286e354b5e3bd38902cd99988"
SRC_URI[futures-util-0.3.31.sha256sum] = "9fa08315bb612088cc391249efdc3bc77536f16c91f6cf495e6fbe85b20a4a81"
SRC_URI[generic-array-0.14.7.sha256sum] = "85649ca51fd72272d7821adaf274ad91c288277713d9c18820d8499a7ff69e9a"
SRC_URI[getrandom-0.2.15.sha256sum] = "c4567c8db10ae91089c99af84c68c38da3ec2f087c3f82960bcdbf3656b6f4d7"
SRC_URI[gimli-0.29.0.sha256sum] = "40ecd4077b5ae9fd2e9e169b102c6c330d0605168eb0e8bf79952b256dbefffd"
SRC_URI[globset-0.4.16.sha256sum] = "54a1028dfc5f5df5da8a56a73e6c153c9a9708ec57232470703592a3f18e49f5"
SRC_URI[hashbrown-0.14.3.sha256sum] = "290f1a1d9242c78d09ce40a5e87e7554ee637af1351968159f4952f028f75604"
SRC_URI[heck-0.5.0.sha256sum] = "2304e00983f87ffb38b55b444b5e3b60a884b5d30c0fca7d82fe33449bbe55ea"
SRC_URI[hermit-abi-0.3.9.sha256sum] = "d231dfb89cfffdbc30e7fc41579ed6066ad03abda9e567ccafae602b97ec5024"
SRC_URI[hexpm-3.3.0.sha256sum] = "0d4ea53d065cbbcc44f7a5f1814ae2c526d934c5344b97caf2953125aea28e79"
SRC_URI[hkdf-0.12.4.sha256sum] = "7b5f8eb2ad728638ea2c7d47a21db23b7b58a72ed6a38256b8a1849f15fbbdf7"
SRC_URI[hmac-0.12.1.sha256sum] = "6c49c37c09c17a53d937dfbb742eb3a961d65a994e6bcdcf37e7399d0cc8ab5e"
SRC_URI[hostname-0.4.0.sha256sum] = "f9c7c7c8ac16c798734b8a24560c1362120597c40d5e1459f09498f8f6c8f2ba"
SRC_URI[http-1.2.0.sha256sum] = "f16ca2af56261c99fba8bac40a10251ce8188205a4c448fbb745a2e4daa76fea"
SRC_URI[http-auth-basic-0.3.3.sha256sum] = "dd2e17aacf7f4a2428def798e2ff4f4f883c0987bdaf47dd5c8bc027bc9f1ebc"
SRC_URI[http-body-1.0.0.sha256sum] = "1cac85db508abc24a2e48553ba12a996e87244a0395ce011e62b37158745d643"
SRC_URI[http-body-util-0.1.1.sha256sum] = "0475f8b2ac86659c21b64320d5d653f9efe42acd2a4e560073ec61a155a34f1d"
SRC_URI[httparse-1.8.0.sha256sum] = "d897f394bad6a705d5f4104762e116a75639e470d80901eed05a860a95cb1904"
SRC_URI[humansize-2.1.3.sha256sum] = "6cb51c9a029ddc91b07a787f1d86b53ccfa49b0e86688c946ebe8d3555685dd7"
SRC_URI[hyper-1.5.2.sha256sum] = "256fb8d4bd6413123cc9d91832d78325c48ff41677595be797d90f42969beae0"
SRC_URI[hyper-rustls-0.27.2.sha256sum] = "5ee4be2c948921a1a5320b629c4193916ed787a7f7f293fd3f7f5a6c9de74155"
SRC_URI[hyper-util-0.1.10.sha256sum] = "df2dcfbe0677734ab2f3ffa7fa7bfd4706bfdc1ef393f2ee30184aed67e631b4"
SRC_URI[i18n-config-0.4.7.sha256sum] = "8e88074831c0be5b89181b05e6748c4915f77769ecc9a4c372f88b169a8509c9"
SRC_URI[i18n-embed-0.15.2.sha256sum] = "a7839d8c7bb8da7bd58c1112d3a1aeb7f178ff3df4ae87783e758ca3bfb750b7"
SRC_URI[i18n-embed-fl-0.9.2.sha256sum] = "f6e9571c3cba9eba538eaa5ee40031b26debe76f0c7e17bafc97ea57a76cd82e"
SRC_URI[i18n-embed-impl-0.8.4.sha256sum] = "0f2cc0e0523d1fe6fc2c6f66e5038624ea8091b3e7748b5e8e0c84b1698db6c2"
SRC_URI[icu_collections-1.5.0.sha256sum] = "db2fa452206ebee18c4b5c2274dbf1de17008e874b4dc4f0aea9d01ca79e4526"
SRC_URI[icu_locid-1.5.0.sha256sum] = "13acbb8371917fc971be86fc8057c41a64b521c184808a698c02acc242dbf637"
SRC_URI[icu_locid_transform-1.5.0.sha256sum] = "01d11ac35de8e40fdeda00d9e1e9d92525f3f9d887cdd7aa81d727596788b54e"
SRC_URI[icu_locid_transform_data-1.5.0.sha256sum] = "fdc8ff3388f852bede6b579ad4e978ab004f139284d7b28715f773507b946f6e"
SRC_URI[icu_normalizer-1.5.0.sha256sum] = "19ce3e0da2ec68599d193c93d088142efd7f9c5d6fc9b803774855747dc6a84f"
SRC_URI[icu_normalizer_data-1.5.0.sha256sum] = "f8cafbf7aa791e9b22bec55a167906f9e1215fd475cd22adfcf660e03e989516"
SRC_URI[icu_properties-1.5.1.sha256sum] = "93d6020766cfc6302c15dbbc9c8778c37e62c14427cb7f6e601d849e092aeef5"
SRC_URI[icu_properties_data-1.5.0.sha256sum] = "67a8effbc3dd3e4ba1afa8ad918d5684b8868b3b26500753effea8d2eed19569"
SRC_URI[icu_provider-1.5.0.sha256sum] = "6ed421c8a8ef78d3e2dbc98a973be2f3770cb42b606e3ab18d6237c4dfde68d9"
SRC_URI[icu_provider_macros-1.5.0.sha256sum] = "1ec89e9337638ecdc08744df490b221a7399bf8d164eb52a665454e60e075ad6"
SRC_URI[id-arena-2.2.1.sha256sum] = "25a2bc672d1148e28034f176e01fffebb08b35768468cc954630da77a1449005"
SRC_URI[idna-1.0.3.sha256sum] = "686f825264d630750a544639377bae737628043f20d38bbc029e8f29ea968a7e"
SRC_URI[idna_adapter-1.2.0.sha256sum] = "daca1df1c957320b2cf139ac61e7bd64fed304c5040df000a745aa1de3b4ef71"
SRC_URI[ignore-0.4.22.sha256sum] = "b46810df39e66e925525d6e38ce1e7f6e1d208f72dc39757880fcb66e2c58af1"
SRC_URI[im-15.1.0.sha256sum] = "d0acd33ff0285af998aaf9b57342af478078f53492322fafc47450e09397e0e9"
SRC_URI[indexmap-2.2.6.sha256sum] = "168fb715dda47215e360912c096649d23d58bf392ac62f73919e831745e40f26"
SRC_URI[inout-0.1.3.sha256sum] = "a0c10553d664a4d0bcff9f4215d0aac67a639cc68ef660840afe309b807bc9f5"
SRC_URI[insta-1.41.1.sha256sum] = "7e9ffc4d4892617c50a928c52b2961cb5174b6fc6ebf252b2fac9d21955c48b8"
SRC_URI[intl-memoizer-0.5.2.sha256sum] = "fe22e020fce238ae18a6d5d8c502ee76a52a6e880d99477657e6acc30ec57bda"
SRC_URI[intl_pluralrules-7.0.2.sha256sum] = "078ea7b7c29a2b4df841a7f6ac8775ff6074020c6776d48491ce2268e068f972"
SRC_URI[io_tee-0.1.1.sha256sum] = "4b3f7cef34251886990511df1c61443aa928499d598a9473929ab5a90a527304"
SRC_URI[ipnet-2.9.0.sha256sum] = "8f518f335dce6725a761382244631d86cf0ccb2863413590b31338feb467f9c3"
SRC_URI[is_terminal_polyfill-1.70.0.sha256sum] = "f8478577c03552c21db0e2724ffb8986a5ce7af88107e6be5d2ee6e158c12800"
SRC_URI[itertools-0.13.0.sha256sum] = "413ee7dfc52ee1a4949ceeb7dbc8a33f2d6c088194d9f922fb8318faf1f01186"
SRC_URI[itoa-1.0.11.sha256sum] = "49f1f14873335454500d59611f1cf4a4b0f786f9ac11f4312a78e4cf2566695b"
SRC_URI[js-sys-0.3.69.sha256sum] = "29c15563dc2726973df627357ce0c9ddddbea194836909d655df6a75d2cf296d"
SRC_URI[lazy_static-1.4.0.sha256sum] = "e2abad23fbc42b3700f2f279844dc832adb2b2eb069b2df918f455c4e18cc646"
SRC_URI[libc-0.2.155.sha256sum] = "97b3888a4aecf77e811145cadf6eef5901f4782c53886191b2f693f24761847c"
SRC_URI[libdbus-sys-0.2.5.sha256sum] = "06085512b750d640299b79be4bad3d2fa90a9c00b1fd9e1b46364f66f0485c72"
SRC_URI[libm-0.2.8.sha256sum] = "4ec2a862134d2a7d32d7983ddcdd1c4923530833c9f2ea1a44fc5fa473989058"
SRC_URI[libredox-0.1.3.sha256sum] = "c0ff37bd590ca25063e35af745c343cb7a0271906fb7b37e4813e8f79f00268d"
SRC_URI[linked-hash-map-0.5.6.sha256sum] = "0717cef1bc8b636c6e1c1bbdefc09e6322da8a9321966e8928ef80d20f7f770f"
SRC_URI[linux-raw-sys-0.4.13.sha256sum] = "01cda141df6706de531b6c46c3a33ecca755538219bd484262fa09410c13539c"
SRC_URI[litemap-0.7.4.sha256sum] = "4ee93343901ab17bd981295f2cf0026d4ad018c7c31ba84549a4ddbb47a45104"
SRC_URI[lock_api-0.4.12.sha256sum] = "07af8b9cdd281b7915f413fa73f29ebd5d55d0d3f0155584dade1ff18cea1b17"
SRC_URI[log-0.4.22.sha256sum] = "a7a70ba024b9dc04c27ea2f0c0548feb474ec5c54bba33a7f72f873a39d07b24"
SRC_URI[lsp-server-0.7.7.sha256sum] = "550446e84739dcaf6d48a4a093973850669e13e8a34d8f8d64851041be267cd9"
SRC_URI[lsp-types-0.95.1.sha256sum] = "8e34d33a8e9b006cd3fc4fe69a921affa097bae4bb65f76271f4644f9a334365"
SRC_URI[matchers-0.1.0.sha256sum] = "8263075bb86c5a1b1427b5ae862e8889656f126e9f77c484496e8b47cf5c5558"
SRC_URI[memchr-2.7.2.sha256sum] = "6c8640c5d730cb13ebd907d8d04b52f55ac9a2eec55b440c8892f40d56c76c1d"
SRC_URI[mime-0.3.17.sha256sum] = "6877bb514081ee2a7ff5ef9de3281f14a4dd4bceac4c09388074a6b5df8a139a"
SRC_URI[mime_guess-2.0.4.sha256sum] = "4192263c238a5f0d0c6bfd21f336a313a4ce1c450542449ca191bb657b4642ef"
SRC_URI[minimal-lexical-0.2.1.sha256sum] = "68354c5c6bd36d73ff3feceb05efa59b6acb7626617f4962be322a825e61f79a"
SRC_URI[miniz_oxide-0.7.2.sha256sum] = "9d811f3e15f28568be3407c8e7fdb6514c1cda3cb30683f15b6a1a1dc4ea14a7"
SRC_URI[mio-1.0.1.sha256sum] = "4569e456d394deccd22ce1c1913e6ea0e54519f577285001215d33557431afe4"
SRC_URI[multimap-0.10.0.sha256sum] = "defc4c55412d89136f966bbb339008b474350e5e6e78d2714439c386b3137a03"
SRC_URI[nibble_vec-0.1.0.sha256sum] = "77a5d83df9f36fe23f0c3648c6bbb8b0298bb5f1939c8f2704431371f4b84d43"
SRC_URI[nix-0.29.0.sha256sum] = "71e2746dc3a24dd78b3cfcb7be93368c6de9963d30f43a6a73998a9cf4b17b46"
SRC_URI[nom-7.1.3.sha256sum] = "d273983c5a657a70a3e8f2a01329822f3b8c8172b73826411a55751e404a0a4a"
SRC_URI[normpath-1.2.0.sha256sum] = "5831952a9476f2fed74b77d74182fa5ddc4d21c72ec45a333b250e3ed0272804"
SRC_URI[nu-ansi-term-0.46.0.sha256sum] = "77a8165726e8236064dbb45459242600304b42a5ea24ee2948e18e023bf7ba84"
SRC_URI[num-bigint-0.4.6.sha256sum] = "a5e44f723f1133c9deac646763579fdb3ac745e418f2a7af9cd0c431da1f20b9"
SRC_URI[num-conv-0.1.0.sha256sum] = "51d515d32fb182ee37cda2ccdcb92950d6a3c2893aa280e540671c2cd0f3b1d9"
SRC_URI[num-integer-0.1.46.sha256sum] = "7969661fd2958a5cb096e56c8e1ad0444ac2bbcd0061bd28660485a44879858f"
SRC_URI[num-traits-0.2.19.sha256sum] = "071dfc062690e90b734c0b2273ce72ad0ffa95f0c74596bc250dcfd960262841"
SRC_URI[object-0.36.1.sha256sum] = "081b846d1d56ddfc18fdf1a922e4f6e07a11768ea1b92dec44e42b72712ccfce"
SRC_URI[oid-registry-0.6.1.sha256sum] = "9bedf36ffb6ba96c2eb7144ef6270557b52e54b20c0a8e1eb2ff99a6c6959bff"
SRC_URI[once_cell-1.19.0.sha256sum] = "3fdb12b2476b595f9358c5161aa467c2438859caa136dec86c26fdd2efe17b92"
SRC_URI[opaque-debug-0.3.1.sha256sum] = "c08d65885ee38876c4f86fa503fb49d7b507c2b62552df7c70b2fce627e06381"
SRC_URI[opener-0.7.2.sha256sum] = "d0812e5e4df08da354c851a3376fead46db31c2214f849d3de356d774d057681"
SRC_URI[overload-0.1.1.sha256sum] = "b15813163c1d831bf4a13c3610c05c0d03b39feb07f7e09fa234dac9b15aaf39"
SRC_URI[parking_lot-0.12.3.sha256sum] = "f1bf18183cf54e8d6059647fc3063646a1801cf30896933ec2311622cc4b9a27"
SRC_URI[parking_lot_core-0.9.10.sha256sum] = "1e401f977ab385c9e4e3ab30627d6f26d00e2c73eef317493c4ec6d468726cf8"
SRC_URI[pathdiff-0.2.3.sha256sum] = "df94ce210e5bc13cb6651479fa48d14f601d9858cfe0467f43ae157023b938d3"
SRC_URI[pbkdf2-0.12.2.sha256sum] = "f8ed6a7761f76e3b9f92dfb0a60a6a6477c61024b775147ff0973a02653abaf2"
SRC_URI[percent-encoding-2.3.1.sha256sum] = "e3148f5046208a5d56bcfc03053e3ca6334e51da8dfb19b6cdc8b306fae3283e"
SRC_URI[petgraph-0.6.5.sha256sum] = "b4c5cc86750666a3ed20bdaf5ca2a0344f9c67674cae0515bec2da16fbaa47db"
SRC_URI[pin-project-1.1.5.sha256sum] = "b6bf43b791c5b9e34c3d182969b4abb522f9343702850a2e57f460d00d09b4b3"
SRC_URI[pin-project-internal-1.1.5.sha256sum] = "2f38a4412a78282e09a2cf38d195ea5420d15ba0602cb375210efbc877243965"
SRC_URI[pin-project-lite-0.2.14.sha256sum] = "bda66fc9667c18cb2758a2ac84d1167245054bcf85d5d1aaa6923f45801bdd02"
SRC_URI[pin-utils-0.1.0.sha256sum] = "8b870d8c151b6f2fb93e84a13146138f05d02ed11c7e7c54f8826aaaf7c9f184"
SRC_URI[pkg-config-0.3.30.sha256sum] = "d231b230927b5e4ad203db57bbcbee2802f6bce620b1e4a9024a07d94e2907ec"
SRC_URI[poly1305-0.8.0.sha256sum] = "8159bd90725d2df49889a078b54f4f79e87f1f8a8444194cdca81d38f5393abf"
SRC_URI[powerfmt-0.2.0.sha256sum] = "439ee305def115ba05938db6eb1644ff94165c5ab5e9420d1c1bcedbba909391"
SRC_URI[ppv-lite86-0.2.17.sha256sum] = "5b40af805b3121feab8a3c29f04d8ad262fa8e0561883e7653e024ae4479e6de"
SRC_URI[pretty_assertions-1.4.1.sha256sum] = "3ae130e2f271fbc2ac3a40fb1d07180839cdbbe443c7a27e1e3c13c5cac0116d"
SRC_URI[prettyplease-0.2.17.sha256sum] = "8d3928fb5db768cb86f891ff014f0144589297e3c6a1aba6ed7cecfdace270c7"
SRC_URI[proc-macro-error-attr2-2.0.0.sha256sum] = "96de42df36bb9bba5542fe9f1a054b8cc87e172759a1868aa05c1f3acc89dfc5"
SRC_URI[proc-macro-error2-2.0.1.sha256sum] = "11ec05c52be0a07b08061f7dd003e7d7092e0472bc731b4af7bb1ef876109802"
SRC_URI[proc-macro2-1.0.79.sha256sum] = "e835ff2298f5721608eb1a980ecaee1aef2c132bf95ecc026a11b7bf3c01c02e"
SRC_URI[prost-0.13.5.sha256sum] = "2796faa41db3ec313a31f7624d9286acf277b52de526150b7e69f3debf891ee5"
SRC_URI[prost-build-0.13.5.sha256sum] = "be769465445e8c1474e9c5dac2018218498557af32d9ed057325ec9a41ae81bf"
SRC_URI[prost-derive-0.13.5.sha256sum] = "8a56d757972c98b346a9b766e3f02746cde6dd1cd1d1d563472929fdd74bec4d"
SRC_URI[prost-types-0.13.5.sha256sum] = "52c2c1bf36ddb1a1c396b3601a3cec27c2462e45f07c386894ec3ccf5332bd16"
SRC_URI[pubgrub-0.2.1.sha256sum] = "cdd14552ad5f5d743a323c10d576f26822a044355d6601f377d813ece46f38fd"
SRC_URI[pulldown-cmark-0.13.0.sha256sum] = "1e8bbe1a966bd2f362681a44f6edce3c2310ac21e4d5067a6e7ec396297a6ea0"
SRC_URI[pulldown-cmark-escape-0.11.0.sha256sum] = "007d8adb5ddab6f8e3f491ac63566a7d5002cc7ed73901f72057943fa71ae1ae"
SRC_URI[quinn-0.11.2.sha256sum] = "e4ceeeeabace7857413798eb1ffa1e9c905a9946a57d81fb69b4b71c4d8eb3ad"
SRC_URI[quinn-proto-0.11.8.sha256sum] = "fadfaed2cd7f389d0161bb73eeb07b7b78f8691047a6f3e73caaeae55310a4a6"
SRC_URI[quinn-udp-0.5.4.sha256sum] = "8bffec3605b73c6f1754535084a85229fa8a30f86014e6c81aeec4abb68b0285"
SRC_URI[quote-1.0.35.sha256sum] = "291ec9ab5efd934aaf503a6466c5d5251535d108ee747472c3977cc5acc868ef"
SRC_URI[radix_trie-0.2.1.sha256sum] = "c069c179fcdc6a2fe24d8d18305cf085fdbd4f922c041943e203685d6a1c58fd"
SRC_URI[rand-0.8.5.sha256sum] = "34af8d1a0e25924bc5b7c43c079c942339d8f0a8b57c39049bef581b46327404"
SRC_URI[rand_chacha-0.3.1.sha256sum] = "e6c10a63a0fa32252be49d21e7709d4d4baf8d231c2dbce1eaa8141b9b127d88"
SRC_URI[rand_core-0.6.4.sha256sum] = "ec0be4795e2f6a28069bec0b5ff3e2ac9bafc99e6a9a7dc3547996c5c816922c"
SRC_URI[rand_xoshiro-0.6.0.sha256sum] = "6f97cdb2a36ed4183de61b2f824cc45c9f1037f28afe0a322e9fff4c108b5aaa"
SRC_URI[redox_syscall-0.4.1.sha256sum] = "4722d768eff46b75989dd134e5c353f0d6296e5aaa3132e776cbdb56be7731aa"
SRC_URI[redox_syscall-0.5.7.sha256sum] = "9b6dfecf2c74bce2466cabf93f6664d6998a69eb21e39f4207930065b27b771f"
SRC_URI[redox_users-0.4.5.sha256sum] = "bd283d9651eeda4b2a83a43c1c91b266c40fd76ecd39a50a8c630ae69dc72891"
SRC_URI[regex-1.10.5.sha256sum] = "b91213439dad192326a0d7c6ee3955910425f441d7038e0d6933b0aec5c4517f"
SRC_URI[regex-automata-0.1.10.sha256sum] = "6c230d73fb8d8c1b9c0b3135c5142a8acee3a0558fb8db5cf1cb65f8d7862132"
SRC_URI[regex-automata-0.4.6.sha256sum] = "86b83b8b9847f9bf95ef68afb0b8e6cdb80f498442f5179a29fad448fcc1eaea"
SRC_URI[regex-syntax-0.6.29.sha256sum] = "f162c6dd7b008981e4d40210aca20b4bd0f9b60ca9271061b07f78537722f2e1"
SRC_URI[regex-syntax-0.8.3.sha256sum] = "adad44e29e4c806119491a7f06f03de4d1af22c3a680dd47f1e6e179439d1f56"
SRC_URI[reqwest-0.12.12.sha256sum] = "43e734407157c3c2034e0258f5e4473ddb361b1e85f95a66690d67264d7cd1da"
SRC_URI[ring-0.17.13.sha256sum] = "70ac5d832aa16abd7d1def883a8545280c20a60f523a370aa3a9617c2b8550ee"
SRC_URI[rpassword-7.3.1.sha256sum] = "80472be3c897911d0137b2d2b9055faf6eeac5b14e324073d83bc17b191d7e3f"
SRC_URI[rtoolbox-0.0.2.sha256sum] = "c247d24e63230cdb56463ae328478bd5eac8b8faa8c69461a77e8e323afac90e"
SRC_URI[rust-embed-8.5.0.sha256sum] = "fa66af4a4fdd5e7ebc276f115e895611a34739a9c1c01028383d612d550953c0"
SRC_URI[rust-embed-impl-8.5.0.sha256sum] = "6125dbc8867951125eec87294137f4e9c2c96566e61bf72c45095a7c77761478"
SRC_URI[rust-embed-utils-8.5.0.sha256sum] = "2e5347777e9aacb56039b0e1f28785929a8a3b709e87482e7442c72e7c12529d"
SRC_URI[rustc-demangle-0.1.24.sha256sum] = "719b953e2095829ee67db738b3bfa9fa368c94900df327b3f07fe6e794d2fe1f"
SRC_URI[rustc-hash-1.1.0.sha256sum] = "08d43f7aa6b08d49f382cde6a7982047c3426db949b1424bc4b7ec9ae12c6ce2"
SRC_URI[rustc-hash-2.0.0.sha256sum] = "583034fd73374156e66797ed8e5b0d5690409c9226b22d87cb7f19821c05d152"
SRC_URI[rustc_version-0.4.1.sha256sum] = "cfcb3a22ef46e85b45de6ee7e79d063319ebb6594faafcf1c225ea92ab6e9b92"
SRC_URI[rusticata-macros-4.1.0.sha256sum] = "faf0c4a6ece9950b9abdb62b1cfcf2a68b3b67a10ba445b3bb85be2a293d0632"
SRC_URI[rustix-0.38.32.sha256sum] = "65e04861e65f21776e67888bfbea442b3642beaa0138fdb1dd7a84a52dffdb89"
SRC_URI[rustls-0.23.7.sha256sum] = "ebbbdb961df0ad3f2652da8f3fdc4b36122f568f968f45ad3316f26c025c677b"
SRC_URI[rustls-pemfile-2.1.2.sha256sum] = "29993a25686778eb88d4189742cd713c9bce943bc54251a33509dc63cbacf73d"
SRC_URI[rustls-pki-types-1.4.1.sha256sum] = "ecd36cc4259e3e4514335c4a138c6b43171a8d61d8f5c9348f9fc7529416f247"
SRC_URI[rustls-webpki-0.102.2.sha256sum] = "faaa0a62740bedb9b2ef5afa303da42764c012f743917351dc9a237ea1663610"
SRC_URI[rustversion-1.0.14.sha256sum] = "7ffc183a10b4478d04cbbbfc96d0873219d962dd5accaff2ffbd4ceb7df837f4"
SRC_URI[ryu-1.0.17.sha256sum] = "e86697c916019a8588c99b5fac3cead74ec0b4b819707a682fd4d23fa0ce1ba1"
SRC_URI[salsa20-0.10.2.sha256sum] = "97a22f5af31f73a954c10289c93e8a50cc23d971e80ee446f1f6f7137a088213"
SRC_URI[same-file-1.0.6.sha256sum] = "93fc1dc3aaa9bfed95e02e6eadabb4baf7e3078b0bd1b4d7b6b0b68378900502"
SRC_URI[scoped-tls-1.0.1.sha256sum] = "e1cf6437eb19a8f4a6cc0f7dca544973b0b78843adbfeb3683d1a94a0024a294"
SRC_URI[scopeguard-1.2.0.sha256sum] = "94143f37725109f92c262ed2cf5e59bce7498c01bcc1502d7b9afe439a4e9f49"
SRC_URI[scrypt-0.11.0.sha256sum] = "0516a385866c09368f0b5bcd1caff3366aace790fcd46e2bb032697bb172fd1f"
SRC_URI[secrecy-0.10.3.sha256sum] = "e891af845473308773346dc847b2c23ee78fe442e0472ac50e22a18a93d3ae5a"
SRC_URI[self_cell-0.10.3.sha256sum] = "e14e4d63b804dc0c7ec4a1e52bcb63f02c7ac94476755aa579edac21e01f915d"
SRC_URI[self_cell-1.0.4.sha256sum] = "d369a96f978623eb3dc28807c4852d6cc617fed53da5d3c400feff1ef34a714a"
SRC_URI[semver-1.0.23.sha256sum] = "61697e0a1c7e512e84a621326239844a24d8207b4669b41bc18b32ea5cbf988b"
SRC_URI[serde-1.0.210.sha256sum] = "c8e3592472072e6e22e0a54d5904d9febf8508f65fb8552499a1abc7d1078c3a"
SRC_URI[serde-wasm-bindgen-0.6.5.sha256sum] = "8302e169f0eddcc139c70f139d19d6467353af16f9fce27e8c30158036a1e16b"
SRC_URI[serde_derive-1.0.210.sha256sum] = "243902eda00fad750862fc144cea25caca5e20d615af0a81bee94ca738f1df1f"
SRC_URI[serde_json-1.0.138.sha256sum] = "d434192e7da787e94a6ea7e9670b26a036d0ca41e0b7efb2676dd32bae872949"
SRC_URI[serde_repr-0.1.18.sha256sum] = "0b2e6b945e9d3df726b65d6ee24060aff8e3533d431f677a9695db04eff9dfdb"
SRC_URI[serde_urlencoded-0.7.1.sha256sum] = "d3491c14715ca2294c4d6a88f15e84739788c1d030eed8c110436aafdaa2f3fd"
SRC_URI[sha2-0.10.8.sha256sum] = "793db75ad2bcafc3ffa7c68b215fee268f537982cd901d132f89c6343f3a3dc8"
SRC_URI[sharded-slab-0.1.7.sha256sum] = "f40ca3c46823713e0d4209592e8d6e826aa57e928f09752619fc696c499637f6"
SRC_URI[shlex-1.3.0.sha256sum] = "0fda2ff0d084019ba4d7c6f371c95d8fd75ce3524c3cb8fb653a3023f6323e64"
SRC_URI[similar-2.5.0.sha256sum] = "fa42c91313f1d05da9b26f267f931cf178d4aba455b4c4622dd7355eb80c6640"
SRC_URI[sized-chunks-0.6.5.sha256sum] = "16d69225bde7a69b235da73377861095455d298f2b970996eec25ddbb42b3d1e"
SRC_URI[slab-0.4.9.sha256sum] = "8f92a496fb766b417c996b9c5e57daf2f7ad3b0bebe1ccfca4856390e3d3bb67"
SRC_URI[smallvec-1.13.2.sha256sum] = "3c5e1a9a646d36c3599cd173a41282daf47c44583ad367b8e6837255952e5c67"
SRC_URI[socket2-0.5.6.sha256sum] = "05ffd9c0a93b7543e062e759284fcf5f5e3b098501104bfbdde4d404db792871"
SRC_URI[spdx-0.10.8.sha256sum] = "58b69356da67e2fc1f542c71ea7e654a361a79c938e4424392ecf4fa065d2193"
SRC_URI[stable_deref_trait-1.2.0.sha256sum] = "a8f112729512f8e442d81f95a8a7ddf2b7c6b8a1a6f509a95864142b30cab2d3"
SRC_URI[static_vcruntime-2.0.0.sha256sum] = "954e3e877803def9dc46075bf4060147c55cd70db97873077232eae0269dc89b"
SRC_URI[strsim-0.11.1.sha256sum] = "7da8b5736845d9f2fcb837ea5d9e2628564b3b043a70948a3f0b778838c5fb4f"
SRC_URI[strum-0.26.3.sha256sum] = "8fec0f0aef304996cf250b31b5a10dee7980c85da9d759361292b8bca5a18f06"
SRC_URI[strum_macros-0.26.4.sha256sum] = "4c6bee85a5a24955dc440386795aa378cd9cf82acd5f764469152d2270e581be"
SRC_URI[subtle-2.5.0.sha256sum] = "81cdd64d312baedb58e21336b31bc043b77e01cc99033ce76ef539f78e965ebc"
SRC_URI[syn-1.0.109.sha256sum] = "72b64191b275b66ffe2469e8af2c1cfe3bafa67b529ead792a6d0160888b4237"
SRC_URI[syn-2.0.58.sha256sum] = "44cfb93f38070beee36b3fef7d4f5a16f27751d94b187b666a5cc5e9b0d30687"
SRC_URI[sync_wrapper-1.0.1.sha256sum] = "a7065abeca94b6a8a577f9bd45aa0867a2238b74e8eb67cf10d492bc39351394"
SRC_URI[synstructure-0.12.6.sha256sum] = "f36bdaa60a83aca3921b5259d5400cbf5e90fc51931376a9bd4a0eb79aa7210f"
SRC_URI[synstructure-0.13.1.sha256sum] = "c8af7666ab7b6390ab78131fb5b0fce11d6b7a6951602017c35fa82800708971"
SRC_URI[tar-0.4.43.sha256sum] = "c65998313f8e17d0d553d28f91a0df93e4dbbbf770279c7bc21ca0f09ea1a1f6"
SRC_URI[tempfile-3.12.0.sha256sum] = "04cbcdd0c794ebb0d4cf35e88edd2f7d2c4c3e9a5a6dab322839b321c6a87a64"
SRC_URI[termcolor-1.4.1.sha256sum] = "06794f8f6c5c898b3275aebefa6b8a1cb24cd2c6c79397ab15774837a0bc5755"
SRC_URI[thiserror-1.0.65.sha256sum] = "5d11abd9594d9b38965ef50805c5e469ca9cc6f197f883f717e0269a3057b3d5"
SRC_URI[thiserror-impl-1.0.65.sha256sum] = "ae71770322cbd277e69d762a16c444af02aa0575ac0d174f0b9562d3b37f8602"
SRC_URI[thread_local-1.1.8.sha256sum] = "8b9ef9bad013ada3808854ceac7b46812a6465ba368859a37e2100283d2d719c"
SRC_URI[time-0.3.36.sha256sum] = "5dfd88e563464686c916c7e46e623e520ddc6d79fa6641390f2e3fa86e83e885"
SRC_URI[time-core-0.1.2.sha256sum] = "ef927ca75afb808a4d64dd374f00a2adf8d0fcff8e7b184af886c3c87ec4a3f3"
SRC_URI[time-macros-0.2.18.sha256sum] = "3f252a68540fde3a3877aeea552b832b40ab9a69e318efd078774a01ddee1ccf"
SRC_URI[tinystr-0.7.6.sha256sum] = "9117f5d4db391c1cf6927e7bea3db74b9a1c1add8f7eda9ffd5364f40f57b82f"
SRC_URI[tinyvec-1.6.0.sha256sum] = "87cc5ceb3875bb20c2890005a4e226a4651264a5c75edb2421b52861a0a0cb50"
SRC_URI[tinyvec_macros-0.1.1.sha256sum] = "1f3ccbac311fea05f86f61904b462b55fb3df8837a366dfc601a0161d0532f20"
SRC_URI[tokio-1.42.1.sha256sum] = "2209a14885b74764cce87ffa777ffa1b8ce81a3f3166c6f886b83337fe7e077f"
SRC_URI[tokio-rustls-0.26.0.sha256sum] = "0c7bc40d0e5a97695bb96e27995cd3a08538541b0a846f65bba7a359f36700d4"
SRC_URI[toml-0.5.11.sha256sum] = "f4f7f0dd8d50a853a531c426359045b1998f04219d88799810762cd4ad314234"
SRC_URI[toml_datetime-0.6.8.sha256sum] = "0dd7358ecb8fc2f8d014bf86f6f638ce72ba252a2c3a2572f2a795f1d23efb41"
SRC_URI[toml_edit-0.22.20.sha256sum] = "583c44c02ad26b0c3f3066fe629275e50627026c51ac2e595cca4c230ce1ce1d"
SRC_URI[tower-0.5.2.sha256sum] = "d039ad9159c98b70ecfd540b2573b97f7f52c3e8d9f8ad57a24b916a536975f9"
SRC_URI[tower-layer-0.3.3.sha256sum] = "121c2a6cda46980bb0fcd1647ffaf6cd3fc79a013de288782836f6df9c48780e"
SRC_URI[tower-service-0.3.3.sha256sum] = "8df9b6e13f2d32c91b9bd719c00d1958837bc7dec474d94952798cc8e69eeec3"
SRC_URI[tracing-0.1.40.sha256sum] = "c3523ab5a71916ccf420eebdf5521fcef02141234bbc0b8a49f2fdc4544364ef"
SRC_URI[tracing-attributes-0.1.27.sha256sum] = "34704c8d6ebcbc939824180af020566b01a7c01f80641264eba0999f6c2b6be7"
SRC_URI[tracing-core-0.1.32.sha256sum] = "c06d3da6113f116aaee68e4d601191614c9053067f9ab7f6edbcb161237daa54"
SRC_URI[tracing-log-0.2.0.sha256sum] = "ee855f1f400bd0e5c02d150ae5de3840039a3f54b025156404e34c23c03f47c3"
SRC_URI[tracing-subscriber-0.3.18.sha256sum] = "ad0f048c97dbd9faa9b7df56362b8ebcaa52adb06b498c050d2f4e32f90a7a8b"
SRC_URI[tracing-wasm-0.2.1.sha256sum] = "4575c663a174420fa2d78f4108ff68f65bf2fbb7dd89f33749b6e826b3626e07"
SRC_URI[try-lock-0.2.5.sha256sum] = "e421abadd41a4225275504ea4d6566923418b7f05506fbc9c0fe86ba7396114b"
SRC_URI[type-map-0.5.0.sha256sum] = "deb68604048ff8fa93347f02441e4487594adc20bb8a084f9e564d2b827a0a9f"
SRC_URI[typenum-1.17.0.sha256sum] = "42ff0bf0c66b8238c6f3b578df37d0b7848e55df8577b3f74f92a69acceeb825"
SRC_URI[unic-langid-0.9.5.sha256sum] = "23dd9d1e72a73b25e07123a80776aae3e7b0ec461ef94f9151eed6ec88005a44"
SRC_URI[unic-langid-impl-0.9.5.sha256sum] = "0a5422c1f65949306c99240b81de9f3f15929f5a8bfe05bb44b034cc8bf593e5"
SRC_URI[unicase-2.7.0.sha256sum] = "f7d2d4dafb69621809a81864c9c1b864479e1235c0dd4e199924b9742439ed89"
SRC_URI[unicode-ident-1.0.12.sha256sum] = "3354b9ac3fae1ff6755cb6db53683adb661634f67557942dea4facebec0fee4b"
SRC_URI[unicode-segmentation-1.12.0.sha256sum] = "f6ccf251212114b54433ec949fd6a7841275f9ada20dddd2f29e9ceea4501493"
SRC_URI[unicode-width-0.1.11.sha256sum] = "e51733f11c9c4f72aa0c160008246859e340b00807569a0da0e7a1079b27ba85"
SRC_URI[unicode-xid-0.2.4.sha256sum] = "f962df74c8c05a667b5ee8bcf162993134c104e96440b663c8daa176dc772d8c"
SRC_URI[universal-hash-0.5.1.sha256sum] = "fc1de2c688dc15305988b563c3854064043356019f97a4b46276fe734c4f07ea"
SRC_URI[untrusted-0.9.0.sha256sum] = "8ecb6da28b8a351d773b68d5825ac39017e680750f980f3a1a85cd8dd28a47c1"
SRC_URI[url-2.5.4.sha256sum] = "32f8b686cadd1473f4bd0117a5d28d36b1ade384ea9b5069a1c40aefed7fda60"
SRC_URI[utf16_iter-1.0.5.sha256sum] = "c8232dd3cdaed5356e0f716d285e4b40b932ac434100fe9b7e0e8e935b9e6246"
SRC_URI[utf8_iter-1.0.4.sha256sum] = "b6c140620e7ffbb22c2dee59cafe6084a59b5ffc27a8859a5f0d494b5d52b6be"
SRC_URI[utf8parse-0.2.1.sha256sum] = "711b9620af191e0cdc7468a8d14e709c3dcdb115b36f838e601583af800a370a"
SRC_URI[valuable-0.1.0.sha256sum] = "830b7e5d4d90034032940e4ace0d9a9a057e7a45cd94e6c007832e39edb82f6d"
SRC_URI[vec1-1.12.1.sha256sum] = "eab68b56840f69efb0fefbe3ab6661499217ffdc58e2eef7c3f6f69835386322"
SRC_URI[version_check-0.9.4.sha256sum] = "49874b5167b65d7193b8aba1567f5c7d93d001cafc34600cee003eda787e483f"
SRC_URI[walkdir-2.5.0.sha256sum] = "29790946404f91d9c5d06f9874efddea1dc06c5efe94541a7d6863108e3a5e4b"
SRC_URI[want-0.3.1.sha256sum] = "bfa7760aed19e106de2c7c0b581b509f2f25d3dacaf737cb82ac61bc6d760b0e"
SRC_URI[wasi-0.11.0+wasi-snapshot-preview1.sha256sum] = "9c8d87e72b64a3b4db28d11ce29237c246188f4f51057d65a7eab63b7987e423"
SRC_URI[wasm-bindgen-0.2.99.sha256sum] = "a474f6281d1d70c17ae7aa6a613c87fce69a127e2624002df63dcb39d6cf6396"
SRC_URI[wasm-bindgen-backend-0.2.99.sha256sum] = "5f89bb38646b4f81674e8f5c3fb81b562be1fd936d84320f3264486418519c79"
SRC_URI[wasm-bindgen-futures-0.4.42.sha256sum] = "76bc14366121efc8dbb487ab05bcc9d346b3b5ec0eaa76e46594cabbe51762c0"
SRC_URI[wasm-bindgen-macro-0.2.99.sha256sum] = "2cc6181fd9a7492eef6fef1f33961e3695e4579b9872a6f7c83aee556666d4fe"
SRC_URI[wasm-bindgen-macro-support-0.2.99.sha256sum] = "30d7a95b763d3c45903ed6c81f156801839e5ee968bb07e534c44df0fcd330c2"
SRC_URI[wasm-bindgen-shared-0.2.99.sha256sum] = "943aab3fdaaa029a6e0271b35ea10b72b943135afe9bffca82384098ad0e06a6"
SRC_URI[wasm-bindgen-test-0.3.42.sha256sum] = "d9bf62a58e0780af3e852044583deee40983e5886da43a271dd772379987667b"
SRC_URI[wasm-bindgen-test-macro-0.3.42.sha256sum] = "b7f89739351a2e03cb94beb799d47fb2cac01759b40ec441f7de39b00cbf7ef0"
SRC_URI[web-sys-0.3.69.sha256sum] = "77afa9a11836342370f4817622a2f0f418b134426d91a82dfb48f532d2ec13ef"
SRC_URI[webpki-roots-0.26.1.sha256sum] = "b3de34ae270483955a94f4b21bdaaeb83d508bb84a01435f393818edb0012009"
SRC_URI[winapi-0.3.9.sha256sum] = "5c839a674fcd7a98952e593242ea400abe93992746761e38641405d28b00f419"
SRC_URI[winapi-i686-pc-windows-gnu-0.4.0.sha256sum] = "ac3b87c63620426dd9b991e5ce0329eff545bccbbb34f3be09ff6fb6ab51b7b6"
SRC_URI[winapi-util-0.1.6.sha256sum] = "f29e6f9198ba0d26b4c9f07dbe6f9ed633e1f3d5b8b414090084349e46a52596"
SRC_URI[winapi-x86_64-pc-windows-gnu-0.4.0.sha256sum] = "712e227841d057c1ee1cd2fb22fa7e5a5461ae8e48fa2ca79ec42cfc1931183f"
SRC_URI[windows-0.52.0.sha256sum] = "e48a53791691ab099e5e2ad123536d0fff50652600abaf43bbf952894110d0be"
SRC_URI[windows-core-0.52.0.sha256sum] = "33ab640c8d7e35bf8ba19b884ba838ceb4fba93a4e8c65a9059d08afcfc683d9"
SRC_URI[windows-registry-0.2.0.sha256sum] = "e400001bb720a623c1c69032f8e3e4cf09984deec740f007dd2b03ec864804b0"
SRC_URI[windows-result-0.2.0.sha256sum] = "1d1043d8214f791817bab27572aaa8af63732e11bf84aa21a45a78d6c317ae0e"
SRC_URI[windows-strings-0.1.0.sha256sum] = "4cd9b125c486025df0eabcb585e62173c6c9eddcec5d117d3b6e8c30e2ee4d10"
SRC_URI[windows-sys-0.48.0.sha256sum] = "677d2418bec65e3338edb076e806bc1ec15693c5d0104683f2efe857f61056a9"
SRC_URI[windows-sys-0.52.0.sha256sum] = "282be5f36a8ce781fad8c8ae18fa3f9beff57ec1b52cb3de0789201425d9a33d"
SRC_URI[windows-sys-0.59.0.sha256sum] = "1e38bc4d79ed67fd075bcc251a1c39b32a1776bbe92e5bef1f0bf1f8c531853b"
SRC_URI[windows-targets-0.48.5.sha256sum] = "9a2fa6e2155d7247be68c096456083145c183cbbbc2764150dda45a87197940c"
SRC_URI[windows-targets-0.52.6.sha256sum] = "9b724f72796e036ab90c1021d4780d4d3d648aca59e491e6b98e725b84e99973"
SRC_URI[windows_aarch64_gnullvm-0.48.5.sha256sum] = "2b38e32f0abccf9987a4e3079dfb67dcd799fb61361e53e2882c3cbaf0d905d8"
SRC_URI[windows_aarch64_gnullvm-0.52.6.sha256sum] = "32a4622180e7a0ec044bb555404c800bc9fd9ec262ec147edd5989ccd0c02cd3"
SRC_URI[windows_aarch64_msvc-0.48.5.sha256sum] = "dc35310971f3b2dbbf3f0690a219f40e2d9afcf64f9ab7cc1be722937c26b4bc"
SRC_URI[windows_aarch64_msvc-0.52.6.sha256sum] = "09ec2a7bb152e2252b53fa7803150007879548bc709c039df7627cabbd05d469"
SRC_URI[windows_i686_gnu-0.48.5.sha256sum] = "a75915e7def60c94dcef72200b9a8e58e5091744960da64ec734a6c6e9b3743e"
SRC_URI[windows_i686_gnu-0.52.6.sha256sum] = "8e9b5ad5ab802e97eb8e295ac6720e509ee4c243f69d781394014ebfe8bbfa0b"
SRC_URI[windows_i686_gnullvm-0.52.6.sha256sum] = "0eee52d38c090b3caa76c563b86c3a4bd71ef1a819287c19d586d7334ae8ed66"
SRC_URI[windows_i686_msvc-0.48.5.sha256sum] = "8f55c233f70c4b27f66c523580f78f1004e8b5a8b659e05a4eb49d4166cca406"
SRC_URI[windows_i686_msvc-0.52.6.sha256sum] = "240948bc05c5e7c6dabba28bf89d89ffce3e303022809e73deaefe4f6ec56c66"
SRC_URI[windows_x86_64_gnu-0.48.5.sha256sum] = "53d40abd2583d23e4718fddf1ebec84dbff8381c07cae67ff7768bbf19c6718e"
SRC_URI[windows_x86_64_gnu-0.52.6.sha256sum] = "147a5c80aabfbf0c7d901cb5895d1de30ef2907eb21fbbab29ca94c5b08b1a78"
SRC_URI[windows_x86_64_gnullvm-0.48.5.sha256sum] = "0b7b52767868a23d5bab768e390dc5f5c55825b6d30b86c844ff2dc7414044cc"
SRC_URI[windows_x86_64_gnullvm-0.52.6.sha256sum] = "24d5b23dc417412679681396f2b49f3de8c1473deb516bd34410872eff51ed0d"
SRC_URI[windows_x86_64_msvc-0.48.5.sha256sum] = "ed94fce61571a4006852b7389a063ab983c02eb1bb37b47f8272ce92d06d9538"
SRC_URI[windows_x86_64_msvc-0.52.6.sha256sum] = "589f6da84c646204747d1270a2a5661ea66ed1cced2631d546fdfb155959f9ec"
SRC_URI[winnow-0.6.26.sha256sum] = "1e90edd2ac1aa278a5c4599b1d89cf03074b610800f866d4026dc199d7929a28"
SRC_URI[write16-1.0.0.sha256sum] = "d1890f4022759daae28ed4fe62859b1236caebfc61ede2f63ed4e695f3f6d936"
SRC_URI[writeable-0.5.5.sha256sum] = "1e9df38ee2d2c3c5948ea468a8406ff0db0b29ae1ffde1bcf20ef305bcc95c51"
SRC_URI[x25519-dalek-2.0.1.sha256sum] = "c7e468321c81fb07fa7f4c636c3972b9100f0346e5b6a9f2bd0603a52f7ed277"
SRC_URI[x509-parser-0.15.1.sha256sum] = "7069fba5b66b9193bd2c5d3d4ff12b839118f6bcbef5328efafafb5395cf63da"
SRC_URI[xattr-1.3.1.sha256sum] = "8da84f1a25939b27f6820d92aed108f83ff920fdf11a7b19366c27c4cda81d4f"
SRC_URI[xxhash-rust-0.8.15.sha256sum] = "fdd20c5420375476fbd4394763288da7eb0cc0b8c11deed431a91562af7335d3"
SRC_URI[yansi-1.0.1.sha256sum] = "cfe53a6657fd280eaa890a3bc59152892ffa3e30101319d168b781ed6529b049"
SRC_URI[yoke-0.7.5.sha256sum] = "120e6aef9aa629e3d4f52dc8cc43a015c7724194c97dfaf45180d2daf2b77f40"
SRC_URI[yoke-derive-0.7.5.sha256sum] = "2380878cad4ac9aac1e2435f3eb4020e8374b5f13c296cb75b4620ff8e229154"
SRC_URI[zerofrom-0.1.5.sha256sum] = "cff3ee08c995dee1859d998dea82f7374f2826091dd9cd47def953cae446cd2e"
SRC_URI[zerofrom-derive-0.1.5.sha256sum] = "595eed982f7d355beb85837f651fa22e90b3c044842dc7f2c2842c086f295808"
SRC_URI[zeroize-1.7.0.sha256sum] = "525b4ec142c6b68a2d10f01f7bbf6755599ca3f81ea53b8431b7dd348f5fdb2d"
SRC_URI[zeroize_derive-1.4.2.sha256sum] = "ce36e65b0d2999d2aafac989fb249189a141aee1f53c612c1f37d72631959f69"
SRC_URI[zerovec-0.10.4.sha256sum] = "aa2b893d79df23bfb12d5461018d408ea19dfafe76c2c7ef6d4eba614f8ff079"
SRC_URI[zerovec-derive-0.10.3.sha256sum] = "6eafa6dfb17584ea3e2bd6e76e0cc15ad7af12b09abdd1ca55961bed9b1063c6"

BBCLASSEXTEND = "native nativesdk"
