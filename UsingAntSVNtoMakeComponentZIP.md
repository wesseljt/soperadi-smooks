# Introduction #

This page describes how to make ZIP-archive of **tSmooksInput** component from SVN-repository with help of [Ant](http://ant.apache.org/bindownload.cgi). You need to do next steps and you will have the latest version of **tSmooksInput** in ZIP-archive named **"tSmooksInput.zip"**


# Details #

## Requirements ##

  1. [Java SE Development Kit 6](http://java.sun.com/javase/downloads/index.jsp)
  1. [Ant](http://ant.apache.org/bindownload.cgi) version 1.7.1.
  1. [Subversion](http://subversion.tigris.org) client compatible with your platform (version 1.6.5).
  1. [SvnAnt](http://subclipse.tigris.org/svnant.html) version 1.3.0.


## [Ant](http://ant.apache.org/bindownload.cgi) installing ##

  1. Download [Ant](http://ant.apache.org/bindownload.cgi).
  1. You need only **bin** and **lib** directories. Copy them to directory you need.
  1. Add the **bin** directory to your path.
  1. Set the **ANT\_HOME** environment variable to the directory where you installed [Ant](http://ant.apache.org/bindownload.cgi).
  1. Set the **CLASSPATH** environment variable to the directory where **lib** directory is present.


## [Subversion](http://subversion.tigris.org) client installing ##

  1. Download [Subversion](http://subversion.tigris.org) client, which compatible with your platform.
  1. Install [Subversion](http://subversion.tigris.org) with help of it documentation (if you need, of course).
  1. Add the **bin** directory to your **path**.


## [SvnAnt](http://subclipse.tigris.org/svnant.html) installing ##

  1. Download [SvnAnt](http://subclipse.tigris.org/svnant.html).
  1. Unzip archive to the directory you need.
  1. Copy jar-files from the **lib** directory to the **%ANT\_HOME%/lib**.


## Making component ZIP-archive with [Ant](http://ant.apache.org/bindownload.cgi) ##

  1. Create directory, where you want to make component archive.
  1. Copy **tSmooksInputBuild.xml** file to this directory, if you want to build **tSmooksInput** component, or **tSmooksBuild.xml** to build **tSmooks** component.
  1. Open command line in this directory and type "ant -f tSmooksInputBuild.xml" for **tSmooksInput**, or type "ant -f tSmooksBuild.xml" for **tSmooks** component.

If you did all steps correctly, than you would have zip-file with needed component in your directory.