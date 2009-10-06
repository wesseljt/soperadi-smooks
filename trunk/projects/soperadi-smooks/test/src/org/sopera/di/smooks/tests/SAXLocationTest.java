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

import javax.xml.namespace.QName;
import org.sopera.di.smooks.xpath.SAXLocation;
import org.sopera.di.smooks.xpath.impl.SAXLocationImpl;


/**
 * Test for {@link SAXLocationImpl}
 * 
 * @author zubairov
 */
public class SAXLocationTest extends ProjectTest {

	private static final String ELEMENT_ONE = "parent";
	private static final String NAMESPACE1 = "namespace";
	private static final String ELEMENT_TWO = "child";
	private static final String NAMESPACE2 = "namespace2";

	public SAXLocationTest(String name) {
		super(name);
	}
	
	public void testLocation() throws Exception {
		SAXLocation location = new SAXLocationImpl();
		assertEquals(0, location.depth());			
		location.startElement(new QName(NAMESPACE1, ELEMENT_ONE));
		assertEquals(1, location.depth());
		assertEquals("/{namespace}parent[1]", location.toString());
		location.startElement(new QName(NAMESPACE1, ELEMENT_TWO));
		assertEquals(2, location.depth());
		assertEquals("/{namespace}parent[1]/{namespace}child[1]", location.toString());
		location.endElement();
		assertEquals(1, location.depth());
		assertEquals("/{namespace}parent[1]", location.toString());
		location.startElement(new QName(NAMESPACE1, ELEMENT_TWO));
		assertEquals(2, location.depth());
		assertEquals("/{namespace}parent[1]/{namespace}child[2]", location.toString());
		location.endElement();
		location.startElement(new QName(NAMESPACE2, ELEMENT_TWO));
		assertEquals(2, location.depth());
		assertEquals("/{namespace}parent[1]/{namespace2}child[1]", location.toString());
		location.endElement();
		location.endElement();
		assertEquals(0, location.depth());			
	}
	
}
