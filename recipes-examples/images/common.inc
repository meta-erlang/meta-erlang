IMAGE_FEATURES += "ssh-server-openssh \
    allow-empty-password \
    allow-root-login \
    empty-root-password"

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-core-full-cmdline \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    ${APPLICATION} \
    "