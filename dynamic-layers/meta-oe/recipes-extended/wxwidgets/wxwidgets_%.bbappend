# Enable opengl if available for native
PACKAGECONFIG:class-native = "\
    ${@bb.utils.contains_any('DISTRO_FEATURES', 'x11 wayland', 'gtk', 'no_gui', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11 opengl', 'opengl', '', d)}"

BBCLASSEXTEND += "nativesdk"
