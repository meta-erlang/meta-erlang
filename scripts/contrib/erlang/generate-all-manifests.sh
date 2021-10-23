#!/usr/bin/env bash

versions=("20.0.5" "21.1.0" "21.3.8.24" "22.0.7" "22.1.8" "22.2.8" "22.3" "22.3.4.22" "23.0.3" "23.1.2" "23.2.3" "23.2.4" "23.3.4.4" "23.3.4.8" "24.0.5" "24.1.2")

for version in ${versions[@]}
do
  target="../../../recipes-devtools/erlang/erlang-${version}-manifest.inc"
  ./generate-manifest --erlang-version ${version} > ${target}
done
