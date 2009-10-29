/*******************************************************************************
 * Copyright (c) 2009 SOPERA GmbH
 * All rights reserved. 
 * This program and the accompanying materials are made available
 * under the terms of the GNU Lesser General Public License v 3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package org.sopera.di.smooks.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.milyn.Smooks;
import org.sopera.di.smooks.SmooksTransform;

public class SmooksTransformImpl implements SmooksTransform {

	private String inputFileName;
	private String outputFileName;
	private String configFileName;
	private HashMap<String, String> mappingResource = new HashMap<String, String>();
	private HashMap<String, String> mappingResourceInverted = new HashMap<String, String>();

	public String getConfigFileName() {
		return configFileName;
	}

	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
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
		Smooks smooks = null;
		try {
			// Instantiate Smooks with the config...
			fillMappingFile(mappingResource);
			smooks = new Smooks(getConfigFileName());

			// Create an exec context - no profiles....
			org.milyn.container.ExecutionContext executionContext = smooks
					.createExecutionContext();
			javax.xml.transform.stream.StreamResult streamResult = new javax.xml.transform.stream.StreamResult();
			streamResult.setOutputStream(new java.io.FileOutputStream(
					getOutputFileName()));

			// Filter the input message to the outputWriter, using the execution
			// context...
			smooks.filterSource(executionContext,
					new javax.xml.transform.stream.StreamSource(
							new java.io.FileInputStream(getInputFileName())),
					streamResult);
			return true;
		} finally {
			if (smooks != null)
				smooks.close();
			fillMappingFile(mappingResourceInverted);
		}
	}

	public void setMappingResource(String targetID, String sourseName) {
		mappingResource.put(targetID, sourseName);
		mappingResourceInverted.put(sourseName, targetID);
	}

	protected void fillMappingFile(HashMap<String, String> mappingResource) {
		FileReader configReader;
		StringBuilder file = new StringBuilder();
		FileWriter mappingWriter;
		try {
			configReader = new FileReader(getConfigFileName());
			BufferedReader br = new BufferedReader(configReader);
			String s;

			while ((s = br.readLine()) != null) {
				for (Iterator<String> iterator = (mappingResource.keySet())
						.iterator(); iterator.hasNext();) {
					String type = (String) iterator.next();
					s = s.replaceAll(type, mappingResource.get(type));
				}
				file.append(s).append("\r\n");
			}
			configReader.close();
			mappingWriter = new FileWriter(getConfigFileName());
			mappingWriter.append(file.toString());
			mappingWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Can't find config file.");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("IO exception has taken place.");
		}

	}
}