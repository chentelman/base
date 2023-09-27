package org.chentelman.base.module.core.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/**
 * Abstract class used to provide logging functionality to all components.
 */
public class BaseComponent {

	/**
	 * Common logger for use in subclasses.
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Do not allow base component to be instantiated
	 */
	protected BaseComponent () {

	}

	@PostConstruct
	private void init() {
		if (logger.isDebugEnabled()) {
			logger.debug("Loaded {}.", getClass().getName());
		}
	}

	@PreDestroy
	private void destroy() {
		if (logger.isDebugEnabled()) {
			logger.debug("Ready to destroy {}.", getClass().getName());
		}
	}
}



