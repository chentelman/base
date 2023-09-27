package org.chentelman.base.testing.exception;

/**
 * Base test exception.
 *
 * This exception is used in case there is an issue
 * with the execution of the test.
 */
public class BaseTestException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BaseTestException (String message) {
		super (message);
	}
}



