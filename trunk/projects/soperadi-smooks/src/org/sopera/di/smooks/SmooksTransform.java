package org.sopera.di.smooks;

import java.io.IOException;

import org.milyn.SmooksException;
import org.xml.sax.SAXException;

public interface SmooksTransform {
	public void setMappingFileName(String mappingFileName);

	public void setInputFileName(String inputFileName);

	public void setOutputFileName(String outputFileName);

	public boolean runSmooksTransform() throws IOException, SAXException,
			SmooksException;

	public void setMappingResource(String targetID, String sourseName);

}
