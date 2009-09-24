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

import java.util.Stack;

import javax.xml.namespace.QName;

import org.sopera.di.smooks.xpath.PathElement;
import org.sopera.di.smooks.xpath.SAXLocation;

/**
 * Object that stores current position in XML document
 * 
 * @author zubairov
 */
public class SAXLocationImpl implements SAXLocation {

	private Stack<PathElementImpl> stack = new Stack<PathElementImpl>();

	public Stack<PathElementImpl> getStack() {

		return stack;
	}
	public void setStack(Stack<PathElementImpl> stack) {
		this.stack = stack;
	}

	public void clear() {
		while (!this.stack.empty())
			this.stack.pop();
	}

	/**
	 * {@inheritDoc}
	 */
	public void startElement(QName name) {
		PathElementImpl parent = null;
		if (stack.size() > 0) {
			parent = stack.peek();
		}
		stack.add(new PathElementImpl(name, parent));
	}

	/**
	 * {@inheritDoc}
	 */
	public void endElement() {
		stack.pop();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (PathElement element : stack) {
			builder.append(element.toString());
		}
		return builder.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	public int depth() {
		return stack.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		SAXLocationImpl other = (SAXLocationImpl) obj;
		if (getStack().size() != other.getStack().size())
			return false;
		for (int i = 0; i < getStack().size(); i++) {
			if (!getStack().elementAt(i).equals(other.getStack().elementAt(i)))
				return false;
		}
		return true;
	}

}
