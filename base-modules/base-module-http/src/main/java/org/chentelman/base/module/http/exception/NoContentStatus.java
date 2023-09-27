package org.chentelman.base.module.http.exception;

import org.chentelman.base.module.core.exception.BaseException;
import org.springframework.http.HttpStatus;

public class NoContentStatus extends BaseException {
	private static final long serialVersionUID = 1L;

	public NoContentStatus() {
		super(HttpStatus.NO_CONTENT, null);
	}
}



