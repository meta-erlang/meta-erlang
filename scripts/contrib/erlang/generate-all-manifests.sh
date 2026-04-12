#!/usr/bin/env bash

versions=("26.2.5.18" "27.3.4.9" "28.4.1" "29.0-rc2")

for version in ${versions[@]}
do
  target="../../../recipes-devtools/erlang/erlang-${version}-manifest.inc"
  ./generate-manifest --erlang-version ${version} > ${target}
done
