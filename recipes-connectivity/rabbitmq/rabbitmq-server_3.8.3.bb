DESCRIPTION = "High-performance AMQP-compliant message broker written in Erlang."
HOMEPAGE = "http://www.rabbitmq.com/"
LICENSE = "MPL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE-MPL-RabbitMQ;md5=b651d0a6feaaf2bf5eb9d36b2495c510"
SECTION = "network"
PR = "r0"

SRC_URI = "http://www.rabbitmq.com/releases/rabbitmq-server/v${PV}/${PN}-${PV}.tar.gz \
           file://rabbitmq-server \
           file://remove-docs.patch \
           file://cleanup_install.patch \
           file://rabbitmq-server.service \
           file://rabbitmq-server-setup \
           "

SRC_URI[md5sum] = "09d1af64c005bc680d6790b90655d021"
SRC_URI[sha256sum] = "a930f92b362df2f292ec5f0281aa2011eb0c668faf6e24c4653a9fc53ec43b9f"

DEPENDS = " \
    erlang-native \
    libxslt \
    python-simplejson \
    zip-native \
    unzip-native \
    libxslt-native \
    coreutils-native\
"

RDEPENDS_${PN} = "erlang erlang-modules"

do_compile() {
    oe_runmake
}

do_install() {
    RABBIT_LIB_DIR=${D}${libdir}/rabbitmq/lib/${PN}-${PV}
    DOC_INSTALL_DIR="${D}${docdir}"

    sed -e "s:^RABBITMQ_HOME=.*:RABBITMQ_HOME=\"${libdir}/rabbitmq/lib/${PN}-${PV}\":g" \
               -i ${S}/scripts/rabbitmq-env
    oe_runmake TARGET_DIR=${D} \
               SBIN_DIR=${D}/${bindir} \
               MAN_DIR=${D}/${mandir} \
               DOC_INSTALL_DIR=${DOC_INSTALL_DIR} install \

    install -d ${D}${libdir}/rabbitmq/lib/${PN}-${PV}

    install -d ${D}${localstatedir}/log/${PN}

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/rabbitmq-server ${D}${sysconfdir}/init.d/rabbitmq-server

    sed -e "s:%ROOT_HOME%:${ROOT_HOME}:" -i ${D}${sysconfdir}/init.d/rabbitmq-server

    mv ${D}/ebin ${RABBIT_LIB_DIR}/ebin
    mv ${D}/include ${RABBIT_LIB_DIR}/include
    mv ${D}/plugins ${RABBIT_LIB_DIR}/plugins

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/rabbitmq-server.service ${D}${systemd_unitdir}/system

    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/rabbitmq-server-setup ${D}${bindir}
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

FILES_${PN} += " ${libdir}/rabbitmq/lib/${PN}-${PV}/* \
                 ${localstatedir}/* \ 
               "

FILES_${PN}-doc += "LICENSE* INSTALL"
INSANE_SKIP_${PN} = "unsafe-references-in-scripts"
