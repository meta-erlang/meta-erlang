SUMMARY = "Robust, Ubiquitous and Massively Scalable Messaging Platform (XMPP, MQTT, SIP Server)"

DESCRIPTION = "ejabberd is a distributed, fault-tolerant technology that allows the creation of large-scale \
instant messaging applications. The server can reliably support thousands of simultaneous users on \
a single node and has been designed to provide exceptional standards of fault tolerance."

HOMEPAGE = "https://www.process-one.net/en/ejabberd/"

SECTION = "network"

DEPENDS = "erlang-native \
           erlang \
           openssl \
           expat \
           libyaml \
           ${@bb.utils.contains('DISTRO_FEATURES', 'pam', 'libpam', '', d)}"

RDEPENDS_${PN} = "erlang erlang-compiler erlang-mnesia erlang-os-mon erlang-crypto erlang-ssl erlang-public-key erlang-asn1 erlang-inets"

LICENSE = "GPL-2.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=81d50b500048941f0838c57e6a7c5ed0"

SRC_URI = "git://github.com/processone/ejabberd;protocol=https \
           file://0001-Fix-escript-path.patch \
           file://ejabberd.init"

SRCREV = "db2825342cfabb35215f067bfa5b3d0e5a21b97c"
PR = "r0"
PV = "20.04+git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools-brokensep erlang-version useradd update-rc.d

EXTRA_OECONF = "\
                --disable-erlang-version-check \
                ${@bb.utils.contains('DISTRO_FEATURES', 'pam', '--enable-pam', '--disable-pam', d)}"

CACHED_CONFIGUREVARS += "ac_cv_erlang_root_dir=${STAGING_LIBDIR}/erlang"

export ERL_CFLAGS = "-I${STAGING_LIBDIR}/erlang/lib/${@get_erlang_application(d, "erl_interface")}/include \
                     -I${STAGING_LIBDIR}/erlang/${@get_erlang_application(d, "erts")}/include"

export ERL_EI_LIBDIR = "${STAGING_LIBDIR}/erlang/lib/${@get_erlang_application(d, "erl_interface")}/lib"

do_configure_append() {
    make deps

    for i in fast_tls fast_xml fast_yaml eimp ezlib stringprep ezlib; do
        cd ${S}/deps/$i
        ./configure ${CONFIGUREOPTS}
    done

    cd ${S}
}

do_install_append() {
	# Fix ejabberdctl
	sed -i -e 's,^ERL=.*$,ERL=${bindir}/erl,g' \
		${D}/${sbindir}/ejabberdctl
	sed -i -e 's,^EPMD=.*$,EPMD=${bindir}/epmd,g' \
		${D}/${sbindir}/ejabberdctl
	sed -i -e 's,^INSTALLUSER=.*$,INSTALLUSER=ejabberd,g' \
		${D}/${sbindir}/ejabberdctl

    rm -rf ${D}/${bindir}
    rm -rf ${D}/${localstatedir}

    chmod 750 ${D}/${sysconfdir}/ejabberd
    chown -R root.ejabberd ${D}/${sysconfdir}/ejabberd

    mkdir -p ${D}/${localstatedir}/log/ejabberd
    chmod 770 ${D}/${localstatedir}/log/ejabberd
    chown -R root.ejabberd ${D}/${localstatedir}/log/ejabberd

    # Install init.d
    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
        install -d ${D}${sysconfdir}/init.d
        install ${WORKDIR}/ejabberd.init ${D}/${sysconfdir}/init.d/ejabberd
    fi
}

FILES_${PN} += " \
               ${libdir}/pkix-* \
               ${libdir}/p1_oauth2-* \
               ${libdir}/unicode_util_compat-* \
               ${libdir}/yconf-* \
               ${libdir}/p1_acme-* \
               ${libdir}/goldrush-* \
               ${libdir}/idna-* \
               ${libdir}/lager-* \
               ${libdir}/p1_utils-* \
               ${libdir}/ejabberd-* \
               ${libdir}/base64url-* \
               ${libdir}/jose-* \
               ${libdir}/eimp-* \
               ${libdir}/fast_xml-* \
               ${libdir}/fast_tls-* \
               ${libdir}/fast_yaml-* \
               ${libdir}/mqtree-* \
               ${libdir}/stringprep-* \
               ${libdir}/xmpp-* \
               ${libdir}/ezlib-* \
               ${libdir}/cache_tab-* \
               ${libdir}/jiffy-* \
               ${libdir}/stun-* \
               ${root_prefix}/run"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system --create-home --home-dir ${localstatedir}/lib/ejabberd \
    --shell /bin/false --user-group ejabberd"

INITSCRIPT_NAME = "ejabberd"
INITSCRIPT_PARAMS = "defaults"
