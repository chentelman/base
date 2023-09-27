package org.chentelman.base.module.core.data;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;

/**
 * BaseDeleteDao
 *
 * This class defines the base operations for removing a {@link BasePartitionedEntity} from a partitioned storage
 *
 * @param <I> the type of the key of the entity
 * @param <P> the type of the partition key of the entity
 */
public interface BasePartitionedDeleteDao<E extends BasePartitionedEntity<I, P>, I, P> extends BaseDeleteDao<E, I> {

	/**
	 * Removes an entity from the persistent store.
	 *
	 * @param id The internal identifier of the entity to delete.
	 * @param partitionKey The partition key of the entity type.
	 */
	public void deleteById (I id, P partitionKey);

	/**
	 * Removes all entities with the same partition key from the persistent store.
	 *
	 * @param partitionKey The partition key of the entity type.
	 */
	public void deleteByPartitionKey (P partitionKey);
}



