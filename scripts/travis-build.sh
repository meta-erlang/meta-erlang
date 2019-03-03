#! /bin/bash

set -ex

SRC_DIR=${1-$(pwd)}
LAYERDIR=$(dirname $0)/..

# Check for large directories (>= 1GB).
du -h $HOME | grep '^[0-9\.]\+G' || true
df -h .
# Remove .rvm to free up space. This can only be done when we do not
# depend anymore on TravisCI add-ons like "deploy" or "artifacts",
# which are implemented in Ruby.
rm -rf $HOME/.rvm
df -h .
# Set up.
$(pwd)/scripts/travis-setup.sh $(pwd)
. init-travis-build-env
tail -50 conf/local.conf
# And now building...
../scripts/travis-cmd-wrapper.py --deadline=$deadline bitbake erlang-native erlang || BITBAKE_RESULT=1
df -h .
# The download directory can become quite large.
(du -h -c -s $(ls -1 -d downloads/* downloads/git2/* tmp*/work/*/*/downloads 2>/dev/null | grep -v -e '.done$' -e '/git2$')) || true
(df -h .; du -h -s * sstate-cache) || true
# temp files are preserved by rm_work.bbclass, but should not be that large.
du -h -c -s tmp*/work/*/*/*/temp | tail -1
