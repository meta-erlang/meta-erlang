#!/usr/bin/env python

# generate Erlang Manifest for the OpenEmbedded build system
# (C) 2002-2010 Michael 'Mickey' Lauer <mlauer@vanille-media.de>
# (C) 2007 Jeremy Laine
# licensed under MIT, see COPYING.MIT
#
# June 22, 2011 -- Mark Hatle <mark.hatle@windriver.com>
#  * Updated to no longer generate special -dbg package, instead use the
#    single system -dbg
#  * Update version with ".1" to indicate this change

import os
import sys
import time

VERSION = "18.2.1"
ERTS_VERSION = "7.2.1"

__author__ = "Michael 'Mickey' Lauer <mlauer@vanille-media.de>"
__version__ = "20110222.2"

class MakefileMaker:

    def __init__( self, outfile ):
        """initialize"""
        self.packages = {}
        self.targetPrefix = "${libdir}/erlang/lib"
        self.output = outfile
        self.out( """
# WARNING: This file is AUTO GENERATED: Manual edits will be lost next time I regenerate the file.
# Generator: '%s' Version %s (C) 2002-2010 Michael 'Mickey' Lauer <mlauer@vanille-media.de>
""" % ( sys.argv[0], __version__ ) )

    #
    # helper functions
    #

    def out( self, data ):
        """print a line to the output file"""
        self.output.write( "%s\n" % data )

    def setPrefix( self, targetPrefix ):
        """set a file prefix for addPackage files"""
        self.targetPrefix = targetPrefix

    def doProlog( self ):
        self.out( """ """ )
        self.out( "" )

    def addPackage( self, name, description, dependencies, filenames, keepfiles = False ):
        """add a package to the Makefile"""
        if type( filenames ) == type( "" ):
            filenames = filenames.split()
        fullFilenames = []
        for filename in filenames:
            if filename[0] != "$":
                fullFilenames.append( "%s%s" % ( self.targetPrefix, filename ) )
            else:
                fullFilenames.append( filename )
        self.packages[name] = description, dependencies, fullFilenames, keepfiles

    def doBody( self ):
        """generate body of Makefile"""

        global VERSION

        #
        # generate provides line
        #

        provideLine = 'PROVIDES+="'
        for name in sorted(self.packages):
            provideLine += "%s " % name
        provideLine += '"'

        self.out( "ERTS_VERSION = \"" + ERTS_VERSION + "\"" )
        self.out( provideLine )
        self.out( "" )

        #
        # generate package line
        #

        packageLine = 'PACKAGES="${PN}-dbg '
        for name in reversed(sorted(self.packages)):
            if name.startswith("${PN}-distutils"):
                if name == "${PN}-distutils":
                    packageLine += "%s-staticdev %s " % (name, name)
            elif name != '${PN}-dbg':
                packageLine += "%s " % name
        packageLine += '${PN}-modules"'

        self.out( packageLine )
        self.out( "" )

        #
        # generate package variables
        #

        for name, data in reversed(sorted(self.packages.iteritems())):
            desc, deps, files, keepfiles = data

            #
            # write out the description, revision and dependencies
            #
            self.out( 'ALLOW_EMPTY_%s="1"' % ( name ) )
            self.out( 'DESCRIPTION_%s="%s"' % ( name, desc ) )
            self.out( 'RDEPENDS_%s="%s"' % ( name, deps ) )

            if keepfiles:
                line = 'FILES_%s+="' % name
            else:
                line = 'FILES_%s="' % name

            #
            # check which directories to make in the temporary directory
            #

            dirset = {} # if python had a set-datatype this would be sufficient. for now, we're using a dict instead.
            for target in files:
                dirset[os.path.dirname( target )] = True

            #
            # generate which files to copy for the target (-dfR because whole directories are also allowed)
            #

            for target in files:
                line += "%s " % target

            line += '"'
            self.out( line )
            self.out( "" )

        self.out( 'DESCRIPTION_${PN}-modules="All Erlang modules"' )
        line = 'RDEPENDS_${PN}-modules="'

        pkgtoskip = ('-dbg', '-staticdev', '-dev')

        for name, data in sorted(self.packages.iteritems()):
            if name.endswith(pkgtoskip):
                continue
            if name not in ['${PN}-dev', '${PN}-distutils-staticdev']:
                line += "%s " % name


        self.out( "%s \"" % line )
        self.out( 'ALLOW_EMPTY_${PN}-modules = "1"' )

    def doEpilog( self ):
        self.out( """""" )
        self.out( "" )

    def make( self ):
        self.doProlog()
        self.doBody()
        self.doEpilog()

