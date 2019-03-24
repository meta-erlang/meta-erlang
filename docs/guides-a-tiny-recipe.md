The next step is to create a recipe to the 'axon' application. Like this:

[](https://raw.githubusercontent.com/joaohf/meta-axon/master/recipes-extended/axon/axon_git.bb ":include :type=code text")

rebar will build the application using the `prod` profile that was previously set by `REBAR3_PROFILE` variable.

!> The REBAR_PROFILE is an important variable to configure the [rebar profile](https://www.rebar3.org/docs/profiles).
