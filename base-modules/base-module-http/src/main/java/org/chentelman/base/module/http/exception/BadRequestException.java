package org.chentelman.base.module.http.exception;

import org.chentelman.base.module.core.exception.BaseException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {
	private static final long serialVersionUID = 1L;

	public BadRequestException(String desc) {
		super(HttpStatus.BAD_REQUEST, desc);
	}
}



