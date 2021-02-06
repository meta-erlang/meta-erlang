SUMMARY = "Meta package for building an installable Erlang/Elixir toolchain"
LICENSE = "MIT"

inherit populate_sdk

TOOLCHAIN_HOST_TASK_append = " \
     nativesdk-rebar3 \
     nativesdk-erlfmt \
     nativesdk-elvis \
     nativesdk-erlang \
     nativesdk-erlang-modules-dev \
     nativesdk-elixir \
     nativesdk-elixir-modules-dev \
"

TOOLCHAIN_TARGET_TASK_append = " \
    ${@multilib_pkg_extend(d, 'packagegroup-erlang-sdk-target')} \
"

SDK_TITLE = "Erlang/Elixir toolchain"
