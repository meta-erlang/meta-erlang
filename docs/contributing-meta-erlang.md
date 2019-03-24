To develop the meta-erlang layer, you need to setup the Yocto project environment and starting to make changes on meta-erlang.

## Add a new erlang version

When [Erlang OTP](https://github.com/erlang/otp) team releaes a new version, and you wish to try that version with meta-erlang then you can do the follow steps:

Run the manifest script to create the bitbake files:

```bash
scripts/contrib/erlang/generate-manifest --erlang-version 21.3.2 > recipes-devtools/erlang/erlang-21.3.2-manifest.inc
```

Copy the erlang recipe to the new version:

```bash
cp recipes-devtools/erlang/erlang_21.1.0.bb recipes-devtools/erlang/erlang_21.3.2.bb
```

Create include file to hold the `SRCREV` and point to the git sha1 commit that represents the Erlang OTP version:

```bash
echo 'SRCREV = "7e6fff1e849998a3dd747b4d3cf35f39cb896aa0"' >> recipes-devtools/erlang/erlang-21.3.2.inc
```

Copy the erlang native recipe, changing the version:

```bash
cp recipes-devtools/erlang/erlang-native_21.1.0.bb recipes-devtools/erlang/erlang-native_21.3.2.bb
```

Copy the erlang native sdk, changing the version:

```bash
cp recipes-devtools/erlang/nativesdk-erlang_21.1.0.bb recipes-devtools/erlang/nativesdk-erlang_21.3.2.bb
```
