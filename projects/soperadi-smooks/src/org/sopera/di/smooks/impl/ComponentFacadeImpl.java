/*******************************************************************************
 * Copyright (c) 2009 SOPERA GmbH
 * All rights reserved. 
 * This program and the accompanying materials are made available
 * under the terms of the GNU Lesser General Public License v 3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/

package org.sopera.di.smooks.impl;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;
import org.sopera.di.smooks.ComponentFacade;
import org.sopera.di.smooks.EDIProcess;
import org.sopera.di.smooks.EDIProcessModule;
import org.sopera.di.smooks.StringTags;
import org.sopera.di.smooks.xpath.SAXLocation;
import org.sopera.di.smooks.xpath.impl.SAXLocationImpl;

import com.google.inject.Guice;
import com.google.inject.Inject;

import javax.xml.namespace.QName;
import java.util.HashMap;

/**
 * This class organize the read sessions loop for StringTags structure.
 * Implementation of the {@link ComponentFacade}
 * 
 * @author soperadi-smooks
 */
public class ComponentFacadeImpl implements ComponentFacade {

	private Thread writer;
	private EDIProcess parser = EDIProcess.INSTANCE; // Parser
	@Inject
	private StringTags res;
	@Inject
	private SAXLocation loc;
	private InputStream EDI; // EDI-massage stream
	private InputStream mapping; // Mapping stream

	private HashMap<String, SAXLocation> xPaths = new HashMap<String, SAXLocation>();

	/**
	 * 
	 * @see org.sopera.di.smooks.ComponentFacade#setEDI(java.io.InputStream)
	 */

	public void setEDI(InputStream edi) {
		EDI = edi;
	}

	/**
	 * 
	 * @see org.sopera.di.smooks.ComponentFacade#setMapping(java.io.InputStream)
	 */
	public void setMapping(InputStream mapping) {
		this.mapping = mapping;
	}

	/**
	 * 
	 * @see org.sopera.di.smooks.ComponentFacade#setXPath(java.lang.String)
	 */
	public void setXPath(String loopPath) {
		if (xPaths.get(loopPath) == null) {
			String[] paramNames = null;
			SAXLocation loc = Guice.createInjector(
					new EDIProcessModule()).getInstance(SAXLocation.class); 
			if (loopPath != null) {
				paramNames = loopPath.split("/");
			}
			if (paramNames != null) {
				for (int i = 0; i < paramNames.length; i++) {
					if (paramNames[i] != null && !("").equals(paramNames[i])) {
						loc.startElement(new QName("", paramNames[i]));
						System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
						System.out.println(loc);
					}
				}
				xPaths.put(loopPath, loc);
			}

		}
		return;
	}

	/**
	 * 
	 * @see org.sopera.di.smooks.ComponentFacade#isEndOfFlow()
	 */
	public boolean isEndOfFlow() {
		return res.isEnd();// writer == null || !writer.isAlive();
	}

	/**
	 * 
	 * @see org.sopera.di.smooks.ComponentFacade#findTagValue(java.lang.String)
	 */
	public String findTagValue(String tagName) {
		return res.findTagValue(tagName);
	}

	/**
	 * 
	 * @see org.sopera.di.smooks.ComponentFacade#endRead()
	 */
	public void endRead() {
		res.endRead();
		return;
	}

	/**
	 * 
	 * @see org.sopera.di.smooks.ComponentFacade#startRead()
	 */
	public void startRead() {
		res.startRead();
		return;
	}

	/**
	 * 
	 * @see org.sopera.di.smooks.ComponentFacade#next()
	 */
	public boolean next() {

		res.endRead();
		res.startRead();
		if (res.isEnd())
			return false;
		else
			return true;
	}

	/**
	 * 
	 * @see org.sopera.di.smooks.ComponentFacade#start()
	 */
	public void start() {

		this.parser.setMapping(mapping);
		this.parser.setRes(res);
		this.parser.setXPaths(xPaths);
		this.parser.setEdi(EDI);

		writer = new Thread(parser);
		writer.start();
	}

	/**
	 * 
	 * @see org.sopera.di.smooks.ComponentFacade#getXPath()
	 */
	public String getXPath() {
		return parser.getXPath();
	}

	@Test
	public void Test() {
		org.sopera.di.smooks.ComponentFacade inputFlow_tSmooksInput_1 = org.sopera.di.smooks.ComponentFacade.INSTANCE;

		inputFlow_tSmooksInput_1.setXPath("/Order/header");
		inputFlow_tSmooksInput_1.setXPath("/Order/customer-details");
		inputFlow_tSmooksInput_1.setXPath("/Order/order-item");
		inputFlow_tSmooksInput_1.setXPath("/Order/order-item");

		// Step 2
		// Activate the inputFlow

		try {
			inputFlow_tSmooksInput_1.setMapping(new java.io.FileInputStream(
					"/.././resources/smooks-mapping.xml"));
			inputFlow_tSmooksInput_1.setEDI(new java.io.FileInputStream(
					"/.././resources/test.edi"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		inputFlow_tSmooksInput_1.start();

		inputFlow_tSmooksInput_1.startRead();

		// Step 3
		// Loop for the data communication iterations

		while (!inputFlow_tSmooksInput_1.isEndOfFlow()) { // loop to iterate
			// the data
			// communication
			System.out.println("!!!");
			inputFlow_tSmooksInput_1.next();
		}
	}
}
