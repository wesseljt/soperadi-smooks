package org.sopera.di.smooks;

import com.google.inject.Guice;

/**
 * Basic interface for organize the MAP structure, synchronized the input and
 * output flows
 * 
 * @author soperadi-smooks
 * 
 */
public interface StringTags {

	/**
	 * Singleton instance
	 */
	public static final StringTags INSTANCE = Guice.createInjector(
			new ComponentModule()).getInstance(StringTags.class);
	
	public void startRead();

	public void startWrite();

	public void endRead();

	public void endWrite();

	public boolean isEnd();

	public void setEnd(boolean end);

	public void write(String key, String res);

	public String findTagValue(String tagName);

}
