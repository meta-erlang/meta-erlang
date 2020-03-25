#!/usr/bin/env bash

versions=("18.1.5" "18.2.3" "18.3.4" "19.0" "19.3.6.2" "20.0.5" "21.1.0" "22.0.7" "22.1.8" "22.2.8" "22.3")

for version in ${versions[@]}
do
  target="../../../recipes-devtools/erlang/erlang-${version}-manifest.inc"
  ./generate-manifest --erlang-version ${version} > ${target}
done
