package org.sopera.di.smooks;

import org.sopera.di.smooks.impl.EDIProcessImpl;
import org.sopera.di.smooks.xpath.SAXLocation;
import org.sopera.di.smooks.xpath.impl.SAXLocationImpl;

import com.google.inject.AbstractModule;

public class EDIProcessModule extends AbstractModule {
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void configure() {
		bind(EDIProcess.class).to(EDIProcessImpl.class);
		bind(SAXLocation.class).to(SAXLocationImpl.class);
	}

}
