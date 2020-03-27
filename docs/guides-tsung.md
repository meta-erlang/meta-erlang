In this guide we are using the plain meta-erlang layer to include a distributed test tool called [tsung](http://tsung.erlang-projects.org/).

The recipe [tsung_1.7.0.bb](https://github.com/joaohf/meta-erlang/blob/master/recipes-tests/tsung/tsung_1.7.0.bb) builds the tsung tool using a crosscompile approach.

As a final result, we are able to run the command `tsung`.

Follow the quickstart guide to get a basic working environment and then:

 * Add `tsung` and `tsung-doc` packages to `IMAGE_INSTAL` in _conf/local.conf_
```bash
echo 'IMAGE_INSTALL_append = " tsung tsung-doc"' >> conf/local.conf
```
 * Now its time to build the image:
```bash
bitbake core-image-minimal
```
 * And check the results with qemu:
```bash
runqemu core-image-minimal
```

The package tsung-doc provides a set of working examples which can be used as a base. The below command will start tsung using a basic scenario:

```bash
tsung -f /usr/share/doc/tsung/examples/http_simple.xml start
```

Tsung has a pretty [decent documentation](http://tsung.erlang-projects.org/user_manual/) showing how to configure more advanced scenarios.