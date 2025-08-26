from oeqa.core.decorator.depends import OETestDepends
from oeqa.core.decorator.oetimeout import OETimeout
from oeqa.runtime.decorator.package import OEHasPackage
from oeqa.runtime.decorator.lux import LuxTestCase
from oeqa.runtime.meta_erlang import MetaErlangTestCase

class RabbitmqTest(MetaErlangTestCase):

    @OETimeout(300)    
    @OEHasPackage(["lux"])
    @OETestDepends(['ssh.SSHTest.test_ssh'])
    @LuxTestCase("start_restart_stop.lux", "rabbitmq")
    def test_start_restart_stop(self):
        self.run_lux_test_case()

    @OETestDepends(['rabbitmq.RabbitmqTest.test_start_restart_stop'])
    @LuxTestCase("ping_and_report.lux", "rabbitmq")
    def test_ping_and_report(self):
        self.run_lux_test_case()