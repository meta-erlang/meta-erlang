In this guide we are using the plain meta-erlang layer to build an httpd webserver called [Yaws](http://yaws.hyber.org/).

The recipe [yaws_2.0.7.bb](https://github.com/joaohf/meta-erlang/blob/master/recipes-httpd/yaws/yaws_2.0.7.bb) builds the Yaws using a crosscompile approach both to erlang and C. Yaws uses the autoconf build environment making the whole process straightforward when creating the recipe.

As a final result, we are able to run a standalone Yams using qemu. In fact, Yaws recipe is only useful when working with standalone mode (see Yaws website). Yaws also supports an embedded mode but usually that should be integrate with an high order application.

Follow the quickstart guide to get a basic working environment and then:

 * Add `yaws` package to `IMAGE_INSTAL` in _conf/local.conf_
```bash
echo 'IMAGE_INSTALL_append = " yaws"' >> conf/local.conf
```
 * There is a package called `yaws-examples` that adds code and examples. This is an optional step
```bash
echo 'IMAGE_INSTALL_append = " yaws yaws-examples"' >> conf/local.conf
```
 * Now it's time to build the image:
```bash
bitbake core-image-minimal
```
 * And check the results with qemu:
```bash
runqemu core-image-minimal
```

To start `yaws`, there are two options:

1. Using systemv or systemd script
```bash
# systemv
/etc/init.d/yaws
# systemd
systemctl start yaws
```
2. Call the yaws help scripts to start the webserver:
```bash
/usr/bin/yaws
```

After that, from your host environment, you can access the webserver reaching one of the two endpoints available:

* http://192.168.7.2:8000
* https://192.168.7.2:8443

Yaws, is a webserver totally functional and ready to create great applications. The [Yaws website](http://yaws.hyber.org/) has a lot of high quality information.