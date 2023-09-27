package org.chentelman.base.module.core.data;

import org.chentelman.base.module.core.domain.BaseEntity;

/**
 * A common super interface for all base dao interfaces.
 * This is used to match any of the supported dao's for autowire purposes
 *
 * As this requires the generic parameters to be set, a dummy get null
 * method is provided
 *
 * @param <E> the type of the entity to persist
 * @param <I> the type of the key of the entity
 */
public interface BaseCommonDao<E extends BaseEntity<I>, I> {

	/**
	 * Dummy method to justify the use of generics to java compiler
	 *
	 * @return null
	 */
	public default Class<E> getNull () {
		return null;
	}
}



