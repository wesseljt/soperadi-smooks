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
 * Test for {@link org.sopera.di.smooksComponent.impl.ComponentFacadeImpl}
 * 
 * @author Alexander
 * 
 */
public class ComponentFacadeImplTest extends ProjectTest {

	public ComponentFacadeImplTest(String name) {
		super(name);
	}
	
	public static TestSuite suite() {
		TestSuite suite = new TestSuite();
		return suite;
	}
}
