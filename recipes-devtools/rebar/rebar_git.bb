SUMMARY = "rebar is an Erlang build tool that makes it easy to compile and test Erlang applications, port drivers and releases."
DESCRIPTION = "rebar is a self-contained Erlang script, so it's easy to distribute or even embed directly in a project. Where possible, rebar uses standard Erlang/OTP conventions for project structures, thus minimizing the amount of build configuration work. rebar also provides dependency management, enabling application writers to easily re-use common libraries from a variety of locations (git, hg, etc)."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

SRCREV = "9e60c709fdf759b614ab1441f36f51936bb5bbd4"
PV = "2.6.2-1+git${SRCPV}"
PR = "r2"

SRC_URI = "git://github.com/apache/couchdb-rebar;branch=main;protocol=https"

DEPENDS += "erlang-native"

do_compile () {
    ./bootstrap
}

do_install() {
        install -d 0755 ${D}/${bindir}
	install -m 0755 ${S}/rebar ${D}/${bindir}/rebar
}

FILES:${PN} = "${bindir}"
FILES:${PN}-dbg += "/.debug"

BBCLASSEXTEND = "native nativesdk"

