.PHONY: test

LUX_OPTIONS +=
LUX_FILES ?= .

LUX_FILES_ERLANG = "beam_otp_29_ex_118.lux beam_otp_28_ex_118.lux beam_otp_27_ex_118.lux beam_otp_26_ex_118.lux"

.PHONY: help
.DEFAULT_GOAL := help

all: test

test: ## Run lux test scripts. LUX_FILES variable can be used to run specific scripts.
	lux --config_dir ../support/luxcfg --progress=summary $(LUX_OPTIONS) $(LUX_FILES)

test-erlang: ## Run lux test for all supported erlang versions.
	$(MAKE) test LUX_FILES=$(LUX_FILES_ERLANG)

rerun: ## Rerun lux test scripts.
	$(MAKE) test LUX_OPTIONS="--rerun=fail"

setup-bitbake-setup: ## Create a venv and install bitbake-setup.
	python3 -m venv --clear ../../bitbake-setup-venv
	. ../../bitbake-setup-venv/bin/activate && pip install bitbake-setup

clean: ## Clean lux logs.
	rm -rf lux_logs

help: ## Display this help message.
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m<target>\033[0m\n\nTargets:\n"} /^[a-zA-Z0-9_-]+:.*?##/ { printf "  \033[36m%-15s\033[0m %s\n", $$1, $$2 }' $(MAKEFILE_LIST)
