package org.chentelman.base.module.core.data;

import org.chentelman.base.module.core.domain.BaseEntity;

/**
 * BaseUpdateDao
 *
 * This class defines the base operations for updating a {@link BaseEntity} to a storage
 *
 * @param <E> the type of the entity to persist
 * @param <I> the type of the key of the entity
 */
public interface BaseUpdateDao<E extends BaseEntity<I>, I> extends BaseCommonDao<E, I> {

	/**
	 * Updates the state of an object.
	 *
	 * @param object The object state to merge.
	 * @return E The persistent state of the saved object.
	 */
	public E update (E object);
}



