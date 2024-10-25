# OpenEmbedded/Yocto Project layer for Erlang, Elixir and Gleam support

This layer provides support for the following BEAM languages:

* [Erlang](https://www.erlang.org/)
* [Elixir](https://elixir-lang.org/)
* [Gleam](https://gleam.run/)

For use with [OpenEmbedded](http://www.openembedded.org/wiki/Main_Page) and/or
the [Yocto Project](https://www.yoctoproject.org/) build system.

## Documentation

See [meta-erlang documentation](https://meta-erlang.github.io/docs/master/).

## Dependencies

This layer depends on:

  URI: git://git.openembedded.org/bitbake
  branch: master

  URI: git://github.com/openembedded/oe-core.git
  branch: master
  revision: HEAD

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

## Usage instructions

Use the _bitbake-layers add-layer_ command to add the meta-erlang to the configuration file:

```
cd ~/poky/build
bitbake-layers add-layer ../meta-erlang
```

Then run:

```
bitbake erlang-embedded-image-minimal
```

That will create an image with embedded erlang.

Or add ```IMAGE_INSTALL_append = " erlang"``` in _conf/local.conf_ file and run
```bitbake core-image-minimal``` to get an image with erlang support.

Also, there are a set of [recipe examples](recipes-examples) demonstrating how to integrate Erlang/Elixir
projects into YP/OE. The examples tried to cover some common use cases when dealing with cross compiled
applications.

## Supported versions

meta-erlang tries to support a well balanced range of Erlang, Elixir and Yocto Project versions. The purpose is to provide up-to-date recipes following the latest fixes found in Erlang and Elixir projects as well keeping the old recipes to not break compatibility.

Please, before building Erlang/Elixir applications, check out the PREFERRED_VERSION for each application on the [meta-erlang application documentation](https://meta-erlang.github.io/docs/master/category/applications/).

As meta-erlang provides multiple versions for the same recipes, pay attention to configure the variable [PREFERRED_VERSION](https://docs.yoctoproject.org/ref-manual/ref-variables.html?highlight=preferred_version#term-PREFERRED_VERSION) in one of the configuration file like _local.conf_, _site.conf_, _auto.conf_ or _distro/include_ files. Like this:

```
PREFERRED_VERSION_erlang = "27.0"
PREFERRED_VERSION_erlang-native = "27.0"

PREFERRED_VERSION_elixir = "1.16.3"
PREFERRED_VERSION_elixir-native = "1.16.3"

PREFERRED_VERSION_gleam-native = "1.5.1"
```

However if you have any specific requirements for a special recipe version, you can always add it in your application/middleware layer.

Following the [OTP Versions Tree](http://erlang.org/download/otp_versions_tree.html),
[Elixir Compatibility and Deprecations](https://hexdocs.pm/elixir/compatibility-and-deprecations.html)
 and [Yocto Project releases](https://wiki.yoctoproject.org/wiki/Releases) meta-erlang supports the below versions:

Erlang:

 * [maint-27](https://github.com/erlang/otp/tree/maint-27)
 * [maint-26](https://github.com/erlang/otp/tree/maint-26)
 * [maint-25](https://github.com/erlang/otp/tree/maint-25)

Elixir:

* [v1.16](https://github.com/elixir-lang/elixir/tree/v1.16)
* [v1.15](https://github.com/elixir-lang/elixir/tree/v1.15)
* [v1.14](https://github.com/elixir-lang/elixir/tree/v1.14)
* [v1.13](https://github.com/elixir-lang/elixir/tree/v1.13)

Gleam:

* [1.5.1](https://github.com/gleam-lang/gleam/releases/tag/v1.5.1)

Yocto:

meta-erlang provides specific branches for each YP/OE release. So, please stick with one of the supported branches
to avoid compatible problems:

* [styhead](https://git.yoctoproject.org/cgit/cgit.cgi/poky/log/?h=styhead)
* [scarthgap](https://git.yoctoproject.org/cgit/cgit.cgi/poky/log/?h=scarthgap)
* [nanbield](https://git.yoctoproject.org/cgit/cgit.cgi/poky/log/?h=nanbield)
* [mickledore](https://git.yoctoproject.org/cgit/cgit.cgi/poky/log/?h=mickledore)
* [langdale](https://git.yoctoproject.org/cgit/cgit.cgi/poky/log/?h=langdale)
* [kirkstone](https://git.yoctoproject.org/cgit/cgit.cgi/poky/log/?h=kirkstone)
* [honister](https://git.yoctoproject.org/cgit/cgit.cgi/poky/log/?h=honister)
* [hardknott](https://git.yoctoproject.org/cgit/cgit.cgi/poky/log/?h=hardknott)
* [gatesgarth](https://git.yoctoproject.org/cgit/cgit.cgi/poky/tree/?h=gatesgarth)
* [dunfell](https://git.yoctoproject.org/cgit/cgit.cgi/poky/tree/?h=dunfell)
* [zeus](https://git.yoctoproject.org/cgit/cgit.cgi/poky/tree/?h=zeus)
* [warrior](https://git.yoctoproject.org/cgit/cgit.cgi/poky/tree/?h=warrior)

Even though supporting a range of versions, users are recommended to checkout specific meta-erlang branch in order to find out the correct combination between Erlang/OTP versions. Due to how Erlang/OTP and YP/OE are made, it is not
possible always to keep some versions without backporting or fixing cross compilation issues.

## Other Information

This layer can provide the entire Erlang installation, from the base
runtime (erlang) to a full erlang development environment with all
of the bells and whistles (erlang-modules).

A list of all the packages provided can be found in the manifest file,
at recipes-devtools/erlang/erlang-${PV}-manifest.inc. This file needs
to be re-generated and new SRCREVs need to be added whenever the version
hanges.

## Contributing

You can send patches using the GitHub pull request process.

## Maintainers

* João Henrique Ferreira de Freitas `<joaohf@gmail.com>`