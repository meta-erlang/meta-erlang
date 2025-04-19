#!/usr/bin/env bash

versions=("25.3.2.20" "26.2.5.11" "27.3.3" "28.0-rc3")

for version in ${versions[@]}
do
  target="../../../recipes-devtools/erlang/erlang-${version}-manifest.inc"
  ./generate-manifest --erlang-version ${version} > ${target}
done
