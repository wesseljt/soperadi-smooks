

# Introduction #

**TSmooks** is a component, which provide [Smooks](http://smooks.org) capabilities into [Talend](http://talend.com). You can find different possible use cases of **tSmooks** component on this page.


# Details #

## Required libraries ##

You need these libraries for all use cases:

  1. aopalliance.jar
  1. commons-logging-1.1.jar
  1. guice-2.0.jar
  1. jaxen-1.1.1.jar
  1. milyn-commons-1.2.2.jar
  1. milyn-smooks-core-1.2.2.jar


## EDI-to-XML ##

#### Description ####

We can use **tSmooks** to perform data transformation from EDI-message format to XML.

#### Required libraries ####

  1. milyn-edisax-parser-1.2.2.jar
  1. milyn-smooks-edi-1.2.2.jar


## CSV-to-XML ##

#### Description ####

We can use **tSmooks** to perform data transformation from CSV-message format to XML.

#### Required libraries ####

  1. milyn-smooks-csv-1.2.2.jar
  1. opencsv-1.8.jar


## XML-to-XML ##

You can perform XML-to-XML transformation with help of different technologies. To use them you will need some external libraries.

### Using of Freemarker ###

#### Description ####

We can use **Freemarker** to perform XML-to-XML transformation.

#### Required libraries ####

  1. freemarker-2.3.11.jar
  1. milyn-smooks-javabean-1.2.2.jar
  1. milyn-smooks-templating-1.2.2.jar

### Using of XSLT ###

#### Description ####

We can use **XSLT** to perform XML-to-XML transformation.

#### Required libraries ####

  1. milyn-smooks-templating-1.2.2.jar
  1. milyn-smooks-javabean-1.2.2.jar


### Using of Groovy ###

#### Description ####

We can use **Groovy** scripts to perform XML-to-XML transformation.

#### Required libraries ####

  1. groovy-all-1.5.0.jar
  1. milyn-smooks-scripting-1.2.2.jar
  1. milyn-smooks-javabean-1.2.2.jar
  1. milyn-smooks-templating-1.2.2.jar



You can find more information about tSmooks component in [SmooksComponents.pdf](http://soperadi-smooks.googlecode.com/files/SmooksComponents.pdf)