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

import org.sopera.di.smooksComponent.ComponentFacade;

/**
 * Basic test that tests that {@link ComponentFacade} can be created
 * 
 * @author Alexander
 *
 */
public class ComponentModuleTest extends ProjectTest {

	public ComponentModuleTest(String name) {
		super(name);
	}
	
	public static TestSuite suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new ComponentModuleTest("testInit"));
		return suite;
	}
	
	public void testInit() throws Exception {
		ComponentFacade instance = ComponentFacade.INSTANCE;
		assertNotNull(instance);
	}
}
