from oeqa.core.decorator.depends import OETestDepends
from oeqa.core.decorator.oetimeout import OETimeout
from oeqa.runtime.decorator.package import OEHasPackage
from oeqa.runtime.decorator.lux import LuxTestCase
from oeqa.runtime.meta_erlang import MetaErlangTestCase

class YawsTest(MetaErlangTestCase):

    @OETimeout(300)    
    @OEHasPackage(["lux"])
    @OETestDepends(['ssh.SSHTest.test_ssh'])
    @LuxTestCase("start_restart_stop.lux", "yaws")
    def test_start_restart_stop(self):
        self.run_lux_test_case()

    @OETestDepends(['yaws.YawsTest.test_start_restart_stop'])
    @LuxTestCase("basic_get.lux", "yaws")
    def test_basic_get(self):
        self.run_lux_test_case()