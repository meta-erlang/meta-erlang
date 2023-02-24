#!/usr/bin/env bash

versions=("24.3.4.8" "25.1.2.1" "25.2.1" "26.0-rc1")

for version in ${versions[@]}
do
  target="../../../recipes-devtools/erlang/erlang-${version}-manifest.inc"
  ./generate-manifest --erlang-version ${version} > ${target}
done
