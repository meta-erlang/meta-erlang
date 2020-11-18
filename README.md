# OpenEmbedded/Yocto Project layer for Erlang and Elixir support


[![CircleCI](https://circleci.com/gh/joaohf/meta-erlang/tree/master.svg?style=svg)](https://circleci.com/gh/joaohf/meta-erlang/tree/master)

This layer provides support for Erlang for use with OpenEmbedded and/or
Yocto Project build system:

This layer depends on:

URI: git://github.com/openembedded/oe-core.git
branch: master
revision: HEAD

## Documentation

See [meta-erlang documentation](http://joaohf.github.io/meta-erlang).

## Dependencies

This layer depends on:

  URI: git://git.openembedded.org/bitbake
  branch: master

  URI: git://git.openembedded.org/openembedded-core
  layers: meta
  branch: master

For erlang odbc:

  URI: https://github.com/openembedded/meta-openembedded.git
  layers: meta-oe
  branch: master

For erlang lksctp run-time support:

  URI: https://github.com/openembedded/meta-openembedded.git
  layers: meta-networking
  branch: master


## Contributing

Send pull requests to openembedded-devel@lists.openembedded.org with '[meta-erlang]' in the subject'

When sending single patches, please using something like:
'git send-email -M -1 --to openembedded-devel@lists.openembedded.org --subject-prefix=meta-erlang][PATCH'

Interim layer maintainer: João Henrique Freitas <joaohf@gmail.com>

## Usage instructions

For conf/bblayers.conf you have to add:

```
BBLAYERS ?= " \
   ...
  path_to_source/sources/meta-erlang \
  "
```

Than run:

```
bitbake erlang-embedded-image-minimal
```

That will creates a image with embedded erlang.

Or add ```IMAGE_INSTALL_append = " erlang"``` in conf/local.conf and run
```bitbake core-image-minimal``` to get an image with erlang support.

## Supported versions

meta-erlang tries to support a well balanced range of Erlang, Elixir and Yocto Project versions.

Following the [OTP Versions Tree](http://erlang.org/download/otp_versions_tree.html),
[Elixir Compatibility and Deprecations](https://hexdocs.pm/elixir/compatibility-and-deprecations.html)
 and [Yocto Project releases](https://wiki.yoctoproject.org/wiki/Releases) meta-erlang supports the below branches:

### Erlang

 * [maint-23](https://github.com/erlang/otp/tree/maint-23)
 * [maint-22](https://github.com/erlang/otp/tree/maint-22)
 * [maint-21](https://github.com/erlang/otp/tree/maint-21)
 * [maint-20](https://github.com/erlang/otp/tree/maint-20)
 
### Elixir

* [v1.11](https://github.com/elixir-lang/elixir/tree/v1.11)
* [v1.10](https://github.com/elixir-lang/elixir/tree/v1.10)
* [v1.9](https://github.com/elixir-lang/elixir/tree/v1.9)

### Yocto

* [gatesgarth](https://git.yoctoproject.org/cgit/cgit.cgi/poky/tree/?h=gatesgarth)
* [dunfell](https://git.yoctoproject.org/cgit/cgit.cgi/poky/tree/?h=dunfell)
* [zeus](https://git.yoctoproject.org/cgit/cgit.cgi/poky/tree/?h=zeus)
* [warrior](https://git.yoctoproject.org/cgit/cgit.cgi/poky/tree/?h=warrior)

## Other Information

This layer can provide the entire Erlang installation, from the base
runtime (erlang) to a full erlang development environment with all
of the bells and whistles (erlang-modules).

A list of all the packages provided can be found in the manifest file,
at recipes-devtools/erlang/erlang-${PV}-manifest.inc. This file needs
to be re-generated and new SRCREVs need to be added whenever the version
changes.
