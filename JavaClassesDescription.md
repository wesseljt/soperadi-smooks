# Introduction #

This page describes Java classes, which are needed for the iterative reading data from the stream of SAX events. On this page you can find out how these classes work together.


# Details #

**1.** Create an instance of **<font size='4'><code>ComponentFacade</code></font>**_._<font size='4'><code>ComponentFacade</code></font>__- interface, which provides_Smooks-functionality_for_Talend-component**. This interface will be a primary facade that will be used by Talend-Smooks jobs at runtime.**

```
org.sopera.di.smooks.ComponentFacade inputFlow = org.sopera.di.smooks.ComponentFacade.INSTANCE;
```_

**2.** Then we set _path_ for tag (which is necessary for reading the needed data) with help of method _**setXPath()**_. Method _**setXPath()**_ have a string parameter with the path to the tag, which we need. This parameter will be a key in the **<font size='4'><code>HashMap</code><></font>**_and will point to the_**SAXLocation**_(which indicates the position in the document)._

```
inputFlow.setXPath("/Order/header");
inputFlow.setXPath("/Order/customer-details");
inputFlow.setXPath("/Order/order-item");
inputFlow.setXPath("/Order/order-item");
```

If the string that we passed as a parameter is already present in the **<font size='4'><code>HashMap</code><></font>**_as a key, then we just finish the work of our function. Otherwise, we check that the string was not equal to null and divide it into parts. Then we form_**SAXLocation**_with the help of these parts. We put in compliance_**loopPath**_and_**SAXLocation**_and puts them into a_<font size='4'><code>HashMap</code><></font>_**.**

**3.** With the help of methods_**setMapping()**_and_**setEDI()**_we set the stream of mapping file and the stream of_EDI-data_file._

```
inputFlow.setMapping(getClass().getResourceAsStream(
				"/smooks-mapping.xml"));
inputFlow.setEDI(getClass().getResourceAsStream(
				"/smooks.edi"));
```

**4.** Then we call the method _**start()**_.

```
inputFlow.start();
```

In this method we call the methods of _**EDIProcess**_ (basic interface for organize the thread, with uses the smooks to transform the data from _EDI-massage_ to the _SAX-events_ flow) _**setMapping()**_ to set the mapping, _**setRes()**_ to set the **<font size='4'><code>StringTags</code></font>**_(contains_<font size='4'><code>HashMap</code><></font>**_with the names of tags and corresponding to these names_**SAXLocations)**_,_**setXPaths()**_to set the_XPath_,_**setEdi()__to set the stream of_EDI-message**file. Then we run the writing thread.**

```
this.parser.setMapping(mapping);
this.parser.setRes(res);
this.parser.setXPaths(xPaths);
this.parser.setEdi(EDI);

writer = new Thread(parser);
writer.start();
```_

**5.** Invoke method _**startRead()**_ from **<font size='4'><code>StringTags</code></font>**_interface._

```
inputFlow.startRead();
```

This method checks the boolean variable _**state**_, which indicates whether we have to read the data structure (in this case _**state = true**_) or to write to it (_**state = false**_). If we need to write into a data structure, then we'll stop the thread of reading.

```
if (!state) {
    try {
        wait();
   } catch (InterruptedException e) {}
```

**6.** In the thread, which is responsible for recording of the data, we call the method _**parse()**_.

```
parser.parse(new InputSource(edi));
```

This method starts parsing our _EDI-file_. If we find element  in the document, which corresponds to one of the given _XPath_, we write it to our data structure
with the help of method _**write()**_ of interface **<font size='4'><code>StringTags</code></font>**_._

```
res.write();
```

After we recorded the necessary data we suspend the writing thread and resume the thread of reading.

```
res.endWrite();
```

**7.** In the thread of reading we start the loop of data transmission. After reading the data we stop the flow of reading again and start the thread of writing and we do so until the end of _EDI-file_ is reached. The loop will continue until the state of our data structure will equal **end**. This state indicates whether the end of the stream is reached. We check this state using the method _**isEnd()**_ from interface **<font size='4'><code>StringTags</code></font>**_, and set it using the method_**setEnd()**_._

```
while (!inputFlow.isEndOfFlow()) { 
   inputFlow.next();
```

After that we finish the thread of writing and the thread of reading.