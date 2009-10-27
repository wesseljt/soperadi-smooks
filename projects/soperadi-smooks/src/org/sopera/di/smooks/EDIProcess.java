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
import java.util.HashMap;

import org.sopera.di.smooks.xpath.SAXLocation;

import com.google.inject.Guice;

/**
 * Basic interface for organize the thread, with uses the smooks to transform
 * the data from EDI-massage to the SAX events flow
 * 
 * @author soperadi-smooks
 * 
 */
public interface EDIProcess extends Runnable {
	/**
	 * Singleton instance
	 */
	public static final EDIProcess INSTANCE = Guice.createInjector(
			new ComponentModule()).getInstance(EDIProcess.class);

	public String getXPath();

	public void setRes(StringTags res);

	public void setEdi(InputStream edi);

	public void setMapping(InputStream mapping);

	public void setXPaths(HashMap<String, SAXLocation> paths);

}
