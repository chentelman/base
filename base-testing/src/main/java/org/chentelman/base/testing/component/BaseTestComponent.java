package org.chentelman.base.testing.component;

import org.chentelman.base.module.core.component.BaseComponent;

/**
 * Abstract class used to provide logging functionality,
 * core test functionality and clear capabilities to all test components.
 */
public abstract class BaseTestComponent extends BaseComponent {

	/**
	 * Do not allow base test component to be instantiated
	 */
	protected BaseTestComponent () {
	}

	/**
	 * method to clear the status of the test component.
	 *
	 * It is used reset components between different test cases.
	 */
	public void clear () {
		// do nothing
	}
}



