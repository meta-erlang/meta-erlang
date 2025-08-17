from oeqa.core.decorator.depends import OETestDepends
from oeqa.core.decorator.oetimeout import OETimeout
from oeqa.runtime.decorator.package import OEHasPackage
from oeqa.runtime.decorator.lux import LuxTestCase
from oeqa.runtime.meta_erlang import MetaErlangTestCase

class ErlangRedTest(MetaErlangTestCase):

    @OETimeout(300)    
    @OEHasPackage(["lux"])
    @OETestDepends(['ssh.SSHTest.test_ssh'])
    @LuxTestCase("start_restart_stop.lux", "erlang-red")
    def test_start_restart_stop(self):
        self.run_lux_test_case()