# Introduction #
This page describes how to establish a development environment where you can checkout/develop/run and checkin SOPERA-DI Smooks components.


# Details #

### Deploying a Soperadi-smooks components with _SVN_ ###

**1.**Specify a “User components folder” in TOS preferences (preferences/Talend/Components).

**2.**Use this command in the user components folder
_**svn checkout http://soperadi-smooks.googlecode.com/svn/trunk/components**_
to anonymously check out the latest components.

**3.**Restart your Talend Open Studio application.

**4.**After launch, you should see soperadi-smooks components in folder “components/user” in org.talend.designer.components.localprovider plugin.

**5.**Soperadi-smooks components should also appear in the Palette in the job designer (in a family folder Smooks).

### Deploying a Soperadi-smooks components with _Talend Exchange_ ###

You can also deploy Soperadi-smooks components with help of  [Talend Exchange](http://code.google.com/p/soperadi-smooks/wiki/DownloadComponentTalendExchange?ts=1250675073&updated=DownloadComponentTalendExchange).