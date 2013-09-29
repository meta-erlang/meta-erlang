include erlang.inc
DEPENDS += "openssl"

PR = "r0"

XCOMP_CONF_PATH = "${S}/xcomp/erl-xcomp-oe.conf"

RELEASE_DIR = "${S}/release/${HOST_SYS}-gnu"

ERL_LIBS = "appmon asn1 common_test compiler cosEvent cosEventDomain cosFileTransfer cosNotification cosProperty cosTime cosTransactions crypto debugger dialyzer diameter edoc eldap erl_docgen erl_interface erts et eunit gs hipe ic inets jinterface megaco mnesia observer orber os_mon otp_mibs parsetools percept pman public_key reltool runtime_tools snmp ssh ssl syntax_tools test_server toolbar tv typer webtool xmerl"

do_configure() {
    cat > ${XCOMP_CONF_PATH} <<EOF
erl_xcomp_build=${BUILD_SYS}
erl_xcomp_host=${HOST_SYS}
erl_xcomp_configure_flags=--with-ssl=${STAGING_DIR_HOST}${layout_exec_prefix}
erl_xcomp_sysroot=${STAGING_DIR_HOST}
EOF 

    for lib in odbc wx ; do touch ${S}/lib/${lib}/SKIP ; done

    rm -rf ${S}/lib/wx

    ./otp_build configure --xcomp-conf=${XCOMP_CONF_PATH}
}

do_compile() {    
    ./otp_build boot -a    
    ./otp_build release -a
}

do_install() {
    make install DESTDIR=${D}

    pushd ${D}/usr/local/lib/erlang/ 2> /dev/null
    ./Install -cross -minimal /usr/local/erlang
    rm -f Install

    rm -rf ${D}/usr/local/lib/erlang/usr
}

def get_erlang_libs(d):
    import os, bb
    install_root = bb.data.getVar('D', d, 1)
    libdir = '/usr/local/lib'[1:]
    libs = ["/usr/local/bin/dialyzer", "/usr/local/lib/erlang/bin/dialyzer"]
    erlang_lib = bb.data.getVar('ERL_LIBS', d, 1)
    for fname in erlang_lib.split():
        lname = fname + '-*' + '/'
        libs.append(os.path.join("/usr/local/lib", "erlang/lib", lname))
    libs.sort()
    return libs

def get_erlang_libs_static(d):
    import os, bb
    install_root = bb.data.getVar('D', d, 1)
    libdir = '/usr/local/lib'[1:]
    libs = []
    erlang_lib = bb.data.getVar('ERL_LIBS', d, 1)
    for fname in erlang_lib.split():
        lname = fname + '-*' + '/'
        libs.append(os.path.join("/usr/local/lib", "erlang/lib", lname, "lib/*.a"))
        libs.append(os.path.join("/usr/local/lib", "erlang/lib", lname, "priv/lib/*.a"))
    libs.sort()
    return libs    

def get_erlang_libs_dev(d):
    import os, bb
    install_root = bb.data.getVar('D', d, 1)
    libdir = '/usr/local/lib'[1:]
    libs = []
    erlang_lib = bb.data.getVar('ERL_LIBS', d, 1)
    for fname in erlang_lib.split():
        lname = fname + '-*' + '/'
        libs.append(os.path.join("/usr/local/lib", "erlang/lib", lname, "src"))
        libs.append(os.path.join("/usr/local/lib", "erlang/lib", lname, "include"))
    libs.sort()
    return libs    

FILES_${PN}-libs-dbg += " /usr/local/lib/erlang/*/.debug /usr/local/lib/erlang/*/*/.debug /usr/local/lib/erlang/*/*/*/.debug /usr/local/lib/erlang/*/*/*/*/.debug /usr/local/lib/erlang/*/*/*/*/*/.debug "

FILES_${PN}-libs-dev += " ${@' '.join(get_erlang_libs_dev(d))}"

FILES_${PN}-libs-staticdev += " ${@' '.join(get_erlang_libs_static(d))}"

FILES_${PN}-libs += " ${@' '.join(get_erlang_libs(d))}"

FILES_${PN}-doc += " /usr/local/lib/erlang/erts-*/doc /usr/local/lib/erlang/erts-*/man /usr/local/lib/erlang/lib/*/examples /usr/local/lib/erlang/misc"
FILES_${PN}-dev += " /usr/local/lib/erlang/erts-*/include /usr/local/lib/erlang/erts-*/src"
FILES_${PN}-staticdev += " /usr/local/lib/erlang/erts-*/lib/*.a /usr/local/lib/erlang/erts-*/lib/internal/*.a /usr/local/lib/erlang/erts-*/lib/internal/README"

FILES_${PN} += " /usr/local/lib/erlang/releases /usr/local/bin/* /usr/local/lib/erlang/bin /usr/local/lib/erlang/lib /usr/local/lib/erlang/erts-*/bin /usr/local/lib/erlang/lib/*/ebin"

FILES_${PN}-dev += " /usr/local/lib/erlang/lib/*/include /usr/local/lib/erlang/lib/*/src /usr/local/lib/erlang/lib/*/c_src"

PACKAGES =+ "${PN}-libs-dbg ${PN}-libs-staticdev ${PN}-libs-dev ${PN}-libs"

SRC_URI[md5sum] = "ca63bcde0e5ae0f2df9457f97b3115a4"
SRC_URI[sha256sum] = "6ab8ad1df8185345554a4b80e10fd8be06c4f2b71b69dcfb8528352787b32f85"