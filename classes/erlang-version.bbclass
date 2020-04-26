
# Determine the staged version of an specific erlang application from release files
def get_erlang_application(d, application):
    import re

    start_erl = d.expand('${STAGING_LIBDIR}/erlang/releases/start_erl.data')
    try:
        f = open(start_erl, 'r')
    except IOError:
        return None
    l = f.readlines();
    f.close();
    r = re.compile(r"^(.*) (.*)")
    for s in l:
        m = r.match(s)
        if m:
            major_release = m.group(2)

    if not major_release:
        return None

    installed_application_versions = \
        d.expand('${STAGING_LIBDIR}/erlang/releases/%s/installed_application_versions' % major_release)
    try:
        f = open(installed_application_versions, 'r')
    except IOError:
        return None
    l = f.readlines();
    f.close();
    r = re.compile(r"^%s-(.*)" % application)
    for s in l:
        m = r.match(s)
        if m:
            return m.group(0)

    return None