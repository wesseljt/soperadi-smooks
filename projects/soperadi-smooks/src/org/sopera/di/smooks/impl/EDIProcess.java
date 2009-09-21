/*******************************************************************************
 * Copyright (c) 2009 SOPERA GmbH
 * All rights reserved. 
 * This program and the accompanying materials are made available
 * under the terms of the GNU Lesser General Public License v 3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/

package org.sopera.di.smooks.impl;

import static junit.framework.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.namespace.QName;
import org.milyn.edisax.EDIConfigurationException;
import org.milyn.edisax.EDIParser;
import org.sopera.di.smooks.xpath.SAXLocation;
import org.sopera.di.smooks.xpath.impl.SAXLocationImpl;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Run as a thread, filling the structure StringTags res using the flow of SAX events. 
 * @author mirom
 *
 */
	public class EDIProcess extends DefaultHandler implements Runnable
	{
		StringTags res; // TugString resource
		String tagKey;  // Current tag
		EDIParser parser; // Parser
		InputStream edi; // EDI-massage stream
		InputStream mapping; // Mapping stream
		private int flagXPath = 0; // location flag
		
	
		private SAXLocation location = new SAXLocationImpl(); // Current location
		private SAXLocation loc = new SAXLocationImpl(); // Location 
	
	
		public SAXLocation getLocation() {
			return location;
		}

		public SAXLocation getLoc() {
			return loc;
		}

		public void setLocation(SAXLocation location) {
			this.location = location;
		}

		public void setLoc(SAXLocation loc) {
			this.loc = loc;
		}
	
	public EDIProcess(StringTags res) 
	{
		parser = new EDIParser();
		edi = getClass().getResourceAsStream("/smooks.edi");
		assertNotNull("Can't find EDI file for test",edi);
		mapping = getClass().getResourceAsStream("/smooks-mapping.xml");
		assertNotNull("Can't find mapping file", mapping);
		parser.setContentHandler(this);
		try {
			parser.setMappingModel(EDIParser.parseMappingModel(mapping));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EDIConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loc.startElement(new QName("", "Order"));
		loc.startElement(new QName("", "order-item"));
		//System.err.println(loc);
		
		new Thread(this, "EDIProcess").start();
		this.res = res;
	}
	/**
	 * 
	 * @param res
	 * @param edi
	 * @param mapping Mapping stream
	 * @param loc
	 */
	public EDIProcess(StringTags res, InputStream edi, InputStream mapping,
			SAXLocation loc) {
		super();
		this.res = res;
		this.edi = edi;
		this.loc = loc;
		
		parser = new EDIParser();
		parser.setContentHandler(this);
		
		try {
			parser.setMappingModel(EDIParser.parseMappingModel(mapping));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EDIConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//loc.startElement(new QName("", "Order"));
		//loc.startElement(new QName("", "order-item"));
		
		
		this.res = res;
		//new Thread(this, "EDIProcess").start();

	}

	public void run()
	{
		try {
			parser.parse(new InputSource(edi));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	 public void startElement(String uri, String localName, String qName,
	   Attributes attributes) throws SAXException {
		tagKey = localName;
	  location.startElement(new QName(uri, localName));
	  if (location.equals(loc)) {
		  flagXPath = 1;
		  res.startWrite();
	  }
	 }
	 @Override
	 public void endElement(String uri, String localName, String qName
	   ) throws SAXException {
	  if (location.equals(loc)) {
		  flagXPath = 0;
		  res.endWrite();
	  }   

	  location.endElement();
	 }
	 @Override
	 public void characters(char[] ch, int start, int length) throws SAXException {
	     String value = new String(ch,start,length);
	     if (flagXPath>0) {
		     if (!Character.isISOControl(value.charAt(0))) {
		    	 res.write(tagKey, value);
		     }
	     }
	 }

	@Override
	public void endDocument() throws SAXException {
		System.out.println("endDocument BEGIN");
		res.startWrite();
		res.setEnd(true);
		res.endWrite();
		System.out.println("endDocument END");
		
	}
	 
}
