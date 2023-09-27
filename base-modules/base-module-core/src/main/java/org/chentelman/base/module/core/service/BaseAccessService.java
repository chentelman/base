package org.chentelman.base.module.core.service;

import java.util.List;

import org.chentelman.base.module.core.domain.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * BaseAccessService
 *
 * This class defines the base read operations for {@link BaseEntity}
 *
 * @param <E> the type of the entity to service
 * @param <I> the type of the key of the entity
 */
public interface BaseAccessService<E extends BaseEntity<I>, I> extends BaseCommonService<E, I> {

	/**
	 * Retrieve all entities
	 *
	 * @return All entities.
	 */
	public List<E> readAll ();

	/**
	 * Retrieve a page of all entities
	 *
	 * @param pageable The number of items and page to retrieve.
	 * @return The page of entities.
	 */
	public Page<E> readAll (Pageable pageable);

	/**
	 * Retrieve the number of all entities
	 *
	 * @return count of all entities.
	 */
	public long count ();

	/**
	 * Retrieve an entity by id.
	 *
	 * @param id The internal identifier of the entity to retrieve.
	 * @return The entity with the given id.
	 */
	public E read (I id);
}



