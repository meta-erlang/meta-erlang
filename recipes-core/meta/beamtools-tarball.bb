DESCRIPTION = "SDK type target for building a standalone tarball containing BEAM languages and tools. The \
               tarball can be used to run Erlang and Elixir builds on systems which don't meet the usual version requirements."
SUMMARY = "Standalone tarball for running BEAM builds on systems with does not provide or is hard to get BEAM based languages"
LICENSE = "MIT"

TOOLCHAIN_TARGET_TASK ?= ""

TOOLCHAIN_HOST_TASK ?= "\
    nativesdk-sdk-provides-dummy \
    nativesdk-rebar3 \
    nativesdk-erlfmt \
    nativesdk-elvis \
    nativesdk-erlang-ls \
    nativesdk-concuerror \
    nativesdk-erlang \
    nativesdk-erlang-modules \
    nativesdk-erlang-modules-dev \
    nativesdk-elixir \
    nativesdk-elixir-modules-dev \
    nativesdk-livebook \
    nativesdk-exdoc \
    nativesdk-gleam \
    nativesdk-lux \
    "

MULTIMACH_TARGET_SYS = "${SDK_ARCH}-nativesdk${SDK_VENDOR}-${SDK_OS}"
PACKAGE_ARCH = "${SDK_ARCH}_${SDK_OS}"
PACKAGE_ARCHS = ""
TARGET_ARCH = "none"
TARGET_OS = "none"

SDK_PACKAGE_ARCHS += "beamtools-dummy-${SDKPKGSUFFIX}"

require conf/include/beamtools.inc

TOOLCHAIN_OUTPUTNAME ?= "${SDK_ARCH}-beamtools-nativesdk-standalone-${DISTRO_VERSION}-erlang-${ERLANG_VERSION}-elixir-${ELIXIR_VERSION}-gleam-${GLEAM_VERSION}"

SDK_TITLE = "BEAM tools"
SDK_VERSION = "${@d.getVar('DISTRO_VERSION').replace('snapshot-${METADATA_REVISION}', 'snapshot')}-erlang-${ERLANG_VERSION}-elixir-${ELIXIR_VERSION}-gleam-${GLEAM_VERSION}"
SDKPATHINSTALL = "/opt/beamtools/${SDK_VERSION}"

RDEPENDS = "${TOOLCHAIN_HOST_TASK}"

EXCLUDE_FROM_WORLD = "1"

inherit populate_sdk
inherit toolchain-scripts-base
inherit nopackages

deltask install
deltask populate_sysroot

do_populate_sdk[stamp-extra-info] = "${PACKAGE_ARCH}"

REAL_MULTIMACH_TARGET_SYS = "none"

create_sdk_files:append () {
	rm -f ${SDK_OUTPUT}/${SDKPATH}/site-config-*
	rm -f ${SDK_OUTPUT}/${SDKPATH}/environment-setup-*
	rm -f ${SDK_OUTPUT}/${SDKPATH}/version-*

	# Generate new (mini) sdk-environment-setup file
	script=${1:-${SDK_OUTPUT}/${SDKPATH}/environment-setup-${SDK_SYS}}
	touch $script
	echo 'export PATH="${SDKPATHNATIVE}${bindir_nativesdk}:${SDKPATHNATIVE}${sbindir_nativesdk}:${SDKPATHNATIVE}${base_bindir_nativesdk}:${SDKPATHNATIVE}${base_sbindir_nativesdk}:$PATH"' >> $script
	echo 'export OECORE_NATIVE_SYSROOT="${SDKPATHNATIVE}"' >> $script
	# beamtools does not provide target artifacts, only native. It's still
	# possible to use beamtools in order to build all BEAM applications
	echo 'export OECORE_TARGET_SYSROOT="${OECORE_NATIVE_SYSROOT}"' >> $script
	if [ -e "${SDK_OUTPUT}${SDKPATHNATIVE}${sysconfdir}/ssl/certs/ca-certificates.crt" ]; then
		echo 'export GIT_SSL_CAINFO="${SDKPATHNATIVE}${sysconfdir}/ssl/certs/ca-certificates.crt"' >>$script
		echo 'export SSL_CERT_FILE="${SDKPATHNATIVE}${sysconfdir}/ssl/certs/ca-certificates.crt"' >>$script
		echo 'export REQUESTS_CA_BUNDLE="${SDKPATHNATIVE}${sysconfdir}/ssl/certs/ca-certificates.crt"' >>$script
		echo 'export CURL_CA_BUNDLE="${SDKPATHNATIVE}${sysconfdir}/ssl/certs/ca-certificates.crt"' >>$script
	fi
	echo 'HOST_PKG_PATH=$(command -p pkg-config --variable=pc_path pkg-config 2>/dev/null || true)' >>$script
	echo 'export PKG_CONFIG_LIBDIR=${SDKPATHNATIVE}/${libdir}/pkgconfig:${SDKPATHNATIVE}/${datadir}/pkgconfig:${HOST_PKG_PATH:-/usr/lib/pkgconfig:/usr/share/pkgconfig}' >>$script
	echo 'unset HOST_PKG_PATH'

	toolchain_create_sdk_version ${SDK_OUTPUT}/${SDKPATH}/version-${SDK_SYS}
	beamtools_add_version ${SDK_OUTPUT}/${SDKPATH}/version-${SDK_SYS}

	cat >> $script <<EOF
# Detect host ca file/path, export for envfile to use
# /etc/ssl/certs/ca-certificates.crt Debian systems
# /etc/pki/tls/certs/ca-bundle.crt Fedora systems
# /etc/ssl/ca-bundle.pem Suse systems
export CAFILE
export CAPATH
for a in /etc/ssl/certs/ca-certificates.crt \
    /etc/pki/tls/certs/ca-bundle.crt \
    /etc/ssl/ca-bundle.pem ; do
    if test -f "\$a"; then
        CAFILE="\$a"
        break
    fi
done

a="/etc/ssl/certs"
if test -d "\$a" && ls "\$a"/[0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f][0-9a-f].0 >/dev/null 2>/dev/null; then
    CAPATH="\$a"
fi

if [ -d "\$OECORE_NATIVE_SYSROOT/environment-setup.d" ]; then
	for envfile in \$OECORE_NATIVE_SYSROOT/environment-setup.d/*.sh; do
		. \$envfile
	done
fi

# We have to unset this else it can confuse oe-selftest and other tools
# which may also use the overlapping namespace.
unset OECORE_NATIVE_SYSROOT CAFILE CAPATH
EOF

	if [ "${SDKMACHINE}" = "i686" ]; then
		echo 'export NO32LIBS="0"' >>$script
		echo 'echo "$BB_ENV_PASSTHROUGH_ADDITIONS" | grep -q "NO32LIBS"' >>$script
		echo '[ $? != 0 ] && export BB_ENV_PASSTHROUGH_ADDITIONS="NO32LIBS $BB_ENV_PASSTHROUGH_ADDITIONS"' >>$script
	fi
}

# beamtools-tarball doesn't need config site
TOOLCHAIN_NEED_CONFIGSITE_CACHE = ""

# The recipe doesn't need any default deps
INHIBIT_DEFAULT_DEPS = "1"

beamtools_add_version () {
	local versionfile=$1
	echo 'Erlang: ${ERLANG_VERSION}' >> $versionfile
	echo 'Elixir: ${ELIXIR_VERSION}' >> $versionfile
	echo 'Gleam: ${GLEAM_VERSION}' >> $versionfile
}
