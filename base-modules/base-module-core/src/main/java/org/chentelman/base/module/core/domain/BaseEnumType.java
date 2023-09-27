package org.chentelman.base.module.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * BaseEnumType definition
 *
 * This is a generic interface to be used on enumerations.
 * it will require a code and name to be provided for each entry
 * that can be used to generate a {@link BaseEnumValue}
 *
 * Interface extends the base entity so it can be used
 * as an in memory read only storage.
 */
public interface BaseEnumType extends BaseEntity<String> {

	public String getCode();
	public String getName();

	@JsonIgnore
	public default String getId () {
		return getCode ();
	}

	public default BaseEnumValue getEnumValue () {
		return new BaseEnumValue (getCode(), getName());
	}
}



