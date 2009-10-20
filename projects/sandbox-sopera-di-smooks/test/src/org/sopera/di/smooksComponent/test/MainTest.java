/*******************************************************************************
 * Copyright (c) 2009 SOPERA GmbH
 * All rights reserved. 
 * This program and the accompanying materials are made available
 * under the terms of the GNU Lesser General Public License v 3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package org.sopera.di.smooksComponent.test;

import junit.framework.TestSuite;

/**
 * Main class for testing code.
 * 
 * Run this class with help of JUnit and it will perform all test cases, which
 * are needed.
 * 
 * @author Alexander
 */
public class MainTest extends ProjectTest{

	/**
	 * @param name
	 *            reference to a named test method.
	 */
	public MainTest(String name) {
		super(name);
	}
	
	/**
	 * Method shows a suite of tests, that are needed to perform.
	 * 
	 * @return collection of test cases.
	 */
	public static TestSuite suite() {
		MainTest mainTest = new MainTest("");
		assertNotNull(mainTest);
		TestSuite suite = new TestSuite();
		suite.addTest(ComponentModuleTest.suite());
		suite.addTest(ComponentFacadeImplTest.suite());
		return suite;
	}
}
