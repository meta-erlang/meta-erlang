import os

from oeqa.core.decorator import OETestDecorator, registerDecorator
from oeqa.runtime.meta_erlang import MetaErlangTestCase

@registerDecorator
class LuxTestCase(OETestDecorator):
    """
    This decorator setup a lux test case on target.

    Attributes
    ----------
    test_case : string
        Lux test case full name. E.g.: my_test.lux

    test_case_dir : string
        Folder relative to lib/oeqa/runtime/files which
        has lux test cases.
    """

    attrs = ('test_case', 'test_case_dir')

    _dest = None
    _test_case_name = None

    def setUpDecorator(self):
        test_case_name, _ = os.path.splitext(self.test_case)
        self._test_case_name = test_case_name

        if not self.case.tc._registry.get('lux'):
            self.case.tc._registry['lux'] = {}
        self.case.tc._registry['lux']['test_case'] = self.test_case
        self.case.tc._registry['lux']['test_case_dir'] = self.test_case_dir
        self.case.tc._registry['lux']['test_case_name'] = self._test_case_name

        self._dest = os.path.join(MetaErlangTestCase.LUX_WORKDIR, self.test_case_dir)

        self._lux_copy_test_case_files()

    def tearDownDecorator(self):
        test_case = os.path.join(self._dest, self.test_case)
        self.case.tc.target.run(f'rm -rf {test_case}')

    def _lux_copy_test_case_files(self):
        src = os.path.join(
            os.path.dirname(os.path.realpath(__file__)),
            '../files/lux',
            self.test_case_dir)
        self.case.tc.target.copyTo(src, self._dest)