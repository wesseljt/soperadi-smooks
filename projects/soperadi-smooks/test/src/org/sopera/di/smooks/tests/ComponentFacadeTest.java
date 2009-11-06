/*******************************************************************************
 * Copyright (c) 2009 SOPERA GmbH
 * All rights reserved. 
 * This program and the accompanying materials are made available
 * under the terms of the GNU Lesser General Public License v 3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/

package org.sopera.di.smooks.tests;

import junit.extensions.TestSetup;
import junit.framework.*;
import java.io.File;
import java.io.InputStream;

/**
 * Test for {@link org.sopera.di.smooks.impl.ComponentFacadeImpl}
 * 
 * @author Alexander
 * 
 */
public class ComponentFacadeTest extends ProjectTest {

	private static org.sopera.di.smooks.ComponentFacade inputFlow;

	/**
	 * @param name
	 *            reference to a named test method.
	 */
	public ComponentFacadeTest(String name) {
		super(name);
	}

	/**
	 * This method perform one time initialization of all resources, that are
	 * needed to test {@link org.sopera.di.smooks.impl.ComponentFacadeImpl}.
	 */
	public static void oneTimeSetUp() {
		inputFlow = org.sopera.di.smooks.ComponentFacade.INSTANCE;
		assertNotNull("Can't create ComponentFacade instance", inputFlow);
	}

	/**
	 * This method perform one time cleanup of all resources, that are needed to
	 * test {@link org.sopera.di.smooks.impl.ComponentFacadeImpl}.
	 */
	public static void oneTimeTearDown() {
		inputFlow = null;
		assertNull(inputFlow);
	}

	/**
	 * This method perform initialization of all resources, that are needed to
	 * test {@link org.sopera.di.smooks.impl.ComponentFacadeImpl} before each
	 * test case.
	 */
	protected void setUp() {

	}

	/**
	 * This method perform cleanup of all resources, that are needed to test
	 * {@link org.sopera.di.smooks.impl.ComponentFacadeImpl} after each test
	 * case.
	 */
	protected void tearDown() {

	}

	/**
	 * Method shows a suite of tests for
	 * {@link org.sopera.di.smooks.impl.ComponentFacadeImpl}, that are needed to
	 * perform.
	 * 
	 * @return collection of test cases.
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new ComponentFacadeTest("testSetEDI"));
		suite.addTest(new ComponentFacadeTest("testSetEDIFileNotFoundException"));
		suite.addTest(new ComponentFacadeTest("testSetEDISecurityException"));
		suite.addTest(new ComponentFacadeTest("testSetMapping"));
		suite.addTest(new ComponentFacadeTest("testSetMappingFileNotFoundException"));
		suite.addTest(new ComponentFacadeTest("testSetMappingSecurityException"));
		//suite.addTest(new ComponentFacadeTest("testSetXPath"));
		//suite.addTest(new ComponentFacadeTest("testGetXPath"));
		TestSetup wrapper = new TestSetup(suite) {
			protected void setUp() {
				oneTimeSetUp();
			}

			protected void tearDown() {
				oneTimeTearDown();
			}
		};
		return wrapper;
	}

	/**
	 * Test case for
	 * {@link org.sopera.di.smooks.impl.ComponentFacadeImpl#setEDI(InputStream)}
	 * method.
	 */
	public void testSetEDI() {
		String edi = (new File("").getAbsolutePath())+"/test/resources/smooks.edi";
		assertNotNull("Can't find EDI file", edi);
		inputFlow.setEDI(edi);
	}
	
	/**
	 * Test case for
	 * {@link org.sopera.di.smooks.impl.ComponentFacadeImpl#setEDI(InputStream)}
	 * method.
	 */
	public void testSetEDIFileNotFoundException() {
		try {
			inputFlow.setEDI("incorrect edi");
			fail("Should have thrown an exception");
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test case for
	 * {@link org.sopera.di.smooks.impl.ComponentFacadeImpl#setEDI(InputStream)}
	 * method.
	 */
	public void testSetEDISecurityException() {

	}
	
	/**
	 * Test case for
	 * {@link org.sopera.di.smooks.impl.ComponentFacadeImpl#setMapping(InputStream)}
	 * method.
	 */
	public void testSetMapping() {
		String mapping = (new File("").getAbsolutePath())+"\\test\\resources\\edi-to-xml-input\\smooks-mapping.xml";
		assertNotNull("Can't find mapping file", mapping);
		inputFlow.setMapping(mapping);
	}

	/**
	 * Test case for
	 * {@link org.sopera.di.smooks.impl.ComponentFacadeImpl#setMapping(InputStream)}
	 * method.
	 */
	public void testSetMappingFileNotFoundException() {
		try {
			inputFlow.setMapping("incorrect mapping");
			fail("Should throw an exception");
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test case for
	 * {@link org.sopera.di.smooks.impl.ComponentFacadeImpl#setMapping(InputStream)}
	 * method.
	 */
	public void testSetMappingSecurityException() {
		
	}
	
	/**
	 * Test case for
	 * {@link org.sopera.di.smooks.impl.ComponentFacadeImpl#setXPath(String)}
	 * method
	 */
	public void testSetXPath() {
		//HashMap<String, SAXLocation> xPaths = new HashMap<String, SAXLocation>();
	}

	/**
	 * Test case for
	 * {@link org.sopera.di.smooks.impl.ComponentFacadeImpl#getXPath()} method
	 */
	public void testGetXPath() {
		//EDIProcess parser = EDIProcess.INSTANCE;
		//assertNotNull("Can't create EDIProcess instance", parser);
		//String xPath = null;
		//xPath = inputFlow.getXPath();
		//assertNull(xPath);
	}
}
