# OpenEmbedded/Yocto Project layer for Erlang and Elixir support

This layer provides support for [Erlang](https://www.erlang.org/) and [Elixir](https://elixir-lang.org/) for use with [OpenEmbedded](http://www.openembedded.org/wiki/Main_Page) and/or
the [Yocto Project](https://www.yoctoproject.org/) build system:

## Documentation

See [meta-erlang documentation](https://meta-erlang.github.io/).

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


## Contributing

Send pull requests to openembedded-devel@lists.openembedded.org with '[meta-erlang]' in the subject'

When sending single patches, please using something like:

```
git send-email -M -1 --to openembedded-devel@lists.openembedded.org --subject-prefix=meta-erlang][PATCH
```

Interim layer maintainer: João Henrique Freitas <joaohf@gmail.com>

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

As meta-erlang provides multiple versions for the same recipes, pay attention to configure the variable [PREFERRED_VERSION](https://docs.yoctoproject.org/ref-manual/ref-variables.html?highlight=preferred_version#term-PREFERRED_VERSION) in one of the configuration file like _local.conf_, _site.conf_, _auto.conf_ or _distro/include_ files. Like this:

```
PREFERRED_VERSION_erlang = "23.1.3"
PREFERRED_VERSION_erlang-native = "23.1.3"

PREFERRED_VERSION_elixir = "1.10.0"
PREFERRED_VERSION_elixir-native = "1.10.0"
```

However if you have any specific requirements for a special recipe version, you can always add it in your application/middleware layer.

Following the [OTP Versions Tree](http://erlang.org/download/otp_versions_tree.html),
[Elixir Compatibility and Deprecations](https://hexdocs.pm/elixir/compatibility-and-deprecations.html)
 and [Yocto Project releases](https://wiki.yoctoproject.org/wiki/Releases) meta-erlang supports the below versions:

Erlang:

 * [maint-25](https://github.com/erlang/otp/tree/maint-25)
 * [maint-24](https://github.com/erlang/otp/tree/maint-24)
 * [maint-23](https://github.com/erlang/otp/tree/maint-23)
 * [maint-22](https://github.com/erlang/otp/tree/maint-22)

Elixir:

* [v1.13](https://github.com/elixir-lang/elixir/tree/v1.13)
* [v1.12](https://github.com/elixir-lang/elixir/tree/v1.12)
* [v1.11](https://github.com/elixir-lang/elixir/tree/v1.11)
* [v1.10](https://github.com/elixir-lang/elixir/tree/v1.10)
* [v1.9](https://github.com/elixir-lang/elixir/tree/v1.9)

Yocto:

meta-erlang provides specific branches for each YP/OE release. So, please stick with one of the supported branches
to avoid compatible probles:

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
