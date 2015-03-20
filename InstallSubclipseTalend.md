# Introduction #

Subclipse is a SVN graphic user interface (GUI) for Eclipse Platform. This page describes how to install Subclipse into Talend Open Studio (TOS 1.3.1).


# Details #

## Requirements ##

**1.** Eclipse Platform (version 3.4.2+).

**2.** Talend Open Studio (TOS 1.3.1).

**3.** Internet connection.


## Sequence of actions to install Subclipse into TOS 1.3.1 ##

**1.** Open your Eclipse.

http://soperadi-smooks.googlecode.com/svn/wiki/screenshoots/subclipse1.JPG

**2.** Choose **Available software** tab and click **Add site...** button.

http://soperadi-smooks.googlecode.com/svn/wiki/screenshoots/subclipse2.JPG

**3.** Enter location and click **Ok**.

http://soperadi-smooks.googlecode.com/svn/wiki/screenshoots/subclipse3.JPG

**4.** Select the following items and click **Install** button.

http://soperadi-smooks.googlecode.com/svn/wiki/screenshoots/subclipse4.JPG

**5.** Click **Next**.

http://soperadi-smooks.googlecode.com/svn/wiki/screenshoots/subclipse5.JPG

**6.** Accept license and click **Finish** button.

http://soperadi-smooks.googlecode.com/svn/wiki/screenshoots/subclipse6.JPG

**7.** Exit Eclipse Platform.

**8.** Open your eclipse directory and then **plugins** directory in it. From this folder copy next files to your **talend/plugins** directory:

  * org.tigris.subversion.subclipse.ui\_1.6.5.jar

  * org.tigris.subversion.subclipse.doc\_1.3.0.jar

  * org.tigris.subversion.subclipse.mylyn\_3.0.0.jar

  * org.tigris.subversion.subclipse.core\_1.6.5.jar

  * org.tigris.subversion.subclipse.graph\_1.0.7.jar

  * org.tigris.subversion.clientadapter.svnkit\_1.6.4.jar

  * com.collabnet.subversion.merge\_1.10.0.jar

  * org.tigris.subversion.clientadapter.javahl\_1.6.5.jar

  * org.tigris.subversion.clientadapter\_1.6.4.1.jar

  * org.tmatesoft.svnkit\_1.3.0.5847.jar

  * com.sun.jna\_3.0.9.jar

Also copy directory **org.tigris.subversion.clientadapter.javahl.win32\_1.6.5**.

_You can also copy these files from this place **http://soperadi-smooks.googlecode.com/files/plugins.rar**_
## Alternative variant of installing Subclipse ##

You can also try to install Subclipse as it described at **http://code.google.com/p/gadzlend/wiki/SubclipseInTalend**

But this way was available with earlier versions of TOS (TOS 2.40). And in the next versions of TOS this method of installing leads to an error.