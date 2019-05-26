When doing erlang or elixir releases, usually the application run inside an embedded Erlang Run Time System (ERTS). The ERTS will be copied from Yocto build environment to the target application.

The layer meta-erlang provides additional bitbake classes to handle this process when using rebar3 or mix tool.

## Erlang

### rebar3 bbclass

The [rebar3.class](https://github.com/joaohf/meta-erlang/blob/master/classes/rebar3.bbclass) from meta-erlang layer exports the following environment variables:

[](https://gist.githubusercontent.com/joaohf/d50292f242c870e3e7ebfbe1dac13f2b/raw/86fc1dd34aaf0e8904c72d094e21a93b3eaeed87/rebar3_class ':include :type=code')

 * `REBAR3_TARGET_INCLUDE_ERTS`, target include ERTS path
 * `REBAR3_TARGET_SYSTEM_LIBS`, target system ERTS path
 * `ERLANG_ERTS`, the version of ERTS

### rebar config script

The `axon` application has a [rebar3 script config](https://github.com/joaohf/axon/blob/3baebb418f6024aeb221b79fd65f820f8968dd8e/rebar.config.script) that uses the REBAR3_TARGET_INCLUDE_ERTS and REBAR3_TARGET_SYSTEM_LIBS replace the `system_libs` and `include_erts` arguments from `relx`:

[](https://gist.githubusercontent.com/joaohf/03596d956ed549304936d19aa711312c/raw/721e5c39190b3aeaf58fcd475573b2eb72ac5f36/rebar3_script_config ':include :type=code erlang')

That way the `rebar3.bbclass` export the needeed variables and the `rebar.config.script` use them. Then rebar has all the information to release the application witin a ERTS inside.

### rebar config

A very basic [rebar3 config](https://github.com/joaohf/axon/blob/3baebb418f6024aeb221b79fd65f820f8968dd8e/rebar.config):

[](https://gist.githubusercontent.com/joaohf/6c9b2dfc640a1f2992a015f26104d40f/raw/7077f3de3242611b4084cb820189d406c543ab54/rebar3_config ':include :type=code erlang')

`relx` must have the `include_erts` and `system_libs` setted to `true`.

## Elixir

### mix bbclass

The [mix.class](https://github.com/joaohf/meta-erlang/blob/master/classes/mix.bbclass) from meta-erlang layer exports one environment variables:

 * `MIX_TARGET_INCLUDE_ERTS`, target include ERTS path

In additional the application recipe can configure the variable `MIX_ENV` to a specific mix environment. The default value is `prod`.

### release with distillery

This approach uses distillery to handle the release. The usual config is documented [here]( https://hexdocs.pm/distillery/introduction/installation.html). An extra step is necessary to allow distillery copy the crosscompile elixir and erlang libraries to the release package. The following code show the additional configuration:

[](https://gist.githubusercontent.com/joaohf/6da82c8b1f20ef7f378a0c97c8beb5a8/raw/1a3b445238df53e99ca623d629a4af96b67d1ab3/rel_distillery ':include :type=code elixir')

The `include_erts` distillery config will use the value from `MIX_TARGET_INCLUDE_ERTS`.