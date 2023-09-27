package org.chentelman.base.module.http.exception;

import org.chentelman.base.module.core.exception.BaseException;
import org.springframework.http.HttpStatus;

public class SuccessStatus extends BaseException {
	private static final long serialVersionUID = 1L;

	public SuccessStatus() {
		super(HttpStatus.OK, null);
	}
}



