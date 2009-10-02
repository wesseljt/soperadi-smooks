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

import org.sopera.di.smooks.ComponentFacade;
import org.sopera.di.smooks.ComponentModule;
import org.sopera.di.smooks.EDIProcess;
import org.sopera.di.smooks.EDIProcessModule;
import org.sopera.di.smooks.StringTags;
import org.sopera.di.smooks.xpath.SAXLocation;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

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
	private InputStream EDI; // EDI-massage stream
	private InputStream mapping; // Mapping stream
	Injector injector = Guice.createInjector(new EDIProcessModule());

	private HashMap<String, SAXLocation> xPaths = new HashMap<String, SAXLocation>();

	/**
	 * 
	 * @see org.sopera.di.smooks.ComponentFacade#setEDI(java.io.InputStream)
	 */

	public void setEDI(String edi) {
		try {
			this.EDI = new java.io.FileInputStream(edi);
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException for " + edi);
			throw new RuntimeException("Can't find EDI file for set up");
		} catch (SecurityException e) {
			System.err.println("SecurityException for " + edi);
			throw new RuntimeException("Denied read access to the EDI file.");
		}
	}

	/**
	 * 
	 * @see org.sopera.di.smooks.ComponentFacade#setMapping(java.io.InputStream)
	 */
	public void setMapping(String mapping) {
		try {
			this.mapping = new java.io.FileInputStream(mapping);
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException for " + mapping);
			throw new RuntimeException("Can't find mapping file for set up");
		} catch (SecurityException e) {
			System.err.println("SecurityException for " + mapping);
			throw new RuntimeException(
					"Denied read access to the mapping file.");
		}
	}

	/**
	 * 
	 * @see org.sopera.di.smooks.ComponentFacade#setXPath(java.lang.String)
	 */
	public void setXPath(String loopPath) {
		if (xPaths.get(loopPath) == null) {
			String[] paramNames = null;
			SAXLocation loc = injector.getInstance(SAXLocation.class);
			if (loopPath != null) {
				paramNames = loopPath.split("/");
			}
			if (paramNames != null) {
				for (int i = 0; i < paramNames.length; i++) {
					if (paramNames[i] != null && !("").equals(paramNames[i])) {
						loc.startElement(new QName("", paramNames[i]));
						// System.out.println(" ------ location in process ------ ");
						// System.out.println(loc);
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
		if (mapping == null) {
			System.err.println("Null pointer exception for mapping");
			throw new RuntimeException("Can't find mapping file for set up");
		} else {
			this.parser.setMapping(mapping);
		}

		if (res == null) {
			System.err.println("Null pointer exception for StringTags");
			throw new RuntimeException(
					"Can't find StringTags structure for set up");
		} else {
			this.parser.setRes(res);
		}

		this.parser.setXPaths(xPaths);

		if (EDI == null) {
			System.err.println("Null pointer exception for EDI");
			throw new RuntimeException("Can't find EDI file for set up");
		} else {
			this.parser.setEdi(EDI);
		}

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
}
