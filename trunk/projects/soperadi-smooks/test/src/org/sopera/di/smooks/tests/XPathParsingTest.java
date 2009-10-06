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

import java.util.List;

import junit.framework.TestCase;

import org.jaxen.JaxenHandler;
import org.jaxen.expr.Expr;
import org.jaxen.expr.LocationPath;
import org.jaxen.expr.NameStep;
import org.jaxen.expr.Step;
import org.jaxen.expr.UnionExpr;
import org.jaxen.expr.XPathExpr;
import org.jaxen.saxpath.Axis;
import org.jaxen.saxpath.XPathReader;
import org.junit.Test;

/**
 * Test for parsing XPath using SAXPath
 * 
 * @author zubairov
 */
public class XPathParsingTest extends ProjectTest {

	public XPathParsingTest(String name) {
		super(name);
	}
	
	public void testXPath() throws Exception {
		XPathReader reader = new org.jaxen.saxpath.base.XPathReader();
		JaxenHandler handler = new JaxenHandler();
		reader.setXPathHandler(handler);
		reader.parse("/ns:Order/ns:order-items[8]/@ns:item | //ns:book");
		XPathExpr xpath = handler.getXPathExpr();
		visitExpr(xpath.getRootExpr());
	}
	
	/**
	 * Start visiting
	 * 
	 * @param expr
	 */
	public boolean visitExpr(Expr expr) {
		if (expr instanceof UnionExpr) {
			UnionExpr unionExpr = (UnionExpr) expr;
			boolean left = visitExpr(unionExpr.getLHS());
			boolean right = visitExpr(unionExpr.getRHS());
			return left || right;
		}
		if (expr instanceof LocationPath) {
			LocationPath path = (LocationPath) expr;
			return visitPath(path);
		}
		return false;
	}

	/**
	 * @param path
	 */
	@SuppressWarnings("unchecked")
	private boolean visitPath(LocationPath path) {
		List<Step> steps = path.getSteps();
		System.err.println("Absolute: " + path.isAbsolute());
		for (Step step : steps) {
			if (step instanceof NameStep) {
				NameStep nameStep = (NameStep) step;
				System.err.println("Selecting "
						+ Axis.lookup(step.getAxis()) + " with "
						+ nameStep.getPrefix() + ":"
						+ nameStep.getLocalName());
			} else {
				System.err.println("UnknownType: " + step.getClass() + " - " + step);
			}
		}
		return true;
	}
	
}
