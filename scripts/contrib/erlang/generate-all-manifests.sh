#!/usr/bin/env bash

versions=("25.1.2.1" "25.3.2.15" "26.2.5.4" "27.1.2" "28.0-rc0")

for version in ${versions[@]}
do
  target="../../../recipes-devtools/erlang/erlang-${version}-manifest.inc"
  ./generate-manifest --erlang-version ${version} > ${target}
done
