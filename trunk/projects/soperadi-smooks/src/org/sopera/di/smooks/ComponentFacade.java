/*******************************************************************************
 * Copyright (c) 2009 SOPERA GmbH
 * All rights reserved. 
 * This program and the accompanying materials are made available
 * under the terms of the GNU Lesser General Public License v 3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package org.sopera.di.smooks;

import org.sopera.di.smooks.impl.StringTagsImpl;
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
	 * Set the stream file of Smooks mapping (EDI-to-XML)
	 * 
	 * @param res
	 *            URI or Path of Smooks mapping file
	 */
	public void setMapping(String res);

	/**
	 * Set the stream file of EDI-massage to transfer
	 * 
	 * @param res
	 *            URI or Path of input stream EDI-file
	 */
	public void setEDI(String res);

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
	 * Ends the read session of current {@link StringTagsImpl} structure
	 */
	public void endRead();

	/**
	 * Starts the read session of current {@link StringTagsImpl} structure
	 */
	public void startRead();

	/**
	 * Initializes {@link StringTagsImpl} structure, create and run
	 * {@link EDIProcessImpl} to start the write sessions loop
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
