/*******************************************************************************
 * Copyright (c) 2009 SOPERA GmbH
 * All rights reserved. 
 * This program and the accompanying materials are made available
 * under the terms of the GNU Lesser General Public License v 3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/

package org.sopera.di.smooks.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import org.milyn.edisax.EDIParser;
import org.sopera.di.smooks.EDIProcess;
import org.sopera.di.smooks.StringTags;
import org.sopera.di.smooks.impl.EDIProcessImpl;
import org.sopera.di.smooks.impl.StringTagsImpl;
import org.sopera.di.smooks.xpath.SAXLocation;
import org.sopera.di.smooks.xpath.impl.SAXLocationImpl;
import org.xml.sax.SAXException;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class EDIProcessTest extends ProjectTest{
	
	private static EDIProcessImpl parser;
	
	public EDIProcessTest(String name) {
		super(name);
	}
	
	/**
	 * This method perform one time initialization of all resources, that are
	 * needed to test {@link org.sopera.di.smooks.impl.EDIProcessImpl}.
	 */
	public static void oneTimeSetUp() {
		parser = new EDIProcessImpl();
		assertNotNull("Can't create EDIProcess instance", parser);
	}

	/**
	 * This method perform one time cleanup of all resources, that are needed to
	 * test {@link org.sopera.di.smooks.impl.EDIProcessImpl}.
	 */
	public static void oneTimeTearDown() {
		parser = null;
		assertNull(parser);
	}

	/**
	 * This method perform initialization of all resources, that are needed to
	 * test {@link org.sopera.di.smooks.impl.EDIProcessImpl} before each
	 * test case.
	 */
	protected void setUp() {
	}

	/**
	 * This method perform cleanup of all resources, that are needed to test
	 * {@link org.sopera.di.smooks.impl.EDIProcessImpl} after each test
	 * case.
	 */
	protected void tearDown() {
	}

	/**
	 * Method shows a suite of tests for
	 * {@link org.sopera.di.smooks.impl.StringTagsImpl}, that are needed to
	 * perform.
	 * 
	 * @return collection of test cases.
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new EDIProcessTest("testSetLoc"));
		suite.addTest(new EDIProcessTest("testSetLocation"));
		suite.addTest(new EDIProcessTest("testSetRes"));
		suite.addTest(new EDIProcessTest("testSetEDI"));
		suite.addTest(new EDIProcessTest("testSetMapping"));
		suite.addTest(new EDIProcessTest("testSetXPaths"));
		suite.addTest(new EDIProcessTest("testGetXPath"));
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
	
	public void testSetLoc() {
		SAXLocation loc = new SAXLocationImpl();
		assertNotNull(loc);
		parser.setLoc(loc);
	}
	
	public void testSetLocation() {
		SAXLocation loc = new SAXLocationImpl();
		assertNotNull(loc);
		parser.setLocation(loc);
	}
	
	public void testSetRes() {
		StringTags strTags = new StringTagsImpl();
		assertNotNull(strTags);
		parser.setRes(strTags);
	}
	
	public void testSetEDI() throws FileNotFoundException {
		InputStream edi = new FileInputStream("test/resources/smooks.edi");
		assertNotNull("Can't find EDI file", edi);
		parser.setEdi(edi);
	}
	
	public void testSetMapping() throws FileNotFoundException, SAXException {
		InputStream mapping = new FileInputStream("test/resources/smooks-mapping.xml");
		assertNotNull("Can't find EDI file", mapping);
		//parser.setMapping(mapping);
	}
	
	public void testSetXPaths() {
		HashMap<String, SAXLocation> paths = new HashMap<String, SAXLocation>();
		assertNotNull(paths);
		parser.setXPaths(paths);
	}
	
	public void testGetXPath() {
		assertNull(parser.getXPath());
	}
}
