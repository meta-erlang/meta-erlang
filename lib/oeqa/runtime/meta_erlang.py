import os

from shutil import rmtree
from oeqa.runtime.case import OERuntimeTestCase

class MetaErlangTestCase(OERuntimeTestCase):
    """
    Prepare and run Lux test cases on target image.

    This class copy all local files from 'files/lux/support'
    to LUX_WORKDIR on target before.

    The decorator LuxTestCase is responsible for configuring the
    lux test case to be executed and also where the lux tests will
    be copied from.

    Methods
    -------
    run_lux_test_case()
        run a lux test case
    """

    LUX_WORKDIR = '/tmp/lux'
    LUX_SUPPORT_DIR = 'lux/support'
    LUX_CONFIG_DIR = 'support/luxcfg'
    LUX_TIMEOUT = 1200


    @classmethod
    def setUpClass(cls):
        cls.lux_startup()


    @classmethod
    def tearDownClass(cls):
        cls.lux_finishup()


    @classmethod
    def lux_startup(cls):
        cls._lux_copy_support_files()

        if not hasattr(cls.tc, "extraresults"):
            cls.tc.extraresults = {}
        cls.extras = cls.tc.extraresults
        cls.extras['lux.rawlogs'] = {'log': ""}


    @classmethod
    def lux_finishup(cls):
        cls.tc.target.run(f'rm -rf {MetaErlangTestCase.LUX_WORKDIR}')


    @classmethod
    def _lux_copy_support_files(cls):
        myfilesdir = os.path.join(os.path.dirname(os.path.realpath(__file__)), 'files')
        src = os.path.join(myfilesdir, MetaErlangTestCase.LUX_SUPPORT_DIR)
        _, _ = cls.tc.target.run(f'mkdir -p {MetaErlangTestCase.LUX_WORKDIR}')
        cls.tc.target.copyTo(src, MetaErlangTestCase.LUX_WORKDIR)


    def run_lux_test_case(self):
        """
        Run a lux test case previously configured using LuxTestCase decorator.

        After finishing test execution, all logs will be transferred to
        'WORKDIR/lux_logs' folder.
        """
        luxscript = self.tc._registry['lux']['test_case']
        test_case_dir = self.tc._registry['lux']['test_case_dir']
        workdir = os.path.join(MetaErlangTestCase.LUX_WORKDIR, test_case_dir)
        configdir = os.path.join(MetaErlangTestCase.LUX_WORKDIR, MetaErlangTestCase.LUX_CONFIG_DIR)
        configname = f'{self.td.get("MACHINE")}.luxcfg'

        log_dir = self._get_lux_log_dir(luxscript)

        lux_command = f'cd {workdir}; lux --log_dir {log_dir} \
            --config_name {configname} \
            --config_dir {configdir} \
            {luxscript}'

        self.logger.debug(f'lux command: {lux_command}')

        status, output = self.target.run(lux_command, timeout=MetaErlangTestCase.LUX_TIMEOUT)

        self.extras['lux.rawlogs']['log'] = self.extras['lux.rawlogs']['log'] + output

        self._transfer_logs(luxscript)

        msg = f'Test case {luxscript} failed, workdir {workdir} \
              lux command: {lux_command} output: {output}'
        self.assertEqual(status, 0, msg=msg)

        return (status, output)


    # Copy the log files to be parsed locally
    def _transfer_logs(self, luxscript):
        local_logs = os.path.join(self.td.get('WORKDIR'), 'lux_logs')
        local_logs_test_case = os.path.join(local_logs, self._get_testcase(luxscript))
        if os.path.exists(local_logs_test_case):
            rmtree(local_logs_test_case)
        os.makedirs(local_logs_test_case)
        log_dir = self._get_lux_log_dir(luxscript)
        self.target.copyFrom(log_dir, local_logs)


    def _get_testcase(self, luxscript):
        testcase, _ = os.path.splitext(luxscript)
        return testcase


    def _get_lux_log_dir(self, luxscript):
        return os.path.join(MetaErlangTestCase.LUX_WORKDIR,
                            'logs',
                            self._get_testcase(luxscript))