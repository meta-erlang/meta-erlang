#!/usr/bin/env bash

versions=("25.3.2.21" "26.2.5.15" "27.3.4.3" "28.3" "29.0-rc0")

for version in ${versions[@]}
do
  target="../../../recipes-devtools/erlang/erlang-${version}-manifest.inc"
  ./generate-manifest --erlang-version ${version} > ${target}
done
