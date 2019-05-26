
The most important step is to create the recipes using bitbake and rebar3 or mix classes. The following sections show recipes from [meta-axon layer](https://github.com/joaohf/meta-axon/tree/master/recipes-extended/axon)

## Erlang

The recipe `axon_git.bb` uses rebar3 to compile and release an erlang application:

[](https://raw.githubusercontent.com/joaohf/meta-axon/master/recipes-extended/axon/axon_git.bb ':include :type=code text')

rebar3 will build the application using the `prod` profile that was previously set by `REBAR3_PROFILE` variable.

!> The REBAR_PROFILE is an important variable to configure the [rebar profile](https://www.rebar3.org/docs/profiles).

## Elixir

The recipe `axon-scenic_git.bb` uses mix to compile and release an elixir application:

[](https://raw.githubusercontent.com/joaohf/meta-axon/master/recipes-extended/axon/axon-scenic_git.bb ':include :type=code text')