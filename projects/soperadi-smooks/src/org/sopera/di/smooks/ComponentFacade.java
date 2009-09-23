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

import org.sopera.di.smooks.impl.StringTags;
import org.sopera.di.smooks.xpath.SAXLocation;

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

	/**
	 * Set the stream of Smooks mapping file (EDI-to-XML)
	 * 
	 * @param res
	 *            input stream of Smooks mapping file
	 */
	public void setMapping(InputStream res);

	/**
	 * Set the stream of EDI-massage file to transfer
	 * 
	 * @param res
	 *            input stream of EDI-file
	 */
	public void setEDI(InputStream res);

	/**
	 * Set the Path to determine the necessary data, and make location
	 * {@link SAXLocation} for preset in the reading flow constructor
	 * 
	 * @param loopPath
	 *            the path to the loop tag in output XML
	 */
	public void setXPath(String loopPath);

	/**
	 * Returns the current Path, for which the last writing sessions loop was
	 * started
	 * 
	 * @return
	 */
	public String getXPath();

	/**
	 * Ends the read session of current {@link StringTags} structure
	 */
	public void endRead();

	/**
	 * Starts the read session of current {@link StringTags} structure
	 */
	public void startRead();

	/**
	 * Initializes {@link StringTags} structure, create and run
	 * {@link EDIProcess} to start the write sessions loop
	 * 
	 */
	public void start();

	/**
	 * Starts the next read session if the write flow not ended
	 */
	public boolean next();

	/**
	 * Returns the value of the field for a given tag name. May be caused by any
	 * number of times during the reading session
	 * 
	 * @param tagName
	 *            name of tag in output XML
	 * @return result field value
	 */
	public String findTagValue(String tagName);

	/**
	 * Check if the current writing sessions loop already ended
	 * 
	 * @return
	 */
	public boolean isEndOfFlow();

}
