package org.sopera.di.smooks.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.sopera.di.smooks.SmooksTransform;

public class SmooksTransformImpl implements SmooksTransform{

	private String inputFileName;
	private String outputFileName;
	private String mappingFileName;
	private HashMap<String, String> mappingResource = new HashMap<String, String>();

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

		//setMappingResource(targetID, sourseName);
		// Instantiate Smooks with the config...
		fillMappingFile();
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
		mappingResource.put(targetID, sourseName);
	}
	
	protected void fillMappingFile() {
		FileReader mappingReader;
		try {
			mappingReader = new FileReader(getMappingFileName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Can't find mapping file");
		}
		String file;
		file = mappingReader.toString();
		for (Iterator<String> iterator = (mappingResource.keySet()).iterator(); iterator.hasNext();) {
			String type = (String) iterator.next();
			file.replaceAll(type, mappingResource.get(type));
		}
		FileWriter mappingWriter;
		try {
			mappingWriter = new FileWriter(getMappingFileName());
			mappingWriter.write(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}