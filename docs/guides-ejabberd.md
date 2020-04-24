In this guide we are using the plain meta-erlang layer to build a XMPP server called [ejabberd](https://www.ejabberd.im/).

The recipe [ejabberd_20.03.bb](https://github.com/joaohf/meta-erlang/blob/master/recipes-connectivity/ejabberd/ejabberd_20.03.bb) builds the ejabberd using a crosscompile approach both to erlang and for the various Erlang NIFs. ejabberd uses a custom mix between autotools and rebar2. Basically rebar needs to know how to use the correct environment variables in order to compile all the C code.

As a final result, we are able to run an embedded ejabberd using qemu.

Follow the quickstart guide to get a basic working environment and then:

 * Add `ejabberd` package to `IMAGE_INSTAL` in _conf/local.conf_
```bash
echo 'IMAGE_INSTALL_append = " ejabberd"' >> conf/local.conf
```
 * Now its time to build the image:
```bash
bitbake core-image-minimal
```
 * And check the results with qemu:
```bash
runqemu core-image-minimal
```

Connect to the qemu instance and call the main ejabberd admin tool: ```ejabberdctl```.

The default recipe creates a system user called _ejabberd_ by default.