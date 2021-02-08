#!/usr/bin/env bash

versions=("20.0.5" "21.1.0" "21.1.4" "22.0.7" "22.1.8" "22.2.8" "22.3.4.12" "22.3.4.13" "22.3" "23.0.3" "23.0.4" "23.1.2" "23.1.4" "23.1.5" "23.2.1" "23.2.3")

for version in ${versions[@]}
do
  target="../../../recipes-devtools/erlang/erlang-${version}-manifest.inc"
  ./generate-manifest --erlang-version ${version} > ${target}
done
