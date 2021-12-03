SUMMARY = "rebar is an Erlang build tool that makes it easy to compile and test Erlang applications, port drivers and releases."
DESCRIPTION = "rebar is a self-contained Erlang script, so it's easy to distribute or even embed directly in a project. Where possible, rebar uses standard Erlang/OTP conventions for project structures, thus minimizing the amount of build configuration work. rebar also provides dependency management, enabling application writers to easily re-use common libraries from a variety of locations (git, hg, etc)."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

S = "${WORKDIR}/git"
SRCREV = "365ac649dc818619757f96a699ddb174f004cff9"
PV = "2.6.1-git${SRCPV}"
PR = "r0"

SRC_URI = "git://github.com/rebar/rebar;branch=master;protocol=https"

DEPENDS += "erlang-native"

do_compile () {
    ./bootstrap
}

do_install() {
        install -d 0755 ${D}/${bindir}
	install -m 0755 ${S}/rebar ${D}/${bindir}/rebar
}

FILES_${PN} = "${bindir}"
FILES_${PN}-dbg += "/.debug"

BBCLASSEXTEND = "native nativesdk"

