In this guide we are using the plain meta-erlang layer to build the [riak](http://www.riak.info). The recipe [riak.inc](https://github.com/joaohf/meta-erlang/blob/master/recipes-database/riak/riak.inc) builds the riak using a crosscompile approach.

As a final result, we will be able to run an embedded riak using qemu.

Follow the quickstart guide to get a basic working environment and then:

 * Add erlang preferred version 22.x in _conf/local.conf_ (check the full list of Erlang supported versions here [Riak KV 3.0 Release Notes](https://github.com/basho/riak/blob/develop-3.0/RELEASE-NOTES.md#riak-kv-30-release-notes))
```bash
echo 'PREFERRED_VERSION_erlang = "22%"
```
 * Add `riak` package to `IMAGE_INSTAL` in _conf/local.conf_
```bash
echo 'IMAGE_INSTALL_append = " riak"' >> conf/local.conf
```
 * Now its time to build the image:
```bash
bitbake core-image-minimal
```
 * And check the results with qemu:
```bash
runqemu core-image-minimal qemuparams="-m 1024"
```

The official [riak documentation](https://docs.riak.com/riak/kv/2.2.3/index.html) has a specific session about how to perform the initial configuration: [Basic Riak KV Configuration](https://docs.riak.com/riak/kv/2.2.3/configuring/basic.1.html) -- I recommend follow the instructions there.

After the first configuration it is time to start riak. As usual, use systemd or systemv scripts (stop and restart as well) riak:

 * `/etc/init.d/riak restart`, or
 * `systemctl restart riak`

To make sure that everything is working properly, follow the guide [Verifying a Riak KV Installation](https://docs.riak.com/riak/kv/2.2.3/setup/installing/verify/index.html).

Soon or later you will end up configuring a cluster. Then following this guide: [Running a Cluster](https://docs.riak.com/riak/kv/2.2.3/using/running-a-cluster.1.html).