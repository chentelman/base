package org.chentelman.base.module.core.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Base validation exception
 *
 * Extends the base exception to additionally provide the validation errors
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseValidationException extends BaseException {
	private static final long serialVersionUID = 1L;
	private static final String ERROR = "Validation errors";

	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	private final List<BaseValidationFailure> validations;

	/**
	 * Make a base validation exception with no validations
	 */
	public BaseValidationException () {
		this (null);
	}

	/**
	 * Make a base validation exception with a single validation failure
	 */
	public BaseValidationException (String name, String field, String value, String error) {
		this (List.of(new BaseValidationFailure(name, field, value, error)));
	}

	/**
	 * Make a base validation exception with list of validation failures
	 */
	public BaseValidationException (List<BaseValidationFailure> failures) {
		super (HttpStatus.BAD_REQUEST, ERROR);

		validations = failures;
	}

}



