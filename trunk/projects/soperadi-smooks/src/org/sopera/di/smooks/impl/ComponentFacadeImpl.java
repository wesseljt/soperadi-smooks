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
 * Default implementation of the {@link ComponentFacade}
 * 
 * @author zubairov
 */
public class ComponentFacadeImpl implements ComponentFacade {
	
	StringTags res;
	Thread writer;
	EDIParser parser; // Parser
	InputStream EDI; // EDI-massage stream
	InputStream mapping; // Mapping stream

	private SAXLocation loc = null; // new SAXLocationImpl(); // Location 
	private String xPath = null;

	public InputStream getEDI() {
		return EDI;
	}

	public InputStream getMapping() {
		return mapping;
	}

	public void setEDI(InputStream edi) {
		EDI = edi;
	}

	public void setMapping(InputStream mapping) {
		this.mapping = mapping;
	}

	public void setXPath(String loopXPath) {
		xPath = loopXPath;
		String[] paramNames = null;
		loc = new SAXLocationImpl();
		if(loopXPath != null){
			paramNames = loopXPath.split("/");
		}	
		if (paramNames != null) {
			for(int i = 0; i < paramNames.length; i++){
				if(paramNames[i]!=null && !("").equals(paramNames[i])){
					loc.startElement(new QName("",paramNames[i]));
					System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
					System.out.println(loc);
				}
			}
		}
		return;
	}

	public boolean isEndOfFlow() {
		return writer == null || !writer.isAlive();
	}


	public String findTagValue(String tagName) {
	return res.findTagValue(tagName);
	}

	public void endRead() {
		res.endRead();
		return;
	}
	public void startRead() {
		res.startRead();
		return;
	}
	public void next() {
					
		return;		
	}


	public void start() {
		res = new StringTags();
		EDIProcess parser = new EDIProcess(res,EDI,mapping,loc);
		writer = new Thread(parser);
		writer.start();
		}

	public String getXPath() {
		return xPath;
	}
	}
