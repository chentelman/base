package org.chentelman.base.module.http.exception;

import org.chentelman.base.module.core.exception.BaseException;
import org.springframework.http.HttpStatus;

public class ConflictException extends BaseException {
	private static final long serialVersionUID = 1L;

	public ConflictException(String desc) {
		super(HttpStatus.CONFLICT, desc);
	}
}



