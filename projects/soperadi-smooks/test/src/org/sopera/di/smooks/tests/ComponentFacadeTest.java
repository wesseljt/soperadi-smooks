package org.sopera.di.smooks.tests;

import java.io.InputStream;

import org.junit.Test;

import junit.framework.*;

public class ComponentFacadeTest extends TestCase {
	public ComponentFacadeTest(String name) {
		super(name);
	}

	@Test
	public void testSetEDI() {
		org.sopera.di.smooks.ComponentFacade inputFlow = org.sopera.di.smooks.ComponentFacade.INSTANCE;
		assertNotNull("Can't create ComponentFacade instance", inputFlow);
		InputStream edi = getClass().getResourceAsStream("/smooks.edi");
		assertNotNull("Can't find EDI file for test", edi);
		inputFlow.setEDI(edi);
	}

	@Test
	public void testSetMapping() {
		org.sopera.di.smooks.ComponentFacade inputFlow = org.sopera.di.smooks.ComponentFacade.INSTANCE;
		assertNotNull("Can't create ComponentFacade instance", inputFlow);
		InputStream mapping = getClass().getResourceAsStream(
				"/smooks-mapping.xml");
		assertNotNull("Can't find mapping file for test", mapping);
		inputFlow.setMapping(mapping);
	}

}