if __name__ == "__main__":

    if len( sys.argv ) > 1:
        try:
            os.unlink(sys.argv[1])
        except Exception:
            sys.exc_clear()
        outfile = file( sys.argv[1], "w" )
    else:
        outfile = sys.stdout

    m = MakefileMaker( outfile )

    # Add packages here. Only specify dlopen-style library dependencies here, no ldd-style dependencies!
    # Parameters: revision, name, description, dependencies, filenames
    #

    m.addPackage( "${PN}-sasl", "", "", "/sasl-*")
    m.addPackage( "${PN}-sasl-doc", "", "", "/sasl-*/examples")
    m.addPackage( "${PN}-sasl-dbg", "", "", "/sasl-*/bin/.debug /sasl-*/lib/.debug /sasl-*/priv/lib/.debug /sasl-*/priv/obj/.debug /sasl-*/priv/bin/.debug")
    m.addPackage( "${PN}-sasl-dev", "", "", "/sasl-*/src /sasl-*/include")
    m.addPackage( "${PN}-sasl-staticdev", "", "", "/sasl-*/lib/*.a /sasl-*/priv/lib/*.a")

    m.addPackage( "${PN}-stdlib", "", "", "/stdlib-*")
    m.addPackage( "${PN}-stdlib-doc", "", "", "/sasl-*/examples")
    m.addPackage( "${PN}-stdlib-dbg", "", "", "/stdlib-*/bin/.debug /stdlib-*/lib/.debug /stdlib-*/priv/lib/.debug /stdlib-*/priv/obj/.debug /stdlib-*/priv/bin/.debug")
    m.addPackage( "${PN}-stdlib-dev", "", "", "/stdlib-*/src /stdlib-*/include")
    m.addPackage( "${PN}-stdlib-staticdev", "", "", "/stdlib-*/lib/*.a /stdlib-*/priv/lib/*.a")

    m.addPackage( "${PN}-kernel", "", "", "/kernel-*")
    m.addPackage( "${PN}-kernel-doc", "", "", "/kernel-*/examples")
    m.addPackage( "${PN}-kernel-dbg", "", "", "/kernel-*/bin/.debug /kernel-*/lib/.debug /kernel-*/priv/lib/.debug /kernel-*/priv/obj/.debug /kernel-*/priv/bin/.debug")
    m.addPackage( "${PN}-kernel-dev", "", "", "/kernel-*/src /kernel-*/include")
    m.addPackage( "${PN}-kernel-staticdev", "", "", "/kernel-*/lib/*.a /kernel-*/priv/lib/*.a")

    m.addPackage( "${PN}-tools", "", "", "/tools-*")
    m.addPackage( "${PN}-tools-dbg", "", "", "/tools-*/bin/.debug /tools-*/lib/.debug /tools-*/priv/lib/.debug /tools-*/priv/obj/.debug /tools-*/priv/bin/.debug")
    m.addPackage( "${PN}-tools-dev", "", "", "/tools-*/src /tools-*/include")
    m.addPackage( "${PN}-tools-staticdev", "", "", "/tools-*/lib/*.a /tools-*/priv/lib/*.a")

    m.addPackage( "${PN}-appmon", "", "", "/appmon-*")
    m.addPackage( "${PN}-appmon-dbg", "", "", "/appmon-*/bin/.debug /appmon-*/lib/.debug /appmon-*/priv/lib/.debug /appmon-*/priv/obj/.debug /appmon-*/priv/bin/.debug")
    m.addPackage( "${PN}-appmon-dev", "", "", "/appmon-*/src /appmon-*/include")
    m.addPackage( "${PN}-appmon-staticdev", "", "", "/appmon-*/lib/*.a /appmon-*/priv/lib/*.a")

    m.addPackage( "${PN}-asn1", "", "", "/asn1-*")
    m.addPackage( "${PN}-asn1-dbg", "", "", "/asn1-*/bin/.debug /asn1-*/lib/.debug /asn1-*/priv/lib/.debug /asn1-*/priv/obj/.debug /asn1-*/priv/bin/.debug")
    m.addPackage( "${PN}-asn1-dev", "", "", "/asn1-*/src /asn1-*/include")
    m.addPackage( "${PN}-asn1-staticdev", "", "", "/asn1-*/lib/*.a /asn1-*/priv/lib/*.a")

    m.addPackage( "${PN}-common-test", "", "", "${bindir}/ct_run ${libdir}/erlang/bin/ct_run /common_test-* " + "${libdir}/erlang/erts-" + ERTS_VERSION + "/bin/ct_run")
    m.addPackage( "${PN}-common-test-dbg", "", "", "/common_test-*/bin/.debug /common_test-*/lib/.debug /common_test-*/priv/lib/.debug /common_test-*/priv/obj/.debug /common_test-*/priv/bin/.debug")
    m.addPackage( "${PN}-common-test-dev", "", "", "/common_test-*/src /common_test-*/include")
    m.addPackage( "${PN}-common-test-staticdev", "", "", "/common_test-*/lib/*.a /common_test-*/priv/lib/*.a")

    m.addPackage( "${PN}-compiler", "", "", "${bindir}/erlc ${libdir}/erlang/bin/erlc /compiler-* " + "${libdir}/erlang/erts-" + ERTS_VERSION + "/bin/erlc")
    m.addPackage( "${PN}-compiler-dbg", "", "", "/compiler-*/bin/.debug /compiler-*/lib/.debug /compiler-*/priv/lib/.debug /compiler-*/priv/obj/.debug /compiler-*/priv/bin/.debug")
    m.addPackage( "${PN}-compiler-dev", "", "", "/compiler-*/src /compiler-*/include")
    m.addPackage( "${PN}-compiler-staticdev", "", "", "/compiler-*/lib/*.a /compiler-*/priv/lib/*.a")

    m.addPackage( "${PN}-cosevent", "", "", "/cosEvent-*")
    m.addPackage( "${PN}-cosevent-dbg", "", "", "/cosEvent-*/bin/.debug /cosEvent-*/lib/.debug /cosEvent-*/priv/lib/.debug /cosEvent-*/priv/obj/.debug /cosEvent-*/priv/bin/.debug")
    m.addPackage( "${PN}-cosevent-dev", "", "", "/cosEvent-*/src /cosEvent-*/include")
    m.addPackage( "${PN}-cosevent-staticdev", "", "", "/cosEvent-*/lib/*.a /cosEvent-*/priv/lib/*.a")

    m.addPackage( "${PN}-coseventdomain", "", "", "/cosEventDomain-*")
    m.addPackage( "${PN}-coseventdomain-dbg", "", "", "/cosEventDomain-*/bin/.debug /cosEventDomain-*/lib/.debug /cosEventDomain-*/priv/lib/.debug /cosEventDomain-*/priv/obj/.debug /cosEventDomain-*/priv/bin/.debug")
    m.addPackage( "${PN}-coseventdomain-dev", "", "", "/cosEventDomain-*/src /cosEventDomain-*/include")
    m.addPackage( "${PN}-coseventdomain-staticdev", "", "", "/cosEventDomain-*/lib/*.a /cosEventDomain-*/priv/lib/*.a")

    m.addPackage( "${PN}-cosfiletransfer", "", "", "/cosFileTransfer-*")
    m.addPackage( "${PN}-cosfiletransfer-dbg", "", "", "/cosFileTransfer-*/bin/.debug /cosFileTransfer-*/lib/.debug /cosFileTransfer-*/priv/lib/.debug /cosFileTransfer-*/priv/obj/.debug /cosFileTransfer-*/priv/bin/.debug")
    m.addPackage( "${PN}-cosfiletransfer-dev", "", "", "/cosFileTransfer-*/src /cosFileTransfer-*/include")
    m.addPackage( "${PN}-cosfiletransfer-staticdev", "", "", "/cosFileTransfer-*/lib/*.a /cosFileTransfer-*/priv/lib/*.a")

    m.addPackage( "${PN}-cosnotification", "", "", "/cosNotification-*")
    m.addPackage( "${PN}-cosnotification-dbg", "", "", "/cosNotification-*/bin/.debug /cosNotification-*/lib/.debug /cosNotification-*/priv/lib/.debug /cosNotification-*/priv/obj/.debug /cosNotification-*/priv/bin/.debug")
    m.addPackage( "${PN}-cosnotification-dev", "", "", "/cosNotification-*/src /cosNotification-*/include")
    m.addPackage( "${PN}-cosnotification-staticdev", "", "", "/cosNotification-*/lib/*.a /cosNotification-*/priv/lib/*.a")

    m.addPackage( "${PN}-cosproperty", "", "", "/cosProperty-*")
    m.addPackage( "${PN}-cosproperty-dbg", "", "", "/cosProperty-*/bin/.debug /cosProperty-*/lib/.debug /cosProperty-*/priv/lib/.debug /cosProperty-*/priv/obj/.debug /cosProperty-*/priv/bin/.debug")
    m.addPackage( "${PN}-cosproperty-dev", "", "", "/cosProperty-*/src /cosProperty-*/include")
    m.addPackage( "${PN}-cosproperty-staticdev", "", "", "/cosProperty-*/lib/*.a /cosProperty-*/priv/lib/*.a")

    m.addPackage( "${PN}-costime", "", "", "/cosTime-*")
    m.addPackage( "${PN}-costime-dbg", "", "", "/cosTime-*/bin/.debug /cosTime-*/lib/.debug /cosTime-*/priv/lib/.debug /cosTime-*/priv/obj/.debug /cosTime-*/priv/bin/.debug")
    m.addPackage( "${PN}-costime-dev", "", "", "/cosTime-*/src /cosTime-*/include")
    m.addPackage( "${PN}-costime-staticdev", "", "", "/cosTime-*/lib/*.a /cosTime-*/priv/lib/*.a")

    m.addPackage( "${PN}-costransactions", "", "", "/cosTransactions-*")
    m.addPackage( "${PN}-costransactions-dbg", "", "", "/cosTransactions-*/bin/.debug /cosTransactions-*/lib/.debug /cosTransactions-*/priv/lib/.debug /cosTransactions-*/priv/obj/.debug /cosTransactions-*/priv/bin/.debug")
    m.addPackage( "${PN}-costransactions-dev", "", "", "/cosTransactions-*/src /cosTransactions-*/include")
    m.addPackage( "${PN}-costransactions-staticdev", "", "", "/cosTransactions-*/lib/*.a /cosTransactions-*/priv/lib/*.a")

    m.addPackage( "${PN}-crypto", "", "", "/crypto-*")
    m.addPackage( "${PN}-crypto-dbg", "", "", "/crypto-*/bin/.debug /crypto-*/lib/.debug /crypto-*/priv/lib/.debug /crypto-*/priv/obj/.debug /crypto-*/priv/bin/.debug")
    m.addPackage( "${PN}-crypto-dev", "", "", "/crypto-*/src /crypto-*/include")
    m.addPackage( "${PN}-crypto-staticdev", "", "", "/crypto-*/lib/*.a /crypto-*/priv/lib/*.a")

    m.addPackage( "${PN}-debugger", "", "", "/debugger-*")
    m.addPackage( "${PN}-debugger-dbg", "", "", "/debugger-*/bin/.debug /debugger-*/lib/.debug /debugger-*/priv/lib/.debug /debugger-*/priv/obj/.debug /debugger-*/priv/bin/.debug")
    m.addPackage( "${PN}-debugger-dev", "", "", "/debugger-*/src /debugger-*/include")
    m.addPackage( "${PN}-debugger-staticdev", "", "", "/debugger-*/lib/*.a /debugger-*/priv/lib/*.a")

    m.addPackage( "${PN}-dialyzer", "", "", "${bindir}/dialyzer ${libdir}/erlang/bin/dialyzer /dialyzer-* " + "${libdir}/erlang/erts-" + ERTS_VERSION + "/bin/dialyzer")
    m.addPackage( "${PN}-dialyzer-dbg", "", "", "/dialyzer-*/bin/.debug /dialyzer-*/lib/.debug /dialyzer-*/priv/lib/.debug /dialyzer-*/priv/obj/.debug /dialyzer-*/priv/bin/.debug")
    m.addPackage( "${PN}-dialyzer-dev", "", "", "/dialyzer-*/src /dialyzer-*/include")
    m.addPackage( "${PN}-dialyzer-staticdev", "", "", "/dialyzer-*/lib/*.a /dialyzer-*/priv/lib/*.a")

    m.addPackage( "${PN}-diameter", "", "", "/diameter-*")
    m.addPackage( "${PN}-diameter-dbg", "", "", "/diameter-*/bin/.debug /diameter-*/lib/.debug /diameter-*/priv/lib/.debug /diameter-*/priv/obj/.debug /diameter-*/priv/bin/.debug")
    m.addPackage( "${PN}-diameter-dev", "", "", "/diameter-*/src /diameter-*/include")
    m.addPackage( "${PN}-diameter-staticdev", "", "", "/diameter-*/lib/*.a /diameter-*/priv/lib/*.a")

    m.addPackage( "${PN}-edoc", "", "", "/edoc-*")
    m.addPackage( "${PN}-edoc-dbg", "", "", "/edoc-*/bin/.debug /edoc-*/lib/.debug /edoc-*/priv/lib/.debug /edoc-*/priv/obj/.debug /edoc-*/priv/bin/.debug")
    m.addPackage( "${PN}-edoc-dev", "", "", "/edoc-*/src /edoc-*/include")
    m.addPackage( "${PN}-edoc-staticdev", "", "", "/edoc-*/lib/*.a /edoc-*/priv/lib/*.a")

    m.addPackage( "${PN}-eldap", "", "", "/eldap-*")
    m.addPackage( "${PN}-eldap-dbg", "", "", "/eldap-*/bin/.debug /eldap-*/lib/.debug /eldap-*/priv/lib/.debug /eldap-*/priv/obj/.debug /eldap-*/priv/bin/.debug")
    m.addPackage( "${PN}-eldap-dev", "", "", "/eldap-*/src /eldap-*/include")
    m.addPackage( "${PN}-eldap-staticdev", "", "", "/eldap-*/lib/*.a /eldap-*/priv/lib/*.a")

    m.addPackage( "${PN}-erl-docgen", "", "", "/erl_docgen-*")
    m.addPackage( "${PN}-erl-docgen-dbg", "", "", "/erl_docgen-*/bin/.debug /erl_docgen-*/lib/.debug /erl_docgen-*/priv/lib/.debug /erl_docgen-*/priv/obj/.debug /erl_docgen-*/priv/bin/.debug")
    m.addPackage( "${PN}-erl-docgen-dev", "", "", "/erl_docgen-*/src /erl_docgen-*/include")
    m.addPackage( "${PN}-erl-docgen-staticdev", "", "", "/erl_docgen-*/lib/*.a /erl_docgen-*/priv/lib/*.a")

    m.addPackage( "${PN}-erl-interface", "", "", "/erl_interface-*")
    m.addPackage( "${PN}-erl-interface-dbg", "", "", "/erl_interface-*/bin/.debug /erl_interface-*/lib/.debug /erl_interface-*/priv/lib/.debug /erl_interface-*/priv/obj/.debug /erl_interface-*/priv/bin/.debug")
    m.addPackage( "${PN}-erl-interface-dev", "", "", "/erl_interface-*/src /erl_interface-*/include")
    m.addPackage( "${PN}-erl-interface-staticdev", "", "", "/erl_interface-*/lib/*.a /erl_interface-*/priv/lib/*.a")

    m.addPackage( "${PN}-erts", "", "", "${bindir} ${libdir}/erlang/releases ${libdir}/erlang/bin ${libdir}/erlang/erts-*/bin")
    m.addPackage( "${PN}-erts-dbg", "", "", "/erts-*/bin/.debug /erts-*/lib/.debug /erts-*/priv/lib/.debug /erts-*/priv/obj/.debug /erts-*/priv/bin/.debug")
    m.addPackage( "${PN}-erts-dev", "", "", "/erts-*/src /erts-*/include")
    m.addPackage( "${PN}-erts-staticdev", "", "", "/erts-*/lib/*.a /erts-*/priv/lib/*.a")

    m.addPackage( "${PN}-et", "", "", "/et-*")
    m.addPackage( "${PN}-et-dbg", "", "", "/et-*/bin/.debug /et-*/lib/.debug /et-*/priv/lib/.debug /et-*/priv/obj/.debug /et-*/priv/bin/.debug")
    m.addPackage( "${PN}-et-dev", "", "", "/et-*/src /et-*/include")
    m.addPackage( "${PN}-et-staticdev", "", "", "/et-*/lib/*.a /et-*/priv/lib/*.a")

    m.addPackage( "${PN}-eunit", "", "", "/eunit-*")
    m.addPackage( "${PN}-eunit-dbg", "", "", "/eunit-*/bin/.debug /eunit-*/lib/.debug /eunit-*/priv/lib/.debug /eunit-*/priv/obj/.debug /eunit-*/priv/bin/.debug")
    m.addPackage( "${PN}-eunit-dev", "", "", "/eunit-*/src /eunit-*/include")
    m.addPackage( "${PN}-eunit-staticdev", "", "", "/eunit-*/lib/*.a /eunit-*/priv/lib/*.a")

    m.addPackage( "${PN}-gs", "", "", "/gs-*")
    m.addPackage( "${PN}-gs-dbg", "", "", "/gs-*/bin/.debug /gs-*/lib/.debug /gs-*/priv/lib/.debug /gs-*/priv/obj/.debug /gs-*/priv/bin/.debug")
    m.addPackage( "${PN}-gs-dev", "", "", "/gs-*/src /gs-*/include")
    m.addPackage( "${PN}-gs-staticdev", "", "", "/gs-*/lib/*.a /gs-*/priv/lib/*.a")

    m.addPackage( "${PN}-hipe", "", "", "/hipe-*")
    m.addPackage( "${PN}-hipe-dbg", "", "", "/hipe-*/bin/.debug /hipe-*/lib/.debug /hipe-*/priv/lib/.debug /hipe-*/priv/obj/.debug /hipe-*/priv/bin/.debug")
    m.addPackage( "${PN}-hipe-dev", "", "", "/hipe-*/src /hipe-*/include")
    m.addPackage( "${PN}-hipe-staticdev", "", "", "/hipe-*/lib/*.a /hipe-*/priv/lib/*.a")

    m.addPackage( "${PN}-ic", "", "", "/ic-*")
    m.addPackage( "${PN}-ic-dbg", "", "", "/ic-*/bin/.debug /ic-*/lib/.debug /ic-*/priv/lib/.debug /ic-*/priv/obj/.debug /ic-*/priv/bin/.debug")
    m.addPackage( "${PN}-ic-dev", "", "", "/ic-*/src /ic-*/include")
    m.addPackage( "${PN}-ic-staticdev", "", "", "/ic-*/lib/*.a /ic-*/priv/lib/*.a")

    m.addPackage( "${PN}-inets", "", "", "/inets-*")
    m.addPackage( "${PN}-inets-dbg", "", "", "/inets-*/bin/.debug /inets-*/lib/.debug /inets-*/priv/lib/.debug /inets-*/priv/obj/.debug /inets-*/priv/bin/.debug")
    m.addPackage( "${PN}-inets-dev", "", "", "/inets-*/src /inets-*/include")
    m.addPackage( "${PN}-inets-staticdev", "", "", "/inets-*/lib/*.a /inets-*/priv/lib/*.a")

    m.addPackage( "${PN}-jinterface", "", "", "/jinterface-*")
    m.addPackage( "${PN}-jinterface-dbg", "", "", "/jinterface-*/bin/.debug /jinterface-*/lib/.debug /jinterface-*/priv/lib/.debug /jinterface-*/priv/obj/.debug /jinterface-*/priv/bin/.debug")
    m.addPackage( "${PN}-jinterface-dev", "", "", "/jinterface-*/src /jinterface-*/include")
    m.addPackage( "${PN}-jinterface-staticdev", "", "", "/jinterface-*/lib/*.a /jinterface-*/priv/lib/*.a")

    m.addPackage( "${PN}-megaco", "", "", "/megaco-*")
    m.addPackage( "${PN}-megaco-dbg", "", "", "/megaco-*/bin/.debug /megaco-*/lib/.debug /megaco-*/priv/lib/.debug /megaco-*/priv/obj/.debug /megaco-*/priv/bin/.debug")
    m.addPackage( "${PN}-megaco-dev", "", "", "/megaco-*/src /megaco-*/include")
    m.addPackage( "${PN}-megaco-staticdev", "", "", "/megaco-*/lib/*.a /megaco-*/priv/lib/*.a")

    m.addPackage( "${PN}-mnesia", "", "", "/mnesia-*")
    m.addPackage( "${PN}-mnesia-dbg", "", "", "/mnesia-*/bin/.debug /mnesia-*/lib/.debug /mnesia-*/priv/lib/.debug /mnesia-*/priv/obj/.debug /mnesia-*/priv/bin/.debug")
    m.addPackage( "${PN}-mnesia-dev", "", "", "/mnesia-*/src /mnesia-*/include")
    m.addPackage( "${PN}-mnesia-staticdev", "", "", "/mnesia-*/lib/*.a /mnesia-*/priv/lib/*.a")

    m.addPackage( "${PN}-observer", "", "", "/observer-*")
    m.addPackage( "${PN}-observer-dbg", "", "", "/observer-*/bin/.debug /observer-*/lib/.debug /observer-*/priv/lib/.debug /observer-*/priv/obj/.debug /observer-*/priv/bin/.debug")
    m.addPackage( "${PN}-observer-dev", "", "", "/observer-*/src /observer-*/include")
    m.addPackage( "${PN}-observer-staticdev", "", "", "/observer-*/lib/*.a /observer-*/priv/lib/*.a")

    m.addPackage( "${PN}-orber", "", "", "/orber-*")
    m.addPackage( "${PN}-orber-dbg", "", "", "/orber-*/bin/.debug /orber-*/lib/.debug /orber-*/priv/lib/.debug /orber-*/priv/obj/.debug /orber-*/priv/bin/.debug")
    m.addPackage( "${PN}-orber-dev", "", "", "/orber-*/src /orber-*/include")
    m.addPackage( "${PN}-orber-staticdev", "", "", "/orber-*/lib/*.a /orber-*/priv/lib/*.a")

    m.addPackage( "${PN}-os-mon", "", "", "/os_mon-*")
    m.addPackage( "${PN}-os-mon-dbg", "", "", "/os_mon-*/bin/.debug /os_mon-*/lib/.debug /os_mon-*/priv/lib/.debug /os_mon-*/priv/obj/.debug /os_mon-*/priv/bin/.debug")
    m.addPackage( "${PN}-os-mon-dev", "", "", "/os_mon-*/src /os_mon-*/include")
    m.addPackage( "${PN}-os-mon-staticdev", "", "", "/os_mon-*/lib/*.a /os_mon-*/priv/lib/*.a")

    m.addPackage( "${PN}-otp-mibs", "", "", "/otp_mibs-*")
    m.addPackage( "${PN}-otp-mibs-dbg", "", "", "/otp_mibs-*/bin/.debug /otp_mibs-*/lib/.debug /otp_mibs-*/priv/lib/.debug /otp_mibs-*/priv/obj/.debug /otp_mibs-*/priv/bin/.debug")
    m.addPackage( "${PN}-otp-mibs-dev", "", "", "/otp_mibs-*/src /otp_mibs-*/include")
    m.addPackage( "${PN}-otp-mibs-staticdev", "", "", "/otp_mibs-*/lib/*.a /otp_mibs-*/priv/lib/*.a")

    m.addPackage( "${PN}-parsetools", "", "", "/parsetools-*")
    m.addPackage( "${PN}-parsetools-dbg", "", "", "/parsetools-*/bin/.debug /parsetools-*/lib/.debug /parsetools-*/priv/lib/.debug /parsetools-*/priv/obj/.debug /parsetools-*/priv/bin/.debug")
    m.addPackage( "${PN}-parsetools-dev", "", "", "/parsetools-*/src /parsetools-*/include")
    m.addPackage( "${PN}-parsetools-staticdev", "", "", "/parsetools-*/lib/*.a /parsetools-*/priv/lib/*.a")

    m.addPackage( "${PN}-percept", "", "", "/percept-*")
    m.addPackage( "${PN}-percept-dbg", "", "", "/percept-*/bin/.debug /percept-*/lib/.debug /percept-*/priv/lib/.debug /percept-*/priv/obj/.debug /percept-*/priv/bin/.debug")
    m.addPackage( "${PN}-percept-dev", "", "", "/percept-*/src /percept-*/include")
    m.addPackage( "${PN}-percept-staticdev", "", "", "/percept-*/lib/*.a /percept-*/priv/lib/*.a")

    m.addPackage( "${PN}-pman", "", "", "/pman-*")
    m.addPackage( "${PN}-pman-dbg", "", "", "/pman-*/bin/.debug /pman-*/lib/.debug /pman-*/priv/lib/.debug /pman-*/priv/obj/.debug /pman-*/priv/bin/.debug")
    m.addPackage( "${PN}-pman-dev", "", "", "/pman-*/src /pman-*/include")
    m.addPackage( "${PN}-pman-staticdev", "", "", "/pman-*/lib/*.a /pman-*/priv/lib/*.a")

    m.addPackage( "${PN}-public-key", "", "", "/public_key-*")
    m.addPackage( "${PN}-public-key-dbg", "", "", "/public_key-*/bin/.debug /public_key-*/lib/.debug /public_key-*/priv/lib/.debug /public_key-*/priv/obj/.debug /public_key-*/priv/bin/.debug")
    m.addPackage( "${PN}-public-key-dev", "", "", "/public_key-*/src /public_key-*/include")
    m.addPackage( "${PN}-public-key-staticdev", "", "", "/public_key-*/lib/*.a /public_key-*/priv/lib/*.a")

    m.addPackage( "${PN}-reltool", "", "", "/reltool-*")
    m.addPackage( "${PN}-reltool-dbg", "", "", "/reltool-*/bin/.debug /reltool-*/lib/.debug /reltool-*/priv/lib/.debug /reltool-*/priv/obj/.debug /reltool-*/priv/bin/.debug")
    m.addPackage( "${PN}-reltool-dev", "", "", "/reltool-*/src /reltool-*/include")
    m.addPackage( "${PN}-reltool-staticdev", "", "", "/reltool-*/lib/*.a /reltool-*/priv/lib/*.a")

    m.addPackage( "${PN}-runtime-tools", "", "", "/runtime_tools-*")
    m.addPackage( "${PN}-runtime-tools-dbg", "", "", "/runtime_tools-*/bin/.debug /runtime_tools-*/lib/.debug /runtime_tools-*/priv/lib/.debug /runtime_tools-*/priv/obj/.debug /runtime_tools-*/priv/bin/.debug")
    m.addPackage( "${PN}-runtime-tools-dev", "", "", "/runtime_tools-*/src /runtime_tools-*/include")
    m.addPackage( "${PN}-runtime-tools-staticdev", "", "", "/runtime_tools-*/lib/*.a /runtime_tools-*/priv/lib/*.a")

    m.addPackage( "${PN}-snmp", "", "", "/snmp-*")
    m.addPackage( "${PN}-snmp-dbg", "", "", "/snmp-*/bin/.debug /snmp-*/lib/.debug /snmp-*/priv/lib/.debug /snmp-*/priv/obj/.debug /snmp-*/priv/bin/.debug")
    m.addPackage( "${PN}-snmp-dev", "", "", "/snmp-*/src /snmp-*/include")
    m.addPackage( "${PN}-snmp-staticdev", "", "", "/snmp-*/lib/*.a /snmp-*/priv/lib/*.a")

    m.addPackage( "${PN}-ssh", "", "", "/ssh-*")
    m.addPackage( "${PN}-ssh-dbg", "", "", "/ssh-*/bin/.debug /ssh-*/lib/.debug /ssh-*/priv/lib/.debug /ssh-*/priv/obj/.debug /ssh-*/priv/bin/.debug")
    m.addPackage( "${PN}-ssh-dev", "", "", "/ssh-*/src /ssh-*/include")
    m.addPackage( "${PN}-ssh-staticdev", "", "", "/ssh-*/lib/*.a /ssh-*/priv/lib/*.a")

    m.addPackage( "${PN}-ssl", "", "", "/ssl-*")
    m.addPackage( "${PN}-ssl-dbg", "", "", "/ssl-*/bin/.debug /ssl-*/lib/.debug /ssl-*/priv/lib/.debug /ssl-*/priv/obj/.debug /ssl-*/priv/bin/.debug")
    m.addPackage( "${PN}-ssl-dev", "", "", "/ssl-*/src /ssl-*/include")
    m.addPackage( "${PN}-ssl-staticdev", "", "", "/ssl-*/lib/*.a /ssl-*/priv/lib/*.a")

    m.addPackage( "${PN}-syntax-tools", "", "", "/syntax_tools-*")
    m.addPackage( "${PN}-syntax-tools-dbg", "", "", "/syntax_tools-*/bin/.debug /syntax_tools-*/lib/.debug /syntax_tools-*/priv/lib/.debug /syntax_tools-*/priv/obj/.debug /syntax_tools-*/priv/bin/.debug")
    m.addPackage( "${PN}-syntax-tools-dev", "", "", "/syntax_tools-*/src /syntax_tools-*/include")
    m.addPackage( "${PN}-syntax-tools-staticdev", "", "", "/syntax_tools-*/lib/*.a /syntax_tools-*/priv/lib/*.a")

    m.addPackage( "${PN}-test-server", "", "", "/test_server-*")
    m.addPackage( "${PN}-test-server-dbg", "", "", "/test_server-*/bin/.debug /test_server-*/lib/.debug /test_server-*/priv/lib/.debug /test_server-*/priv/obj/.debug /test_server-*/priv/bin/.debug")
    m.addPackage( "${PN}-test-server-dev", "", "", "/test_server-*/src /test_server-*/include")
    m.addPackage( "${PN}-test-server-staticdev", "", "", "/test_server-*/lib/*.a /test_server-*/priv/lib/*.a")

    m.addPackage( "${PN}-toolbar", "", "", "/toolbar-*")
    m.addPackage( "${PN}-toolbar-dbg", "", "", "/toolbar-*/bin/.debug /toolbar-*/lib/.debug /toolbar-*/priv/lib/.debug /toolbar-*/priv/obj/.debug /toolbar-*/priv/bin/.debug")
    m.addPackage( "${PN}-toolbar-dev", "", "", "/toolbar-*/src /toolbar-*/include")
    m.addPackage( "${PN}-toolbar-staticdev", "", "", "/toolbar-*/lib/*.a /toolbar-*/priv/lib/*.a")

    m.addPackage( "${PN}-tv", "", "", "/tv-*")
    m.addPackage( "${PN}-tv-dbg", "", "", "/tv-*/bin/.debug /tv-*/lib/.debug /tv-*/priv/lib/.debug /tv-*/priv/obj/.debug /tv-*/priv/bin/.debug")
    m.addPackage( "${PN}-tv-dev", "", "", "/tv-*/src /tv-*/include")
    m.addPackage( "${PN}-tv-staticdev", "", "", "/tv-*/lib/*.a /tv-*/priv/lib/*.a")

    m.addPackage( "${PN}-typer", "", "", "${bindir}/typer ${libdir}/erlang/bin/typer /typer-* " + "${libdir}/erlang/erts-" + ERTS_VERSION + "/bin/typer")
    m.addPackage( "${PN}-typer-dbg", "", "", "/typer-*/bin/.debug /typer-*/lib/.debug /typer-*/priv/lib/.debug /typer-*/priv/obj/.debug /typer-*/priv/bin/.debug")
    m.addPackage( "${PN}-typer-dev", "", "", "/typer-*/src /typer-*/include")
    m.addPackage( "${PN}-typer-staticdev", "", "", "/typer-*/lib/*.a /typer-*/priv/lib/*.a")

    m.addPackage( "${PN}-webtool", "", "", "/webtool-*")
    m.addPackage( "${PN}-webtool-dbg", "", "", "/webtool-*/bin/.debug /webtool-*/lib/.debug /webtool-*/priv/lib/.debug /webtool-*/priv/obj/.debug /webtool-*/priv/bin/.debug")
    m.addPackage( "${PN}-webtool-dev", "", "", "/webtool-*/src /webtool-*/include")
    m.addPackage( "${PN}-webtool-staticdev", "", "", "/webtool-*/lib/*.a /webtool-*/priv/lib/*.a")

    m.addPackage( "${PN}-xmerl", "", "", "/xmerl-*")
    m.addPackage( "${PN}-xmerl-dbg", "", "", "/xmerl-*/bin/.debug /xmerl-*/lib/.debug /xmerl-*/priv/lib/.debug /xmerl-*/priv/obj/.debug /xmerl-*/priv/bin/.debug")
    m.addPackage( "${PN}-xmerl-dev", "", "", "/xmerl-*/src /xmerl-*/include")
    m.addPackage( "${PN}-xmerl-staticdev", "", "", "/xmerl-*/lib/*.a /xmerl-*/priv/lib/*.a")

    m.addPackage( "${PN}-odbc", "", "", "/odbc-*")
    m.addPackage( "${PN}-odbc-dbg", "", "", "/odbc-*/bin/.debug /odbc*/lib/.debug /odbc-*/priv/lib/.debug /odbc-*/priv/obj/.debug /odbc-*/priv/bin/.debug")
    m.addPackage( "${PN}-odbc-dev", "", "", "/odbc-*/src /odbc-*/include")
    m.addPackage( "${PN}-odbc-staticdev", "", "", "/odbc-*/lib/*.a /odbc-*/priv/lib/*.a")

    m.addPackage( "${PN}-ose", "", "", "/ose-*")
    m.addPackage( "${PN}-ose-dbg", "", "", "/ose-*/bin/.debug /ose-*/lib/.debug /ose-*/priv/lib/.debug /ose-*/priv/obj/.debug /ose-*/priv/bin/.debug")
    m.addPackage( "${PN}-ose-dev", "", "", "/ose-*/src /ose-*/include")
    m.addPackage( "${PN}-ose-staticdev", "", "", "/ose-*/lib/*.a /ose-*/priv/lib/*.a")

    m.addPackage( "${PN}-doc", "", "", " ${libdir}/erlang/erts-*/doc ${libdir}/erlang/erts-*/man ${libdir}/erlang/lib/*/examples ${libdir}/erlang/misc", True)
    m.addPackage( "${PN}-dev", "", "", " ${libdir}/erlang/erts-*/include ${libdir}/erlang/erts-*/src ${libdir}/erlang/usr/include", True)
    m.addPackage( "${PN}-dbg", "", "", " ${libdir}/erlang/bin/.debug ${libdir}/erlang/erts-*/bin/.debug", True)
    m.addPackage( "${PN}-staticdev", "", "", "  ${libdir}/erlang/usr/lib/*.a ${libdir}/erlang/usr/lib/internal/*.a ${libdir}/erlang/erts-*/lib/*.a ${libdir}/erlang/erts-*/lib/internal/*", True)
    m.addPackage( "${PN}", "", "${PN}-erts ${PN}-kernel ${PN}-stdlib ${PN}-sasl", "", True)

    m.make()
