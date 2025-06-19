#!/usr/bin/env bash

versions=("25.3.2.21" "26.2.5.13" "27.3.4.1" "28.0.1" "29.0-rc0")

for version in ${versions[@]}
do
  target="../../../recipes-devtools/erlang/erlang-${version}-manifest.inc"
  ./generate-manifest --erlang-version ${version} > ${target}
done
