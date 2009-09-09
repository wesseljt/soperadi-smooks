/*******************************************************************************
 * Copyright (c) 2009 SOPERA GmbH
 * All rights reserved. 
 * This program and the accompanying materials are made available
 * under the terms of the GNU Lesser General Public License v 3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
/*******************************************************************************
 * Copyright (c) 2009 SOPERA GmbH
 * All rights reserved. 
 * This program and the accompanying materials are made available
 * under the terms of the GNU Lesser General Public License v 3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/

package org.sopera.di.smooks.tests;

import static junit.framework.Assert.assertNotNull;

import java.io.InputStream;

import javax.xml.namespace.QName;

import org.junit.Test;
import org.milyn.edisax.EDIParser;
import org.sopera.di.smooks.xpath.SAXLocation;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Testing {@link EDIParser} from Smooks library
 * 
 * @author zubairov
 */
public class EDIParserTest extends DefaultHandler {
	private final SAXLocation location = new SAXLocation();

	@Test
	public void testParser() throws Exception {
		InputStream edi = getClass().getResourceAsStream("/smooks.edi");
		assertNotNull("Can't find EDI file for test",edi);
		InputStream mapping = getClass().getResourceAsStream("/smooks-mapping.xml");
		assertNotNull("Can't find mapping file", mapping);
		EDIParser parser = new EDIParser();
		parser.setContentHandler(this);
		parser.setMappingModel(EDIParser.parseMappingModel(mapping));
		parser.parse(new InputSource(edi));
	}
	
	/**
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		location.startElement(new QName(uri, localName));
		System.err.println("Starting EDI element: " + "{" + uri + "}" + localName);
	}
	@Override
	public void endElement(String uri, String localName, String qName
			) throws SAXException {
		location.endElement();
		System.err.println("Ending EDI element: " + "{" + uri + "}" + localName);
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		   String value = new String(ch,start,length);
		   if (!Character.isISOControl(value.charAt(0))) {
		    System.err.println("VALUE " + value + " FOUND IN " + location.toString());
		   }
	}
}
