/*******************************************************************************
 * Copyright (c) 2009 SOPERA GmbH
 * All rights reserved. 
 * This program and the accompanying materials are made available
 * under the terms of the GNU Lesser General Public License v 3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/

package org.sopera.di.smooks.impl;

import java.io.InputStream;

import org.milyn.edisax.EDIParser;
import org.sopera.di.smooks.ComponentFacade;
import org.sopera.di.smooks.xpath.SAXLocation;
import org.sopera.di.smooks.xpath.impl.SAXLocationImpl;
import javax.xml.namespace.QName;

/**
 * This class organize the read sessions loop for StringTags structure.
 * Implementation of the {@link ComponentFacade}
 * 
 * @author soperadi-smooks
 */
public class ComponentFacadeImpl implements ComponentFacade {

	StringTags res;
	Thread writer;
	EDIParser parser; // Parser
	InputStream EDI; // EDI-massage stream
	InputStream mapping; // Mapping stream

	private SAXLocation loc = null; // new SAXLocationImpl(); // Location
	private String xPath = null;

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
	public void setXPath(String loopXPath) {
		xPath = loopXPath;
		String[] paramNames = null;
		loc = new SAXLocationImpl();
		if (loopXPath != null) {
			paramNames = loopXPath.split("/");
		}
		if (paramNames != null) {
			for (int i = 0; i < paramNames.length; i++) {
				if (paramNames[i] != null && !("").equals(paramNames[i])) {
					loc.startElement(new QName("", paramNames[i]));
					System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
					System.out.println(loc);
				}
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

		res = new StringTags();
		EDIProcess parser = new EDIProcess(res, EDI, mapping, loc);
		writer = new Thread(parser);
		writer.start();
	}

	/**
	 * 
	 * @see org.sopera.di.smooks.ComponentFacade#getXPath()
	 */
	public String getXPath() {
		return xPath;
	}
}
