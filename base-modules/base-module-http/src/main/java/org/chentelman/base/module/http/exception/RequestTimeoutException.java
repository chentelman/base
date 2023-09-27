package org.chentelman.base.module.http.exception;

import org.chentelman.base.module.core.exception.BaseException;
import org.springframework.http.HttpStatus;

public class RequestTimeoutException extends BaseException {
	private static final long serialVersionUID = 1L;

	public RequestTimeoutException(String desc) {
		super(HttpStatus.REQUEST_TIMEOUT, desc);
	}
}



