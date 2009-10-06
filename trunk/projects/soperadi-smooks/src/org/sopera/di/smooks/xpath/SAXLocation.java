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

package org.sopera.di.smooks.xpath;

import javax.xml.namespace.QName;

import org.sopera.di.smooks.ComponentFacade;
import org.sopera.di.smooks.ComponentModule;

import com.google.inject.Guice;

/**
 * @author zubairov
 * 
 */
public interface SAXLocation {

	public static final SAXLocation INSTANCE = Guice.createInjector(
			new ComponentModule()).getInstance(SAXLocation.class);
	/**
	 * Start element
	 * 
	 * @param name
	 */
	public abstract void startElement(QName name);

	/**
	 * Ends last opened element
	 */
	public abstract void endElement();

	/**
	 * Clears the stack of elements
	 */
	public void clear();


	/**
	 * @return
	 */
	public abstract int depth();
	
	
}