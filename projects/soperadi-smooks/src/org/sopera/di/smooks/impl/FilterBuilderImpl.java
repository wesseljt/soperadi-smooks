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

package org.sopera.di.smooks.impl;

import org.sopera.di.smooks.FilterBuilder;
import org.sopera.di.smooks.xpath.SXPathExpr;
import org.xml.sax.ContentHandler;

/**
 * Builder for filtered {@link ContentHandler} delegates
 * 
 * @author zubairov
 */
public class FilterBuilderImpl implements FilterBuilder {

	private ContentHandler handler = null;

	/**
	 * @{inheritDoc
	 */
	public FilterBuilder addXPathFilter(SXPathExpr expr) {
		if (handler == null) {
			throw new RuntimeException("Internal handler is null, please call"
					+ " method startWith(handler) before");
		}
		handler = new FilteredDelegateHandler(handler, expr);
		return this;
	}

	/**
	 * @{inheritDoc
	 */
	public ContentHandler build() {
		return handler;
	}

	/**
	 * @{inheritDoc
	 */
	public FilterBuilder startWith(ContentHandler delegate) {
		handler = delegate;
		return this;
	}

}
