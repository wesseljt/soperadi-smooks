/*******************************************************************************
 * Copyright (c) 2009 SOPERA GmbH
 * All rights reserved. 
 * This program and the accompanying materials are made available
 * under the terms of the GNU Lesser General Public License v 3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/
package org.sopera.di.smooks;

import java.io.IOException;

import org.milyn.SmooksException;
import org.xml.sax.SAXException;

/**
 * Basic interface for tSmooks component. Defines the most important methods for
 * this component.
 * 
 * @author Alexander
 * 
 */
public interface SmooksTransform {
	public void setConfigFileName(String mappingFileName);

	public void setInputFileName(String inputFileName);

	public void setOutputFileName(String outputFileName);

	public boolean runSmooksTransform() throws IOException, SAXException,
			SmooksException;

	public void setMappingResource(String targetID, String sourseName);

}
