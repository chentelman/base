package org.chentelman.base.module.core.service;

import java.util.List;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.springframework.data.domain.Pageable;

/**
 * BasePartitionedAccessService
 *
 * This class defines the base read operations for {@link BasePartitionedEntity}
 *
 * @param <E> the type of the entity to service
 * @param <I> the type of the key of the entity
 * @param <P> the type of the partition key of the entity
 */
public interface BasePartitionedAccessService<E extends BasePartitionedEntity<I, P>, I, P> extends BaseAccessService<E, I> {

	/**
	 * Retrieve all entities by id.
	 * In partitioned entities multiple entities with the same id can exist
	 * in different partitions. This method will return all entities
	 * that share the same id.
	 *
	 * @param id The internal identifier of the entity to retrieve.
	 * @return The list of entities with that id.
	 */
	public List<E> readAllById (I id);

	/**
	 * Retrieve a page of all entities by id.
	 * In partitioned daos multiple entities with the same id can exist
	 * in different partitions. This method will return a page from
	 * all entities that share the same id.
	 *
	 * @param id The internal identifier of the entity to retrieve.
	 * @return The list of entities with that id.
	 */
	public List<E> readAllById (Pageable page, I id);

	/**
	 * Retrieve all entities for a specific partition key.
	 *
	 * @param partitionKey The partition key of the entity type.
	 * @return All entities.
	 */
	public List<E> readAll (P partitionKey);

	/**
	 * Retrieve a page of all entities for a specific partition key.
	 *
	 * @param pageable The number of items and page to retrieve.
	 * @param partitionKey The partition key of the entity type.
	 * @return The page of entities.
	 */
	public List<E> readAll (Pageable pageable, P partitionKey);

	/**
	 * Retrieve the number of all entities for a specific partition key.
	 *
	 * @param partitionKey The partition key of the entity type.
	 * @return count of all entities.
	 */
	public long count (P partitionKey);

	/**
	 * Retrieve an entity by id for a specific partition key.
	 *
	 * @param id The internal identifier of the entity to retrieve.
	 * @param partitionKey The partition key of the entity type.
	 * @return The entity with the given id.
	 */
	public E read (I id, P partitionKey);
}



