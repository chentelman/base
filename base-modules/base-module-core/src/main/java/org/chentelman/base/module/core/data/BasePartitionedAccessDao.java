package org.chentelman.base.module.core.data;

import java.util.Optional;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * BasePartitionedAccessDao
 *
 * This class defines the base operations for accessing a {@link BasePartitionedEntity} from a partitioned storage
 *
 * @param <E> the type of the entity to persist
 * @param <I> the type of the key of the entity
 * @param <P> the type of the partition key of the entity
 */
public interface BasePartitionedAccessDao<E extends BasePartitionedEntity<I, P>, I, P> extends BaseAccessDao<E, I> {

	/**
	 * Retrieve all entities by id.
	 * In partitioned daos multiple entities with the same id can exist
	 * in different partitions. This method will return all entities
	 * that share the same id.
	 *
	 * @param id The internal identifier of the entity to retrieve.
	 * @return The list of entities with that id.
	 */
	public Iterable<E> findAllById (I id);

	/**
	 * Retrieve a page of all entities by id.
	 * In partitioned daos multiple entities with the same id can exist
	 * in different partitions. This method will return a page from
	 * all entities that share the same id.
	 *
	 * @param id The internal identifier of the entity to retrieve.
	 * @return The list of entities with that id.
	 */
	public Iterable<E> findAllById (Pageable page, I id);

	/**
	 * Retrieve all entities for a specific partition key.
	 *
	 * @param partitionKey The partition key of the entity type.
	 * @return All entities.
	 */
	public Iterable<E> findAll (P partitionKey);

	/**
	 * Retrieve a page of all entities for a specific partition key.
	 *
	 * @param pageable The number of items and page to retrieve.
	 * @param partitionKey The partition key of the entity type.
	 * @return The page of entities.
	 */
	public Page<E> findAll (Pageable pageable, P partitionKey);

	/**
	 * Retrieve the number of all entities for a specific partition key.
	 *
	 * @param partitionKey The partition key of the entity type.
	 * @return count of the entities.
	 */
	public long count (P partitionKey);

	/**
	 * Retrieve an entity by id for a specific partition key.
	 *
	 * @param id The internal identifier of the entity to retrieve.
	 * @param partitionKey The partition key of the entity type.
	 * @return The optional entity with the given id.
	 */
	public Optional<E> findById (I id, P partitionKey);
}



