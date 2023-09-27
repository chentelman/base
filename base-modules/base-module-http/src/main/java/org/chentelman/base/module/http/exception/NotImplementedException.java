package org.chentelman.base.module.http.exception;

import org.chentelman.base.module.core.exception.BaseException;
import org.springframework.http.HttpStatus;

public class NotImplementedException extends BaseException {
	private static final long serialVersionUID = 1L;

	public NotImplementedException(String desc) {
		super(HttpStatus.NOT_IMPLEMENTED, desc);
	}
}



