package org.chentelman.base.module.core.service;

import org.chentelman.base.module.core.domain.BaseEntity;

/**
 * BaseCreateService
 *
 * This class defines the base create operations for {@link BaseEntity}
 *
 * @param <E> the type of the entity to service
 * @param <I> the type of the key of the entity
 */
public interface BaseCreateService<E extends BaseEntity<I>, I> extends BaseCommonService<E, I> {

	/**
	 * Create a new entity.
	 *
	 * @param id The entity to be created.
	 * @return The created entity.
	 */
	public E create (E entity);
}



