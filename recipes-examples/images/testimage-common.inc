# Default configuration for testimage

#TESTIMAGE_AUTO:qemuall = "1"

TEST_SUITES:qemuall = "ping ssh"

TEST_RUNQEMUPARAMS:qemuall = "slirp novga nographic"

# lux is needed for running test cases. Install it only if testimage is enabled
IMAGE_INSTALL += "${@bb.utils.contains('IMAGE_CLASSES', 'testimage', 'lux', '', d)}"