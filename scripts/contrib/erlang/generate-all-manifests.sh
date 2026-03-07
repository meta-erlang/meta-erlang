#!/usr/bin/env bash

versions=("25.3.2.21" "26.2.5.17" "27.3.4.8" "28.4" "29.0-rc1")

for version in ${versions[@]}
do
  target="../../../recipes-devtools/erlang/erlang-${version}-manifest.inc"
  ./generate-manifest --erlang-version ${version} > ${target}
done
