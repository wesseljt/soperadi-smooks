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

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.sopera.di.smooks.xpath.PathElement;

/**
 * A single element in the current path stack
 * 
 * @author zubairov
 */
public class PathElementImpl implements PathElement {

	private QName name;
	
	private int position;
	
	private final Map<QName, Integer> childCount = new HashMap<QName, Integer>();

	/**
	 * @param name
	 * @param position
	 */
	public PathElementImpl(QName name, PathElement parent) {
		super();
		this.name = name;
		if (parent != null) {
			position = parent.newChild(name);
		} else {
			position = 1;
		}
	}


	/**
	 * This method is called every time new child is added
	 * this method should return a position of the
	 * new child in the child list of parent
	 * 
	 * @param childName
	 * @return
	 */
	public int newChild(QName childName) {
		Integer count = childCount.get(childName);
		if (count == null) {
			count = 1;
		} else {
			count = count.intValue() + 1;
		}
		childCount.put(childName, count);
		return count.intValue();
	}


	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PathElementImpl other = (PathElementImpl) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "/" + name + "[" + position + "]";
	}

	
}
