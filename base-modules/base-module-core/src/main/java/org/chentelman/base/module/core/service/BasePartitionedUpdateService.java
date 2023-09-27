package org.chentelman.base.module.core.service;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;

/**
 * BaseUpdateService
 *
 * This class defines the base update operations for {@link BasePartitionedEntity}
 *
 * @param <E> the type of the entity to service
 * @param <I> the type of the key of the entity
 * @param <P> the type of the partition key of the entity
 */
public interface BasePartitionedUpdateService<E extends BasePartitionedEntity<I, P>, I, P> extends BaseUpdateService<E, I> {

	/**
	 * Saves the state of an object.
	 *
	 * @param id The id of the entity to update
	 * @param entity The object state to update.
	 */
	public void update (I id, P partitionKey, E entity);
}



