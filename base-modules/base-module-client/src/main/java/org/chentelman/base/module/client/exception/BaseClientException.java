package org.chentelman.base.module.client.exception;

import org.chentelman.base.module.core.exception.BaseException;
import org.springframework.http.HttpStatus;

/**
 * Base exception for client errors
 */
public class BaseClientException extends BaseException {
	private static final long serialVersionUID = 1L;

	/**
	 * Base client exceptions cannot be instantiated directly
	 *
	 * @param desc a description for the exception
	 */
	private BaseClientException (String desc) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, desc);
	}

	/**
	 * Instantiate a new exception with a simple description
	 *
	 * @param desc a description for the exception
	 * @return an instance of the base client exception
	 */
	public static BaseClientException of (String desc) {
		return new BaseClientException (desc);
	}

	/**
	 * Instantiate a new exception with a description and the root exception
	 *
	 * @param e the caught exception that triggered the generation of the base client exception.
	 * @param desc a description for the exception
	 * @return an instance of the base client exception
	 */
	public static BaseClientException of (String desc, Throwable e) {
		BaseClientException exception = of (desc);

		exception.addSuppressed(e);

		return exception;
	}
}



