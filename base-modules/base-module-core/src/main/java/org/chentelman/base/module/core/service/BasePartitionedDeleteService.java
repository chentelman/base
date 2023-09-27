package org.chentelman.base.module.core.service;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;

/**
 * BasePartitionedDeleteService
 *
 * This class defines the base delete operations for {@link BasePartitionedEntity}
 *
 * @param <E> the type of the entity to service
 * @param <I> the type of the key of the entity
 * @param <P> the type of the partition key of the entity
 */
public interface BasePartitionedDeleteService<E extends BasePartitionedEntity<I, P>, I, P> extends BaseDeleteService<E, I> {

	/**
	 * Removes an entity.
	 *
	 * @param entity The id of the entity to remove.
	 * @param partitionKey The partition key of the entity type.
	 */
	public void delete (I id, P partitionKey);

	/**
	 * Removes all entities for a specific partitionKey.
	 *
	 * @param partitionKey The partition key of the entity type.
	 */
	public void deleteAll (P partitionKey);
}



