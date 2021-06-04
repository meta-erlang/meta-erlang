#!/usr/bin/env bash

versions=("21.3.8.24" "22.3.4.20" "23.3.4.2" "24.0.2")

for version in ${versions[@]}
do
  target="../../../recipes-devtools/erlang/erlang-${version}-manifest.inc"
  ./generate-manifest --erlang-version ${version} > ${target}
done
