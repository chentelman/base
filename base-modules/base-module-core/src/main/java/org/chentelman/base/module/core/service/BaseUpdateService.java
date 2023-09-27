package org.chentelman.base.module.core.service;

import org.chentelman.base.module.core.domain.BaseEntity;

/**
 * BaseUpdateService
 *
 * This class defines the base update operations for {@link BaseEntity}
 *
 * @param <E> the type of the entity to service
 * @param <I> the type of the key of the entity
 */
public interface BaseUpdateService<E extends BaseEntity<I>, I> extends BaseCommonService<E, I> {

	/**
	 * Saves the state of an object.
	 *
	 * @param id The id of the entity to update
	 * @param entity The object state to update.
	 */
	public void update (I id, E entity);
}



