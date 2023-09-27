package org.chentelman.base.testing.service;

import org.chentelman.base.testing.component.BaseTestComponent;
import org.springframework.beans.factory.ListableBeanFactory;

import jakarta.annotation.PostConstruct;

/**
 * A base discovery service for identifying beans of a certain type,
 * and registering it to the class.
 */
public abstract class BaseDiscoveryService<T extends BaseListable> extends BaseTestComponent {
	// spring dependency to discover the beans
	protected final ListableBeanFactory listableBeanFactory;
	protected final Class<T>            listableClass;

	/**
	 * object initialisation is set to protected to force a different class
	 * to be created for each different listable that needs to be discovered.
	 *
	 * @param listableBeanFactory autowired dependency to discover the required beans.
	 * @param listableClass the class to be used for the discovery.
	 */
	protected BaseDiscoveryService (ListableBeanFactory listableBeanFactory, Class<T> listableClass) {
		this.listableBeanFactory = listableBeanFactory;
		this.listableClass       = listableClass;
	}

	/**
	 * identification of the listables occurs during the objects post construction.
	 */
	@PostConstruct
	private void discoverTestServices () {
		// get all discoverable beans
		var beans = listableBeanFactory.getBeansOfType(listableClass, false, false);
		logger.info("looking {} implementations", listableClass.getName());

		// process all discoverable beans
		for (T discoverable : beans.values()) {
			logger.info("identified discoverable implementation {}", discoverable.getClass().getName());
			register (discoverable);
		}
	}

	/**
	 * Method to register the listable
	 *
	 * @param discoverable identified bean to be registered
	 */
	protected abstract void register (T discoverable);

}



