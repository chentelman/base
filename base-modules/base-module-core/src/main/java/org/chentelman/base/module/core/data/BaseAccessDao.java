package org.chentelman.base.module.core.data;

import java.util.Optional;

import org.chentelman.base.module.core.domain.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * BaseAccessDao
 *
 * This class defines the base operations for accessing a {@link BaseEntity} from a storage
 *
 * @param <E> the type of the entity to persist
 * @param <I> the type of the key of the entity
 */
public interface BaseAccessDao<E extends BaseEntity<I>, I> extends BaseCommonDao<E, I> {

	/**
	 * Retrieve all entities
	 *
	 * @return All entities.
	 */
	public Iterable<E> findAll ();

	/**
	 * Retrieve a page of all entities
	 *
	 * @param pageable The number of items and page to retrieve.
	 * @return The page of entities.
	 */
	public Page<E> findAll (Pageable pageable);

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
	 * @return The optional entity with the given id.
	 */
	public Optional<E> findById (I id);
}



