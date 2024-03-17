#!/usr/bin/env bash

versions=("25.1.2.1" "25.3.2.10" "26.2.1" "27.0-rc1")

for version in ${versions[@]}
do
  target="../../../recipes-devtools/erlang/erlang-${version}-manifest.inc"
  ./generate-manifest --erlang-version ${version} > ${target}
done
