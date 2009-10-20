/*******************************************************************************
 * Copyright (c) 2009 SOPERA GmbH
 * All rights reserved. 
 * This program and the accompanying materials are made available
 * under the terms of the GNU Lesser General Public License v 3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package org.sopera.di.smooksComponent;

import org.sopera.di.smooksComponent.ComponentFacade;
import org.sopera.di.smooksComponent.impl.ComponentFacadeImpl;

import com.google.inject.AbstractModule;

/**
 * Module that will configure injection rules
 * 
 * @author Alexander
 * @see AbstractModule
 */
public class ComponentModule extends AbstractModule{
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void configure() {
		bind(ComponentFacade.class).to(ComponentFacadeImpl.class);
	}
}
