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

import static junit.framework.Assert.*;

import org.junit.Test;
import org.sopera.di.smooks.ComponentFacade;

/**
 * Basic test that tests that {@link ComponentFacade} can be created
 * @author zubairov
 */
public class ModuleTest {

	@Test
	public void testInit() throws Exception {
		ComponentFacade instance = ComponentFacade.INSTANCE;
		assertNotNull(instance);
	}
	
}
