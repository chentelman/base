package org.chentelman.base.module.core.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

/**
 * Enumeration for information levels for messages to be passed to the client.
 */
@Getter
public enum BaseInfoLevel {
	TRACE (32,   "trace"),
	DEBUG (16,   "debug"),
	INFO  ( 8,    "info"),
	WARN  ( 4, "warning"),
	ERROR ( 2,   "error"),
	FATAL ( 1,   "fatal");

	private final int    code;
	private final String text;

	/**
	 * Instantiate BaseInfoLevel
	 */
	private BaseInfoLevel (int code, String text) {
		this.code = code;
		this.text = text;
	}

	/**
	 * Define a json value method to convert the enumeration to a json value.
	 */
	@Override
	@JsonValue
	public String toString () {
		return this.text;
	}

	/**
	 * define a json creator to convert the json value back to an enumeration.
	 */
	@JsonCreator
	public static BaseInfoLevel getEnumFromText(String text) {
		for (BaseInfoLevel l : BaseInfoLevel.values()) {
			if (l.text.equals(text)) {
				return l;
			}
		}
		return null;
	}

}



