#!/usr/bin/env bash

versions=("22.3.4.22" "23.3.4.8" "24.1.7" "24.2")

for version in ${versions[@]}
do
  target="../../../recipes-devtools/erlang/erlang-${version}-manifest.inc"
  ./generate-manifest --erlang-version ${version} > ${target}
done
