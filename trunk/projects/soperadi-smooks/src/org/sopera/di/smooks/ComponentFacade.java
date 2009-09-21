/*******************************************************************************
 * Copyright (c) 2009 SOPERA GmbH
 * All rights reserved. 
 * This program and the accompanying materials are made available
 * under the terms of the GNU Lesser General Public License v 3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package org.sopera.di.smooks;

import java.io.InputStream;

import com.google.inject.Guice;

/**
 * Component facade for interaction with Talend generated job.
 * 
 * This interface will be a primary facade that will be used by Talend-Smooks
 * jobs at runtime.
 * 
 * @author zubairov
 * 
 */
public interface ComponentFacade {

	/**
	 * Singleton instance
	 */
	public static final ComponentFacade INSTANCE = Guice.createInjector(
			new ComponentModule()).getInstance(ComponentFacade.class);
	
	public void setMapping(InputStream res);
	
	public void setEDI(InputStream res);
	
	public void setXPath(String loopXPath);
	
	public String getXPath();
	
	public void endRead();
	
	public void startRead();
	
	public void start();
	
	public boolean next();
	
	public String  findTagValue(String tagName);
	
	public boolean isEndOfFlow();

}
