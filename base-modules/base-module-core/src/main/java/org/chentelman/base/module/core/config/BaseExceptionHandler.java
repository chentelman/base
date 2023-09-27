package org.chentelman.base.module.core.config;

import org.chentelman.base.module.core.component.BaseComponent;
import org.chentelman.base.module.core.exception.BaseException;
import org.springframework.http.ResponseEntity;

/**
 * Define a base exception handler. The handler it self does not provide any exception definitions
 * and its purpose is to provide logging to all exception handlers through the base component
 * as well as provide a generic way to convert a base exception to a response entity.
 */
public class BaseExceptionHandler extends BaseComponent {

	protected BaseExceptionHandler () {
		// disable BaseExceptionHandler instantiation
	}

	/**
	 * Wrap the generic exception in a response entity to provide the exception status
	 * as an HttpStatus for the response
	 */
	protected <T extends BaseException> ResponseEntity<T> generateResponse (final T ex) {
		return new ResponseEntity<> (
			ex,
			ex.getStatus()
		);
	}
}



