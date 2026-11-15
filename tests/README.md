# Tests

This directory contains the automated test suites for the **meta-erlang** Yocto
layer. Tests are written and executed with [Lux](https://github.com/hawk/lux),
an automated test tool for interacting with command line programs.

## Layout

- [tests/configurations](configurations) — `bitbake-setup` configuration files
  describing the different BitBake environments under test.
- [tests/lux/test](lux/test) — Lux test cases and the `Makefile` entry point.
- [tests/lux/support](lux/support) — helper scripts and configuration used by
  the tests, including the Lux configuration in
  [tests/lux/support/luxcfg/luxcfg](lux/support/luxcfg/luxcfg).

## Prerequisites

- [Lux](https://github.com/hawk/lux) installed and available in your `PATH`.
- A working Yocto/BitBake environment.
- [`bitbake-setup`](https://docs.yoctoproject.org/bitbake/dev/bitbake-user-manual/bitbake-user-manual-environment-setup.html#)
  installed in a Python virtual environment (see below).

### Installing bitbake-setup

From the [tests/lux/test](lux/test) directory, run:

```sh
make setup-bitbake-setup
```

This creates a Python virtual environment at
[tests/bitbake-setup-venv](bitbake-setup-venv) and installs `bitbake-setup`
into it. It is equivalent to:

```sh
python3 -m venv --clear tests/bitbake-setup-venv
. tests/bitbake-setup-venv/bin/activate
pip install bitbake-setup
```

The test macros in
[tests/lux/support/luxinc/bitbake-setup.luxinc](lux/support/luxinc/bitbake-setup.luxinc)
activate this virtual environment before invoking `bitbake-setup`.

## Configuration

Default settings are provided in
[tests/lux/support/luxcfg/luxcfg](lux/support/luxcfg/luxcfg). Architecture or
host specific configuration files, when present, take precedence over this
default file.

Key variables you may need to adjust for your environment:

| Variable                      | Default                      | Description                          |
| ----------------------------- | ---------------------------- | ------------------------------------ |
| `YOCTO_RELEASE`               | `master`                     | Yocto release branch to test against.|
| `YOCTO_DIR`                   | `/data-work/yocto`           | Base directory for Yocto work.       |
| `WORKDIR`                     | `../../`                     | Working directory for the tests.     |
| `BBS_TOP_DIR_PREFIX`          | `/data-work/yocto/builds`    | `bitbake-setup` builds directory.    |
| `BBS_DL_DIR`                  | `/data-work/yocto/downloads` | `bitbake-setup` downloads directory. |
| `BBS_USE_FULL_SETUP_DIR_NAME` | `yes`                        | Use full setup directory name.       |

You can override any variable on the command line, for example:

```sh
lux --var YOCTO_DIR=/my/yocto <testcase>.lux
```

## bitbake-setup configuration

The file
[tests/configurations/meta-erlang-poky-master.conf.json](configurations/meta-erlang-poky-master.conf.json)
is used by
[`bitbake-setup`](https://docs.yoctoproject.org/bitbake/dev/bitbake-user-manual/bitbake-user-manual-environment-setup.html#)
to configure the different BitBake environments exercised by the tests.

It describes:

- **`sources`** — the Git repositories (and their branches/revisions) that make
  up each environment, such as `bitbake`, `openembedded-core`, `meta-yocto`,
  `meta-openembedded` and `meta-erlang`.
- **`bitbake-setup.configurations`** — the set of BitBake configurations to
  generate. Each entry defines the layers (`bb-layers`), the setup directory
  name (`setup-dir-name`) and the OE fragments (`oe-fragments` and
  `oe-fragments-one-of`) that enable specific machines, distributions and
  meta-erlang features. These include:
  - **`yocto-compatible`** — Yocto Project compatibility checks.
  - **`poky`** — base meta-erlang build on Poky.
  - **`beamtools`** — beamtools build, with/without OpenGL and optional
    networking/Python layers.
  - **`beamruntime`** — beamruntime build against `glibc` or `musl`.
  - **image examples** — ready-to-run images bundling BEAM applications such as
    `livebook`, `yaws`, `wings`, `couchdb`, `riak`, `rabbitmq`, `erlang-red`
    and `erlang-elixir`.

Supported target machines include `qemux86-64`, `qemuarm64`, `qemuriscv64`,
`genericarm64` and `genericx86-64`, all using the `poky` distribution.

Refer to the
[bitbake-setup documentation](https://docs.yoctoproject.org/bitbake/dev/bitbake-user-manual/bitbake-user-manual-environment-setup.html#)
for details on how this configuration file is consumed.

## Running the tests

All commands below are run from the [tests/lux/test](lux/test) directory:

```sh
cd tests/lux/test
```

Display all available targets:

```sh
make help
```

### Available targets

| Target         | Description                                                   |
| -------------- | ------------------------------------------------------------- |
| `setup-bitbake-setup` | Create a venv and install `bitbake-setup`.             |
| `test`         | Run lux test scripts. Use `LUX_FILES` to run specific scripts.|
| `test-erlang`  | Run lux tests for all supported Erlang versions.              |
| `rerun`        | Rerun lux test scripts.                                       |
| `clean`        | Clean lux logs.                                               |
| `help`         | Display the help message.                                     |

### Run all tests

```sh
make test
```

### Run a single test

Use the `LUX_FILES` variable to run a specific script:

```sh
make test LUX_FILES=<lux test>
```

### Run tests for all supported Erlang versions

```sh
make test-erlang
```

### Rerun failed or previous tests

```sh
make rerun
```

### Clean logs

```sh
make clean
```

## Results

Lux generates logs and an HTML report in its log directory after each run.
Open the generated `lux_summary.log` or the HTML report to inspect results.