This application run inside an embedded erlang run time system (ERTS). The ERTS will be copied from Yocto build environment to the target application.

## rebar3 bbclass

The [rebar3.class](https://github.com/joaohf/meta-erlang/blob/master/classes/rebar3.bbclass) from meta-erlang layer exported two environment variables:

[](https://gist.githubusercontent.com/joaohf/d50292f242c870e3e7ebfbe1dac13f2b/raw/86fc1dd34aaf0e8904c72d094e21a93b3eaeed87/rebar3_class ":include :type=code text")

## rebar config script

The `axon` application has a [rebar3 script config](https://github.com/joaohf/axon/blob/3baebb418f6024aeb221b79fd65f820f8968dd8e/rebar.config.script) that uses the REBAR3_TARGET_INCLUDE_ERTS and REBAR3_TARGET_SYSTEM_LIBS replace the `system_libs` and `include_erts` arguments from `relx`:

[](https://gist.githubusercontent.com/joaohf/03596d956ed549304936d19aa711312c/raw/721e5c39190b3aeaf58fcd475573b2eb72ac5f36/rebar3_script_config ":include :type=code erlang")

That way the `rebar3.bbclass` export the needeed variables and the `rebar.config.script` use them. Then rebar has all the information to release the application witin a ERTS inside.

## rebar config

A very basic [rebar3 config](https://github.com/joaohf/axon/blob/3baebb418f6024aeb221b79fd65f820f8968dd8e/rebar.config):

[](https://gist.githubusercontent.com/joaohf/6c9b2dfc640a1f2992a015f26104d40f/raw/7077f3de3242611b4084cb820189d406c543ab54/rebar3_config ":include :type=code erlang")

`relx` must have the `include_erts` and `system_libs` setted to `true`.
