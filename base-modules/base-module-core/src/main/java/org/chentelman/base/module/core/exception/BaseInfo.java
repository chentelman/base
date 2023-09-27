package org.chentelman.base.module.core.exception;

import lombok.Value;

/**
 * Base information to be passed along an base exception to the client.
 */
@Value
public class BaseInfo {
	private final BaseInfoLevel type;
	private final String text;
}



