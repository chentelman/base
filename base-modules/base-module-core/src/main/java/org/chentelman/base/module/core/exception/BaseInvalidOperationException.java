package org.chentelman.base.module.core.exception;

import org.springframework.http.HttpStatus;

/**
 * Base exception for invalid operations
 */
public class BaseInvalidOperationException extends BaseException {
	private static final long serialVersionUID = 1L;

	/**
	 * Base invalid update exceptions cannot be instantiated directly
	 *
	 * @param message a description for the exception
	 */
	private BaseInvalidOperationException (String message) {
		super(HttpStatus.BAD_REQUEST, message);
	}

	/**
	 * Instantiate a new exception with custom message
	 *
	 * @param message a description of the encouterned error
	 * @return an instance of the exception
	 */
	public static BaseInvalidOperationException of (String message) {
		return new BaseInvalidOperationException (message);
	}
}



