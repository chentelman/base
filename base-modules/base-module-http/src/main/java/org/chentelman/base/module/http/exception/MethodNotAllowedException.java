package org.chentelman.base.module.http.exception;

import org.chentelman.base.module.core.exception.BaseException;
import org.springframework.http.HttpStatus;

public class MethodNotAllowedException extends BaseException {
	private static final long serialVersionUID = 1L;

	public MethodNotAllowedException(String desc) {
		super(HttpStatus.METHOD_NOT_ALLOWED, desc);
	}
}



