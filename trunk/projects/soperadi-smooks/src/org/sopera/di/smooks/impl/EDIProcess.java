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
import org.sopera.di.smooks.ComponentFacade;
import org.sopera.di.smooks.xpath.SAXLocation;
import org.sopera.di.smooks.xpath.impl.SAXLocationImpl;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This class transforms the EDI-message data in the flow of SAX-events. Run as
 * a thread, EDIProcess filling the structure {@link StringTags} using the
 * present location {@link SAXLocation} to determine the necessary data.
 * Iteratively perform recording sessions, after each session, waiting the
 * ending of reading session. To use this class, we have to organize the read
 * sessions loop for {@link StringTags} structure.
 * 
 * @author soperadi-smooks
 * 
 */
public class EDIProcess extends DefaultHandler implements Runnable {
	private StringTags res; // TugString resource
	private String tagKey; // Current tag
	private EDIParser parser; // Parser
	private InputStream edi; // EDI-massage stream
	private InputStream mapping; // Mapping stream
	private boolean flagXPath = false; // location flag

	private SAXLocation location = new SAXLocationImpl(); // Current location
	private SAXLocation loc = new SAXLocationImpl(); // Present location

	/**
	 * Set the present location to determine the necessary data.
	 * 
	 * @param loc
	 */
	public void setLoc(SAXLocation loc) {
		this.loc = loc;
	}

	/**
	 * Class constructor specifying just structure for filling and
	 * synchronization. The present location set by default.
	 * 
	 * @param res
	 *            the structure for filling
	 */
	public EDIProcess(StringTags res) {
		parser = new EDIParser();
		edi = getClass().getResourceAsStream("/smooks.edi");
		assertNotNull("Can't find EDI file for test", edi);
		mapping = getClass().getResourceAsStream("/smooks-mapping.xml");
		assertNotNull("Can't find mapping file", mapping);
		parser.setContentHandler(this);
		try {
			parser.setMappingModel(EDIParser.parseMappingModel(mapping));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (EDIConfigurationException e) {
			e.printStackTrace();
		}
		loc.startElement(new QName("", "Order"));
		loc.startElement(new QName("", "order-item"));

		new Thread(this, "EDIProcess").start();
		this.res = res;
	}

	/**
	 * Class constructor specifying necessary streams, structure for filling and
	 * synchronization, location of reading data
	 * 
	 * @param res
	 *            structure for filling
	 * @param edi
	 *            input stream of EDI-file
	 * @param mapping
	 *            input stream of Smooks mapping file (EDI-to-XML)
	 * @param loc
	 *            location of reading data
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
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (EDIConfigurationException e) {
			e.printStackTrace();
		}

		this.res = res;

	}

	/**
	 * Starts the flow of SAX-events
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			parser.parse(new InputSource(edi));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Change the current location. Set the name of current element to class
	 * field. Compares the current and preset location. If they coincide, starts
	 * the session recording into structure. If the read session not ended yet,
	 * waiting for this.
	 * 
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(String, String,
	 *      String, Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		tagKey = localName;
		location.startElement(new QName(uri, localName));
		if (location.equals(loc)) {
			flagXPath = true;
			res.startWrite();
		}
	}

	/**
	 * Compares the current and preset location, if they coincide, ends the
	 * session recording into structure. If the read-stream waiting for this
	 * structure, it can start. <br>
	 * Change the current location.
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (location.equals(loc)) {
			flagXPath = false;
			res.endWrite();
		}

		location.endElement();
	}

	/**
	 * If the recording session started, write the current String value to the
	 * structure. Else, do nothing.
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (flagXPath) {
			String value = new String(ch, start, length);
			if (!Character.isISOControl(value.charAt(0))) {
				res.write(tagKey, value);
			}
		}
	}

	/**
	 * If the read session not ended yet, waiting for this. Set the state of
	 * structure to END.
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException {
		System.out.println("endDocument BEGIN");
		res.startWrite();
		res.setEnd(true);
		res.endWrite();
		System.out.println("endDocument END");

	}

}
