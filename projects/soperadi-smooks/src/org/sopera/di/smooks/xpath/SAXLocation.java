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

/**
 * @author zubairov
 * 
 */
public interface SAXLocation {

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