/*******************************************************************************
 * Copyright (c) 2009 SOPERA GmbH
 * All rights reserved. 
 * This program and the accompanying materials are made available
 * under the terms of the GNU Lesser General Public License v 3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/

package org.sopera.di.smooks.impl;

import java.io.InputStream;
import java.util.List;

import org.sopera.di.smooks.ComponentFacade;
import org.talend.core.model.metadata.IMetadataColumn;

/**
 * Default implementation of the {@link ComponentFacade}
 * 
 * @author zubairov
 */
public class ComponentFacadeImpl implements ComponentFacade {
	
	private List<IMetadataColumn> schema;
	/*
	 * state
	 * N - null
	 * P - prepared
	 * R - ready
	 * E - end
	 * */
	private char state = 'N'; 
	

	public List<IMetadataColumn> process() {
		if (getState() == 'R')
		   return schema;
		else return null;
	}

	public boolean setEDI(InputStream res) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean setMapping(InputStream res) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean setXPath(String loopXPath) {
		// TODO Auto-generated method stub
		return true;
	}

	public List<IMetadataColumn> getSchema() {
		return schema;
	}

	public void setSchema(List<IMetadataColumn> schema) {
		this.schema = schema;
	}
	
	public char getState() {
		return state;
	}

	public void setState(char state) {
		this.state = state;
	}
}
