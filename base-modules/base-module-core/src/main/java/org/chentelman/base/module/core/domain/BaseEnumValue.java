package org.chentelman.base.module.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * A base representation of an base enum type.
 */
@Getter
@Setter
@AllArgsConstructor
public class BaseEnumValue implements BaseEnumType {
	private static final long serialVersionUID = 1L;

	private String code;
	private String name;
}



