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
			new EDIProcessModule()).getInstance(EDIProcess.class);

	public String getXPath();

	public void setRes(StringTags res);

	public void setEdi(InputStream edi);

	public void setMapping(InputStream mapping);

	public void setXPaths(HashMap<String, SAXLocation> paths);

}
