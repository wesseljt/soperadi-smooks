/*******************************************************************************
 * Copyright (c) 2009 SOPERA GmbH
 * All rights reserved. 
 * This program and the accompanying materials are made available
 * under the terms of the GNU Lesser General Public License v 3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/

package org.sopera.di.smooks.impl;

import javax.xml.namespace.QName;

import org.sopera.di.smooks.xpath.SAXLocation;
import org.sopera.di.smooks.xpath.SXPathExpr;
import org.sopera.di.smooks.xpath.impl.SAXLocationImpl;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * {@link ContentHandler} that delegates calls of the SAX event methods
 * to delegate {@link ContentHandler} in case events are
 * not filtered-out by X-Path-like expression.
 * 
 * @author zubairov
 */
public class FilteredDelegateHandler implements ContentHandler {

	private final ContentHandler delegate;
	private final SXPathExpr expr;
	private final SAXLocation location = new SAXLocationImpl();
	
	/**
	 * 
	 * @param delegate
	 */
	public FilteredDelegateHandler(ContentHandler delegate, SXPathExpr expr) {
		this.delegate = delegate;
		this.expr = expr;
	}

	
	/**
	 * {@inheritDoc}
	 */
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		delegate.characters(ch, start, length);
	}

	/**
	 * {@inheritDoc}
	 */
	public void endDocument() throws SAXException {
		delegate.endDocument();
	}

	/**
	 * {@inheritDoc}
	 */
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		location.endElement();
		if (expr.match(location)) {
			delegate.endElement(uri, localName, qName);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void endPrefixMapping(String prefix) throws SAXException {
		delegate.endPrefixMapping(prefix);
	}

	/**
	 * {@inheritDoc}
	 */
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		delegate.ignorableWhitespace(ch, start, length);
	}

	/**
	 * {@inheritDoc}
	 */
	public void processingInstruction(String target, String data)
			throws SAXException {
		delegate.processingInstruction(target, data);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setDocumentLocator(Locator locator) {
		delegate.setDocumentLocator(locator);
	}

	/**
	 * {@inheritDoc}
	 */
	public void skippedEntity(String name) throws SAXException {
		delegate.skippedEntity(name);
	}

	/**
	 * {@inheritDoc}
	 */
	public void startDocument() throws SAXException {
		delegate.startDocument();
	}

	/**
	 * {@inheritDoc}
	 */
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		location.startElement(new QName(uri, localName));
		if (expr.match(location)) {
			delegate.startElement(uri, localName, qName, atts);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		delegate.startPrefixMapping(prefix, uri);
	}

}
