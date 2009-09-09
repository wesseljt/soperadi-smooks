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

import org.junit.Test;
import org.sopera.di.smooks.FilterBuilder;
import org.sopera.di.smooks.impl.FilterBuilderImpl;
import org.sopera.di.smooks.xpath.SAXPathExpressionBuilder;
import org.sopera.di.smooks.xpath.SXPathExpr;
import org.sopera.di.smooks.xpath.impl.SAXPathExpressionBuilderImpl;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;


/**
 * Test for {@link FilterBuilder} 
 * 
 * @author zubairov
 */
public class FilterBuilderTest extends DefaultHandler {
	
	FilterBuilder builder = new FilterBuilderImpl();
	
	@Test
	public void testBuilder() throws Exception {
		SAXPathExpressionBuilder exprBuilder = new SAXPathExpressionBuilderImpl();
		SXPathExpr expr = exprBuilder.buildExpression("order");
		ContentHandler handler = builder.startWith(this).addXPathFilter(expr).build();
		XMLReader reader = XMLReaderFactory.createXMLReader();
		reader.setContentHandler(handler);
		reader.parse(new InputSource(getClass().getResourceAsStream("/order.xml")));
	}
	
}
