#!/usr/bin/env bash

versions=("26.2.5.21" "27.3.4.19" "28.5.0.8" "29.1.2")

for version in ${versions[@]}
do
  target="../../../recipes-devtools/erlang/erlang-${version}-manifest.inc"
  ./generate-manifest --erlang-version ${version} > ${target}
done
