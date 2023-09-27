package org.chentelman.base.module.core.exception;

import lombok.Value;

/**
 * Base validation failure description
 *
 * Contains information for a single validation error
 */
@Value
public class BaseValidationFailure {
	private final String name;
	private final String field;
	private final String value;
	private final String error;

	public BaseValidationFailure (String name, String field, String value, String error) {
		this.name  = name;
		this.field = field;
		this.value = value;
		this.error = error;
	}

	public BaseValidationFailure (String name, String field, Object value, String error) {
		this.name  = name;
		this.field = field;
		this.value = value == null ? null : value.toString();
		this.error = error;
	}

	public BaseValidationFailure (Class<?> clazz, String field, Object value, String error) {
		this.name  = clazz == null ? null : clazz.getSimpleName();
		this.field = field;
		this.value = value == null ? null : value.toString();
		this.error = error;
	}

}



