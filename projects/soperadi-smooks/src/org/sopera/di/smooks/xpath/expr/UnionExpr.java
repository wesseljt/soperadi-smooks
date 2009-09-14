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

package org.sopera.di.smooks.xpath.expr;

import org.sopera.di.smooks.xpath.SAXLocation;
import org.sopera.di.smooks.xpath.SXPathExpr;

/**
 * {@link SXPathExpr} that represents a union of two other {@link SXPathExpr}
 * 
 * @author zubairov
 */
public class UnionExpr implements SXPathExpr {

	private SXPathExpr left;
	
	private SXPathExpr right;
	
	/**
	 * Construt a Uniton expression
	 * 
	 * @param left
	 * @param right
	 */
	public UnionExpr(SXPathExpr left, SXPathExpr right) {
		super();
		this.left = left;
		this.right = right;
	}


	/**
	 * @{inheritDoc}
	 */
	public boolean match(SAXLocation location) {
		return left.match(location) || right.match(location);
	}

}
