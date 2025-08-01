SUMMARY = "Erlang build tool that makes it easy to compile and test Erlang applications and releases. http://www.rebar3.org"
DESCRIPTION = "rebar is a self-contained Erlang script, so it's easy to distribute or even embed directly in a project. Where possible, rebar uses standard Erlang/OTP conventions for project structures, thus minimizing the amount of build configuration work. rebar also provides dependency management, enabling application writers to easily re-use common libraries from a variety of locations (git, hg, etc)."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

SRC_URI = "git://github.com/erlang/rebar3;nobranch=1;protocol=https \
           file://environment.d-rebar3.sh"

INC_PR = "r0"

DEPENDS += "erlang-native"

export ERL_COMPILER_OPTIONS = "deterministic"

do_compile () {
    ./bootstrap
}

do_install() {
    install -d 0755 ${D}/${bindir}
    install -m 0755 ${S}/rebar3 ${D}/${bindir}/rebar3
}

do_install:append:class-nativesdk() {
    mkdir -p ${D}${SDKPATHNATIVE}/environment-setup.d
    install -m 644 ${UNPACKDIR}/environment.d-rebar3.sh ${D}${SDKPATHNATIVE}/environment-setup.d/rebar3.sh
}

FILES:${PN} = "${bindir}/rebar3"
FILES:${PN}-dbg += "/.debug"

BBCLASSEXTEND = "native nativesdk"

FILES:${PN}:append:class-nativesdk = " ${SDKPATHNATIVE}/environment-setup.d/rebar3.sh"
