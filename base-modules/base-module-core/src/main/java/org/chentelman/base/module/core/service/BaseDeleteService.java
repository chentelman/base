package org.chentelman.base.module.core.service;

import org.chentelman.base.module.core.domain.BaseEntity;

/**
 * BaseDeleteService
 *
 * This class defines the base delete operations for {@link BaseEntity}
 *
 * @param <E> the type of the entity to service
 * @param <I> the type of the key of the entity
 */
public interface BaseDeleteService<E extends BaseEntity<I>, I> extends BaseCommonService<E, I> {

	/**
	 * Removes an entity.
	 *
	 * @param entity The entity to remove.
	 */
	public void delete (E entity);

	/**
	 * Removes an entity.
	 *
	 * @param entity The id of the entity to remove.
	 */
	public void delete (I id);
}



