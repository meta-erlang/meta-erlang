from oeqa.core.decorator.depends import OETestDepends
from oeqa.core.decorator.oetimeout import OETimeout
from oeqa.runtime.decorator.package import OEHasPackage
from oeqa.runtime.decorator.lux import LuxTestCase
from oeqa.runtime.meta_erlang import MetaErlangTestCase

class BeamTest(MetaErlangTestCase):

    @OETimeout(300)    
    @OEHasPackage(["lux"])
    @OETestDepends(['ssh.SSHTest.test_ssh'])
    @LuxTestCase("erlang.lux", "beam")
    def test_erlang(self):
        self.run_lux_test_case()

    @OEHasPackage(["lux"])
    @OETestDepends(['ssh.SSHTest.test_ssh'])
    @LuxTestCase("elixir.lux", "beam")
    def test_elixir(self):
        self.run_lux_test_case()