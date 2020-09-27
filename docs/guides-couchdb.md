In this guide we are using the plain meta-erlang layer to build the [couchdb](https://couchdb.apache.org/.

The recipe [couchdb.inc](https://github.com/joaohf/meta-erlang/blob/master/recipes-database/couchdb/couchdb.inc) builds the couchdb using a crosscompile approach. couchdb prefers to work with a full erlang release instead of use the Erlang packages installed on the final image.

As a final result, we are able to run an embedded couchdb using qemu.

Follow the quickstart guide to get a basic working environment and then:

 * Add erlang preferred version 22.x in _conf/local.conf_ (check the full list of Erlang supported versions here [Dependencies](https://docs.couchdb.org/en/stable/install/unix.html#dependencies))
```bash
echo 'PREFERRED_VERSION_erlang = "22%"
```
 * Add `couchdb` package to `IMAGE_INSTAL` in _conf/local.conf_
```bash
echo 'IMAGE_INSTALL_append = " couchdb"' >> conf/local.conf
```
 * Now its time to build the image:
```bash
bitbake core-image-minimal
```
 * And check the results with qemu:
```bash
runqemu core-image-minimal
```

couchdb needs two additional manual configurations in the config file (_/opt/couchdb/etc/local.ini_).

 * The first one is to enable the dashboard remote access. Find the session `chttpd` and change the bind_address. Like this:
```
[chttpd]
port = 5984 
bind_address = 0.0.0.0
```
 * The second one is necessary to add a administrator user to the couchdb instance. Like this:
```
[admins]
admin = mysecretpassword
```

Restart or start the couchdb service using the init scripts (`/etc/init.d/couchdb restart` or `systemctl restart couchdb`).

couchdb has a nice dashboard where we can check the database and do all the maintenance as well creating documents. Access the address http://192.168.7.2:5984/_utils/index.html to get into the dashboard:

![couchdb dashboard](_media/couchdb_dashboard.png)
