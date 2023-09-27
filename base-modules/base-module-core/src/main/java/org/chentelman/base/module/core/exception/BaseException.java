package org.chentelman.base.module.core.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Base exception class
 *
 * Provides the default exception field required by all base exceptions.
 * Defines the json mapping to be able to convert base exceptions to json values.
 */
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(
	fieldVisibility = Visibility.NONE,
	setterVisibility = Visibility.NONE,
	getterVisibility = Visibility.NONE,
	isGetterVisibility = Visibility.NONE,
	creatorVisibility = Visibility.NONE
)
public class BaseException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	private final HttpStatus status;

	@JsonProperty private final int    code;
	@JsonProperty private final String text;
	@JsonProperty private final String desc;

	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	private final List<BaseInfo> info;

	/**
	 * Instantiate a new base exception
	 *
	 * @param status an http status to associate this status with
	 * @param desc a description for the error encountered
	 */
	public BaseException (HttpStatus status, String desc) {
		this.code = status.value();
		this.text = status.name();
		this.desc = desc;
		this.info = new ArrayList<>();

		this.status = status;
	}

}



