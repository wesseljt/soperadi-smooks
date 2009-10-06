/*******************************************************************************
 * Copyright (c) 2009 SOPERA GmbH
 * All rights reserved. 
 * This program and the accompanying materials are made available
 * under the terms of the GNU Lesser General Public License v 3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package org.sopera.di.smooks;

import org.sopera.di.smooks.impl.ComponentFacadeImpl;
import org.sopera.di.smooks.impl.EDIProcessImpl;
import org.sopera.di.smooks.impl.StringTagsImpl;
import org.sopera.di.smooks.impl.FilterBuilderImpl;
import org.sopera.di.smooks.xpath.SAXLocation;
import org.sopera.di.smooks.xpath.impl.SAXLocationImpl;

import com.google.inject.AbstractModule;

/**
 * Module that will configure injection rules
 * 
 * @author zubairov
 * @see AbstractModule
 */
public class ComponentModule extends AbstractModule {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void configure() {
		bind(ComponentFacade.class).to(ComponentFacadeImpl.class);
		bind(FilterBuilder.class).to(FilterBuilderImpl.class);
		bind(StringTags.class).to(StringTagsImpl.class);
		bind(SAXLocation.class).to(SAXLocationImpl.class);
		bind(EDIProcess.class).to(EDIProcessImpl.class);
	}

}
