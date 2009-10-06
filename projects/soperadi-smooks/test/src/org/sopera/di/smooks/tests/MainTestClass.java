/*******************************************************************************
 * Copyright (c) 2009 SOPERA GmbH
 * All rights reserved. 
 * This program and the accompanying materials are made available
 * under the terms of the GNU Lesser General Public License v 3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/

package org.sopera.di.smooks.tests;

import junit.framework.TestSuite;

/**
 * Main class for testing code.
 * 
 * Run this class with help of JUnit and it will perform all test cases, which
 * are needed.
 * 
 * @author Alexander
 */
public class MainTestClass extends ProjectTest {

	/**
	 * @param name
	 *            reference to a named test method.
	 */
	public MainTestClass(String name) {
		super(name);
	}

	/**
	 * Method shows a suite of tests, that are needed to perform.
	 * 
	 * @return collection of test cases.
	 */
	public static TestSuite suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(ComponentFacadeTest.suite());
		suite.addTest(StringTagsTest.suite());
		suite.addTest(EDIProcessTest.suite());
		suite.addTestSuite(SAXLocationTest.class);
		suite.addTestSuite(XPathParsingTest.class);
		suite.addTestSuite(ModuleTest.class);
		//suite.addTest();
		//suite.addTestSuite(EDIParserTest.class);
		return suite;
	}
}
