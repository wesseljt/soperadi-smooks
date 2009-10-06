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
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test for {@link org.sopera.di.smooks.impl.StringTagsImpl}
 * 
 * @author Alexander
 * 
 */
public class StringTagsTest extends ProjectTest {
	
	private static org.sopera.di.smooks.StringTags strTags;
//	private EDIProcess parser = EDIProcess.INSTANCE;
	
	public StringTagsTest(String name) {
		super(name);
	}
	
	/**
	 * This method perform one time initialization of all resources, that are
	 * needed to test {@link org.sopera.di.smooks.impl.StringTagsImpl}.
	 */
	public static void oneTimeSetUp() {
	}

	/**
	 * This method perform one time cleanup of all resources, that are needed to
	 * test {@link org.sopera.di.smooks.impl.StringTagsImpl}.
	 */
	public static void oneTimeTearDown() {
	}

	/**
	 * This method perform initialization of all resources, that are needed to
	 * test {@link org.sopera.di.smooks.impl.StringTagsImpl} before each
	 * test case.
	 */
	protected void setUp() {
		strTags = org.sopera.di.smooks.StringTags.INSTANCE;
		assertNotNull("Can't create StringTags instance", strTags);
	}

	/**
	 * This method perform cleanup of all resources, that are needed to test
	 * {@link org.sopera.di.smooks.impl.StringTagsImpl} after each test
	 * case.
	 */
	protected void tearDown() {
		strTags = null;
		assertNull(strTags);
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
		suite.addTest(new StringTagsTest("testIsEnd"));
		suite.addTest(new StringTagsTest("testSetEnd"));
		suite.addTest(new StringTagsTest("testFindTagValue"));
		suite.addTest(new StringTagsTest("testWrite"));
		suite.addTest(new StringTagsTest("testStartWrite"));
		//suite.addTest(new StringTagsTest("testEndWrite"));
		//suite.addTest(new StringTagsTest("testStartRead"));
		//suite.addTest(new StringTagsTest("testEndRead"));
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
	
	public void testIsEnd() {
		assertFalse(strTags.isEnd());
		strTags.setEnd(true);
		assertTrue(strTags.isEnd());
	}
	
	public void testSetEnd() {
		strTags.setEnd(true);
		assertTrue(strTags.isEnd());
		strTags.setEnd(false);
		assertFalse(strTags.isEnd());
	}
	
	public void testFindTagValue() {
		String value = strTags.findTagValue(null);
		assertNull(value);
		strTags.write("price", "10");
		value = strTags.findTagValue("price");
		assertEquals(value, "10");
	}
	
	public void testWrite() {
		strTags.write("price", "11");
		assertEquals("11", strTags.findTagValue("price"));
	}
	
	public void testStartWrite() {
//		Thread writer = new Thread(parser);
	}
	
	public void testStartRead() {
		
	}
	
	public void testEndWrite() {
		
	}

	public void testEndRead() {
		
	}
}
