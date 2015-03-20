<font size='4'>
<br>
</font>

# Introduction #

You can find some examples of how tSmooks component works on this page.

# Details #

## Scenario 1:  EDI-to-XML transformation ##

### Description ###

In the beginning we created in Talend a simple Java job using only **tSmooks** component. The component implements the Smooks data EDI-to-XML transformation.
Then we complicated the scenario and used standard Talend component to provide data into the Talend flow.
**TSmooks** component performs data conversion and stores the result in the file.  Then the resulting file name it saves in a Talend Job global Map. Next job component uses file name as parameter.


<a href='http://www.youtube.com/watch?feature=player_embedded&v=7hJBUUPRz3I' target='_blank'><img src='http://img.youtube.com/vi/7hJBUUPRz3I/0.jpg' width='956' height=600 /></a>
<br>
<br>

<h2>Scenario 2: Interaction with the standard components. Complex Iterate-EDI-to-XML-to-XML transform.</h2>

<h3>Description</h3>

This scenario demonstrates interaction  tSmooks with standard Talend components by triggered and Iterate connectors. We use two <b>tSmooks</b> components in this Job, the first for the EDI-to-XML processing, the second for XML-to-XML transformation. The resulting data we provide into Talend flow and display it on the Log.<br>
<br>
<a href='http://www.youtube.com/watch?feature=player_embedded&v=iqV1yfqPx-M' target='_blank'><img src='http://img.youtube.com/vi/iqV1yfqPx-M/0.jpg' width='956' height=600 /></a><br>
<br>
<br>