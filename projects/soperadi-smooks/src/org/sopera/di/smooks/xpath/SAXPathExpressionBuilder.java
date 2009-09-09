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

/**
 * Builder that is responsible for building Stirngs
 * to the SXPath expressions which would be suitable
 * for evaluation
 * 
 * @author zubairov
 */
public interface SAXPathExpressionBuilder {

	/**
	 * Builds expression
	 * 
	 * @param xPath
	 * @return
	 */
	public SXPathExpr buildExpression(String xPath);
	
}
