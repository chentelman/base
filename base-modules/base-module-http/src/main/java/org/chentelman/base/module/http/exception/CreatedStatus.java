package org.chentelman.base.module.http.exception;

import org.chentelman.base.module.core.exception.BaseException;
import org.springframework.http.HttpStatus;

public class CreatedStatus extends BaseException {
	private static final long serialVersionUID = 1L;

	public CreatedStatus() {
		super(HttpStatus.CREATED, null);
	}
}



