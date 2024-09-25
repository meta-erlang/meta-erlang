# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=00d0a5fff8216c94faadd9a51b23695e"

SRC_URI = "git://github.com/bellard/quickjs;protocol=https;branch=master"

# Modify these as desired
PV = "2024-02-14+git"
SRCREV = "6a89d7c27099be84e5312a7ec73205d6a7abe1b4"

S = "${WORKDIR}/git"

# NOTE: this is a Makefile-only piece of software, so we cannot generate much of the
# recipe automatically - you will need to examine the Makefile yourself and ensure
# that the appropriate arguments are passed in.

do_configure () {
	# Specify any needed configure commands here
	:
}

do_compile () {
	# You will almost certainly need to add additional arguments here
	oe_runmake PREFIX=${D}${prefix}
}

do_install () {
	# This is a guess; additional arguments may be required
	oe_runmake install PREFIX=${D}${prefix}
}

BBCLASSEXTEND = "native"