#!/usr/bin/env bash

versions=("24.3.4.17" "25.3.2.20" "26.2.5.11")

for version in ${versions[@]}
do
  target="../../../recipes-devtools/erlang/erlang-${version}-manifest.inc"
  ./generate-manifest --erlang-version ${version} > ${target}
done
