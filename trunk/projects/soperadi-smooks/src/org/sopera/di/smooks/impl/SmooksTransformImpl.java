package org.sopera.di.smooks.impl;

import org.sopera.di.smooks.SmooksTransform;

public class SmooksTransformImpl implements SmooksTransform{

	private String inputFileName;
	private String outputFileName;
	private String mappingFileName;

	public String getMappingFileName() {
		return mappingFileName;
	}

	public void setMappingFileName(String mappingFileName) {
		this.mappingFileName = mappingFileName;
	}

	public String getInputFileName() {
		return inputFileName;
	}

	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public boolean runSmooksTransform() throws java.io.IOException,
			org.xml.sax.SAXException, org.milyn.SmooksException {

		// Instantiate Smooks with the config...
		org.milyn.Smooks smooks = new org.milyn.Smooks(getMappingFileName());

		try {
			// Create an exec context - no profiles....
			org.milyn.container.ExecutionContext executionContext = smooks
					.createExecutionContext();
			javax.xml.transform.stream.StreamResult streamResult = new javax.xml.transform.stream.StreamResult();
			streamResult.setOutputStream(new java.io.FileOutputStream(
					getOutputFileName()));

			// Configure the execution context to generate a report...
			// executionContext
			// .setEventListener(new org.milyn.event.report.HtmlReportGenerator(
			// "/.././resources/report.html"));

			// Filter the input message to the outputWriter, using the execution
			// context...
			smooks.filterSource(executionContext,
					new javax.xml.transform.stream.StreamSource(
							new java.io.FileInputStream(getInputFileName())),
					streamResult);

			return true;
		} finally {
			smooks.close();
		}
	}

	public void setMappingResource(String targetID, String sourseName) {
		// TODO Auto-generated method stub
		
	}
}