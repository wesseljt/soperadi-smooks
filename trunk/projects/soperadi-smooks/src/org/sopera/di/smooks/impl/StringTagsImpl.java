/*******************************************************************************
 * Copyright (c) 2009 SOPERA GmbH
 * All rights reserved. 
 * This program and the accompanying materials are made available
 * under the terms of the GNU Lesser General Public License v 3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 ******************************************************************************/

package org.sopera.di.smooks.impl;

import java.util.HashMap;

import org.sopera.di.smooks.StringTags;

/**
 * This class contains a data structure and methods to work with this structure.
 * The data structure consists of names of tags and corresponding values of
 * these tags.
 * 
 * @author soperadi-smooks
 * 
 */
public class StringTagsImpl implements StringTags {
	private HashMap<String, String> tags = new HashMap<String, String>();
	private boolean state = false;
	private boolean end = false;

	/**
	 * Shows if the end of data structure is reached.
	 * 
	 * @return boolean value, which shows if we have an end of data flow.
	 */
	public boolean isEnd() {
		return end;
	}

	/**
	 * 
	 * @param end
	 *            sign of the end of flow.
	 */
	public void setEnd(boolean end) {
		this.end = end;
	}

	/**
	 * This method finds the value of corresponding tag.
	 * 
	 * @param tagName
	 *            name of tag, which value is needed.
	 * @return value of corresponding tag.
	 */
	public String findTagValue(String tagName) {
		//System.out.println("read: " + tags.get(tagName));
		return tags.get(tagName);
	}

	/**
	 * Writing of data to the structure.
	 * 
	 * Associates the specified tag value with the specified name of tag in data
	 * structure. If the data structure previously contained a mapping for the
	 * tag name, the old value is replaced.
	 * 
	 * @param key
	 *            tag name with which the specified value is to be associated.
	 * @param res
	 *            value to be associated with the specified tag name.
	 */
	public void write(String key, String res) {
		this.tags.put(key, res);
		//System.out.println("write: " + key + "    " + res);
	}

	/**
	 * Start of writing session.
	 * 
	 * If reading session is active, then we wait for the end of it. After this
	 * we begin writing session.
	 */
	public synchronized void startWrite() {
		if (state) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * Start of reading session.
	 * 
	 * If writing session is active, then we wait for the end of it. After this
	 * we begin reading session.
	 */
	public synchronized void startRead() {
		if (!state) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * End of reading of data structure.
	 * 
	 * This method ends reading session, changes boolean variable, which is a
	 * state of reading/writing process and notify the writing session.
	 */
	public synchronized void endRead() {
		if (state) {
			state = false;
			notify();
		}
	}

	/**
	 * End of writing of data structure.
	 * 
	 * This method ends writing session, changes boolean variable, which is a
	 * state of reading/writing process and notify the writing session.
	 */
	public synchronized void endWrite() {
		if (!state) {
			state = true;
			notify();
		}
	}
}