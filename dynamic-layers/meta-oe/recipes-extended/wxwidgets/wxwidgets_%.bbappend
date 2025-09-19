# Enable opengl if available for native
PACKAGECONFIG:class-native:append = " ${@bb.utils.contains('DISTRO_FEATURES', 'x11 opengl', 'opengl', '', d)}"

BBCLASSEXTEND += "nativesdk"
