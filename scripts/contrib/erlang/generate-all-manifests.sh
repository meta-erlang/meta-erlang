#!/usr/bin/env bash

versions=("25.1.2.1" "25.2.3" "26.0-rc1")

for version in ${versions[@]}
do
  target="../../../recipes-devtools/erlang/erlang-${version}-manifest.inc"
  ./generate-manifest --erlang-version ${version} > ${target}
done
