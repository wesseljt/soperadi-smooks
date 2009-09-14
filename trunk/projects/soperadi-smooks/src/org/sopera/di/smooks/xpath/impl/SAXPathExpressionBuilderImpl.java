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

package org.sopera.di.smooks.xpath.impl;

import java.util.List;
import java.util.Map;

import org.jaxen.JaxenHandler;
import org.jaxen.expr.Expr;
import org.jaxen.expr.LocationPath;
import org.jaxen.expr.NameStep;
import org.jaxen.expr.Step;
import org.jaxen.expr.UnionExpr;
import org.jaxen.expr.XPathExpr;
import org.jaxen.saxpath.Axis;
import org.jaxen.saxpath.SAXPathException;
import org.jaxen.saxpath.XPathReader;
import org.sopera.di.smooks.xpath.SAXPathExpressionBuilder;
import org.sopera.di.smooks.xpath.SXPathExpr;

/**
 * Default implementation
 * 
 * @author zubairov
 */
public class SAXPathExpressionBuilderImpl implements SAXPathExpressionBuilder {

	private final XPathReader reader = new org.jaxen.saxpath.base.XPathReader();

	public Map<String, String> namespaces;
	
	/**
	 * {@inheritDoc}
	 */
	public SXPathExpr buildExpression(String xPath) throws SAXPathException {
		JaxenHandler handler = new JaxenHandler();
		reader.setXPathHandler(handler);
		reader.parse(xPath);
		XPathExpr xpath = handler.getXPathExpr();
		return visitExpr(xpath.getRootExpr());
	}

	/**
	 * Start visiting
	 * 
	 * @param expr
	 */
	public SXPathExpr visitExpr(Expr expr) {
		if (expr instanceof UnionExpr) {
			UnionExpr unionExpr = (UnionExpr) expr;
			SXPathExpr left = visitExpr(unionExpr.getLHS());
			SXPathExpr right = visitExpr(unionExpr.getRHS());
			return new org.sopera.di.smooks.xpath.expr.UnionExpr(left, right);
		}
		if (expr instanceof LocationPath) {
			LocationPath path = (LocationPath) expr;
			return visitPath(path);
		}
		throw new RuntimeException("Unuspported type of expression " + expr.getClass() + ":" + expr);
	}

	/**
	 * @param path
	 */
	@SuppressWarnings("unchecked")
	private SXPathExpr visitPath(LocationPath path) {
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
		return null;
	}

	/**
	 * @param namespaces the namespaces to set
	 */
	public void setNamespacesMap(Map<String, String> namespaces) {
		this.namespaces = namespaces;
	}
	
}
