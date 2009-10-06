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

package org.sopera.di.smooks;

import org.sopera.di.smooks.xpath.SXPathExpr;
import org.xml.sax.ContentHandler;

import com.google.inject.Guice;

/**
 * Build a {@link ContentHandler} filter based on the specified XPath-like expression.
 * Sample usage:
 * <code>
 * ContentHandler handler = filterBuilder.startWith(delegate).addXPathFilter(expr1).addXPathFilter(expr2).build();
 * </code>
 * 
 * @author zubairov
 */
public interface FilterBuilder {

	public static final FilterBuilder INSTANCE = Guice.createInjector(
			new ComponentModule()).getInstance(FilterBuilder.class);
	
	/**
	 * Set the first delegate {@link ContentHandler}
	 * @param delegate
	 * @return
	 */
	public FilterBuilder startWith(ContentHandler delegate);
	
	/**
	 * Fluent intermediate method to build filter
	 * 
	 * @param expr
	 * @return
	 */
	public FilterBuilder addXPathFilter(SXPathExpr expr);
	
	/**
	 * A fluent terminate method that will return a {@link ContentHandler}
	 * 
	 * @return
	 */
	public ContentHandler build();
	
}
