#!/usr/bin/env bash

versions=("26.2.5.21" "27.0" "27.3.4.12" "28.5.0.1" "29.0.1")

for version in ${versions[@]}
do
  target="../../../recipes-devtools/erlang/erlang-${version}-manifest.inc"
  ./generate-manifest --erlang-version ${version} > ${target}
done
