DESCRIPTION = "High-performance AMQP-compliant message broker written in Erlang."
HOMEPAGE = "http://www.rabbitmq.com/"
LICENSE = "MPL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE-MPL-RabbitMQ;md5=815ca599c9df247a0c7f619bab123dad"
SECTION = "network"
PR = "r2"

SRC_URI = "https://github.com/rabbitmq/rabbitmq-server/releases/download/v${PV}/${BPN}-${PV}.tar.xz \
           file://rabbitmq-server \
           file://rabbitmq-server.service \
           file://rabbitmq-server-setup \
           file://rabbitmq.conf \
           file://volatiles.99_rabbitmq-server \
           file://rabbitmq-server-volatiles.conf \
           "

SRC_URI[md5sum] = "ea8d933817ce2f4c398d70d87e75ac64"
SRC_URI[sha256sum] = "5eb0ff3fa7e2bd2bd863d6d29c6d476346ed85ee1f5e5218f10e4a1d540724e5"

DEPENDS = " \
    python3-native \
    erlang-native \
    elixir-native \
    rsync-native \
    libxslt \
    zip-native \
    unzip-native \
    libxslt-native \
    coreutils-native\
"

RDEPENDS_${PN} = "erlang erlang-modules \
                  ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'socat', '', d)}"

do_unpack_append() {
    bb.build.exec_func('do_fetch_deps', d)
}

do_fetch_deps() {
    cd ${S}
    oe_runmake fetch-deps
}

do_patch_append() {
    bb.build.exec_func('do_fix_deps', d)
}

do_fix_deps () {
    # Patch python tools to use Python 3; they should be source compatible, but
    # still refer to Python 2 in the shebang
    sed -i -e '1s,#!.*python.*,#!${bindir}/python3,' ${S}/deps/rabbit_common/codegen.py
    sed -i -e '1s,#!.*python.*,#!${bindir}/python3,' ${S}/deps/amqp10_common/codegen.py
}

do_compile() {
    export PYTHON=python3

    oe_runmake
}

do_install() {
    oe_runmake 'DESTDIR=${D}' 'PREFIX=/usr' 'RMQ_BINDIR=${D}/${bindir}' install install-man
    # Info dir listing isn't interesting at this point so remove it if it exists.
    if [ -e "${D}${infodir}/dir" ]; then
        rm -f ${D}${infodir}/dir
    fi

    SCRIPTS="rabbitmq-defaults \
        rabbitmq-env \
        rabbitmq-server \
        rabbitmqctl \
        rabbitmq-plugins \
        rabbitmq-diagnostics \
        rabbitmq-queues \
        rabbitmq-upgrade \
        cuttlefish"

    # Create symbolic links as install-bin does
    install -d ${D}${bindir}
    for script in $SCRIPTS; do
        ln -sf ${libdir}/erlang/lib/rabbitmq_server-${PV}/sbin/$script ${D}${bindir}/$script
    done

    # Create rabbitmq config directory
    install -d ${D}${sysconfdir}/rabbitmq
    chmod 750 ${D}/${sysconfdir}/rabbitmq
    chown -R root.rabbitmq ${D}/${sysconfdir}/rabbitmq

    install -d ${D}${sysconfdir}/default/volatiles
    install -m 0644 ${WORKDIR}/volatiles.99_rabbitmq-server ${D}${sysconfdir}/default/volatiles/99_rabbitmq-server

    install -m 644 ${WORKDIR}/rabbitmq.conf ${D}/${sysconfdir}/rabbitmq/rabbitmq.conf
    chown root.rabbitmq ${D}/${sysconfdir}/rabbitmq/rabbitmq.conf

    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
        install -d ${D}${sysconfdir}/init.d
        install -m 0755 ${WORKDIR}/rabbitmq-server ${D}${sysconfdir}/init.d/rabbitmq-server
        sed -e "s:%ROOT_HOME%:/var/lib/rabbitmq:" -i ${D}${sysconfdir}/init.d/rabbitmq-server
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/rabbitmq-server.service ${D}${systemd_unitdir}/system
	    install -d ${D}${bindir}
	    install -m 0755 ${WORKDIR}/rabbitmq-server-setup ${D}${bindir}
        install -d ${D}${sysconfdir}/tmpfiles.d/
        install -m 0644 ${WORKDIR}/rabbitmq-server-volatiles.conf ${D}${sysconfdir}/tmpfiles.d/rabbitmq-server.conf
    fi
}

inherit useradd update-rc.d systemd

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "--system rabbitmq"
USERADD_PARAM_${PN}  = "--system --create-home --home /var/lib/rabbitmq \
			-g rabbitmq rabbitmq"

INITSCRIPT_NAME = "rabbitmq-server"
INITSCRIPT_PARAMS = "defaults"

SYSTEMD_SERVICE_${PN} = "rabbitmq-server.service"

FILES_${PN} += " ${libdir}/erlang/lib/rabbitmq_server-${PV}/* \
                 ${libdir}/erlang/bin \
                 ${libdir}/erlang/autocomplete \
                 ${localstatedir}/* \
               "
FILES_${PN}-dev += " ${libdir}/erlang/lib/rabbitmq_server-${PV}/include"

FILES_${PN}-doc += " ${libdir}/erlang/lib/rabbitmq_server-${PV}/*LICENSE* ${libdir}/erlang/lib/rabbitmq_server-${PV}/INSTALL"
