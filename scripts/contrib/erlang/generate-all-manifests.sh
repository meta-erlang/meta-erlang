#!/usr/bin/env bash

versions=("24.2" "24.3.4.11" "25.0.4" "25.1.2.1" "25.3.2" "26.0")

for version in ${versions[@]}
do
  target="../../../recipes-devtools/erlang/erlang-${version}-manifest.inc"
  ./generate-manifest --erlang-version ${version} > ${target}
done
