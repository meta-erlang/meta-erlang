In this guide we are using a layer called [meta-axon](https://github.com/joaohf/meta-axon) to hold:

- the axon recipe application
- [axon-image--minimal](https://github.com/joaohf/meta-axon/blob/master/recipes-extended/image/axon-image-minimal.bb), a basic image to run axon application
- [axon-embedded-image-minimal](https://github.com/joaohf/meta-axon/blob/master/recipes-extended/image/axon-embedded-image-minimal.bb), a very basic image with axon application and erlang embedded init script package group, with [erlinit](https://github.com/nerves-project/erlinit) as replacement for '/sbin/init'

The idea is to show two examples about how to run erlang applications.
