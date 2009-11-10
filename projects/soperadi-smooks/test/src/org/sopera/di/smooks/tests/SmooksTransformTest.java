/*******************************************************************************
 * Copyright (c) 2009 SOPERA GmbH
 * All rights reserved. 
 * This program and the accompanying materials are made available
 * under the terms of the GNU Lesser General Public License v 3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package org.sopera.di.smooks.tests;

import java.io.IOException;
import junit.framework.TestSuite;
import org.milyn.SmooksException;
import org.sopera.di.smooks.impl.SmooksTransformImpl;
import org.xml.sax.SAXException;

public class SmooksTransformTest extends ProjectTest {
	public SmooksTransformTest(String name) {
		super(name);
	}

	public static TestSuite suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new SmooksTransformTest("testRunCsvToXml"));
		suite.addTest(new SmooksTransformTest("testFillMappingFile"));
		suite.addTest(new SmooksTransformTest("testRunEdiToXml"));
		suite.addTest(new SmooksTransformTest("testRunXmlToXmlXslt"));
		suite.addTest(new SmooksTransformTest("testRunXmlToXml"));
		suite.addTest(new SmooksTransformTest("testRunXmlToXmlGroovy"));
		return suite;
	}

	public void testFillMappingFile() throws IOException, SAXException {
		SmooksTransformImpl smooksTransform = new SmooksTransformImpl();
		assertNotNull(smooksTransform);
		smooksTransform
				.setConfigFileName("test/resources/csv-to-xml/smooks--config.xml");
		assertEquals("test/resources/csv-to-xml/smooks--config.xml",
				smooksTransform.getConfigFileName());
		try {
			smooksTransform.runSmooksTransform();
			fail("Should be RuntimeException!");
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}

	public void testRunCsvToXml() throws IOException, SAXException,
			SmooksException, InterruptedException {
		SmooksTransformImpl smooksTransform = new SmooksTransformImpl();
		assertNotNull(smooksTransform);
		smooksTransform
				.setConfigFileName("test/resources/csv-to-xml/smooks-config.xml");
		assertEquals("test/resources/csv-to-xml/smooks-config.xml",
				smooksTransform.getConfigFileName());
		smooksTransform
				.setInputFileName("test/resources/csv-to-xml/input-message.csv");
		assertEquals("test/resources/csv-to-xml/input-message.csv",
				smooksTransform.getInputFileName());
		smooksTransform.setOutputFileName("test/resources/csv-to-xml/out.xml");
		assertEquals("test/resources/csv-to-xml/out.xml", smooksTransform
				.getOutputFileName());
		smooksTransform.setMappingResource("!!!not-csv-to-xml!!!",
				"not-mapping");
		assertTrue(smooksTransform.runSmooksTransform());
	}

	public void testRunEdiToXml() throws SmooksException, IOException,
			SAXException {
		SmooksTransformImpl smooksTransform = new SmooksTransformImpl();
		assertNotNull(smooksTransform);
		smooksTransform
				.setConfigFileName("test/resources/edi-to-xml/smooks-config.xml");
		assertEquals("test/resources/edi-to-xml/smooks-config.xml",
				smooksTransform.getConfigFileName());
		smooksTransform
				.setInputFileName("test/resources/edi-to-xml/input-message.edi");
		assertEquals("test/resources/edi-to-xml/input-message.edi",
				smooksTransform.getInputFileName());
		smooksTransform.setOutputFileName("test/resources/edi-to-xml/out.xml");
		assertEquals("test/resources/edi-to-xml/out.xml", smooksTransform
				.getOutputFileName());
		smooksTransform.setMappingResource("!!!smooks_mapping!!!",
				"test/resources/edi-to-xml/smooks-mapping.xml");
		assertTrue(smooksTransform.runSmooksTransform());
	}

	public void testRunXmlToXml() throws IOException, SAXException,
			SmooksException, InterruptedException {
		SmooksTransformImpl smooksTransform = new SmooksTransformImpl();
		assertNotNull(smooksTransform);
		smooksTransform
				.setConfigFileName("test/resources/xml-to-xml/smooks-config.xml");
		assertEquals("test/resources/xml-to-xml/smooks-config.xml",
				smooksTransform.getConfigFileName());
		smooksTransform
				.setInputFileName("test/resources/xml-to-xml/input-message.xml");
		assertEquals("test/resources/xml-to-xml/input-message.xml",
				smooksTransform.getInputFileName());
		smooksTransform.setOutputFileName("test/resources/xml-to-xml/out.xml");
		assertEquals("test/resources/xml-to-xml/out.xml", smooksTransform
				.getOutputFileName());
		smooksTransform.setMappingResource("!!!not-xml-to-xml!!!",
				"not-mapping");
		assertTrue(smooksTransform.runSmooksTransform());
	}

	public void testRunXmlToXmlGroovy() throws IOException, SAXException,
			SmooksException, InterruptedException {
		SmooksTransformImpl smooksTransform = new SmooksTransformImpl();
		assertNotNull(smooksTransform);
		smooksTransform
				.setConfigFileName("test/resources/xml-xslt/smooks-config2.xml");
		assertEquals("test/resources/xml-xslt/smooks-config2.xml",
				smooksTransform.getConfigFileName());
		smooksTransform
				.setInputFileName("test/resources/xml-xslt/input-message2.xml");
		assertEquals("test/resources/xml-xslt/input-message2.xml",
				smooksTransform.getInputFileName());
		smooksTransform.setOutputFileName("test/resources/xml-xslt/out2.xml");
		assertEquals("test/resources/xml-xslt/out2.xml", smooksTransform
				.getOutputFileName());
		smooksTransform.setMappingResource("!!!groovy!!!",
				"test/resources/xml-xslt/DateFormatter.groovy");
		smooksTransform.setMappingResource("!!!order!!!",
		"test/resources/xml-xslt/order.xsl");
		assertTrue(smooksTransform.runSmooksTransform());
	}

	public void testRunXmlToXmlXslt() throws IOException, SAXException,
			SmooksException, InterruptedException {
		SmooksTransformImpl smooksTransform = new SmooksTransformImpl();
		assertNotNull(smooksTransform);
		smooksTransform
				.setConfigFileName("test/resources/xml-xslt/smooks-config1.xml");
		assertEquals("test/resources/xml-xslt/smooks-config1.xml",
				smooksTransform.getConfigFileName());
		smooksTransform
				.setInputFileName("test/resources/xml-xslt/input-message1.xml");
		assertEquals("test/resources/xml-xslt/input-message1.xml",
				smooksTransform.getInputFileName());
		smooksTransform.setOutputFileName("test/resources/xml-xslt/out3.xml");
		assertEquals("test/resources/xml-xslt/out3.xml", smooksTransform
				.getOutputFileName());
		smooksTransform.setMappingResource("!!!BasicXslTransform!!!",
				"test/resources/xml-xslt/BasicXslTransform.xsl");
		assertTrue(smooksTransform.runSmooksTransform());
	}

}
