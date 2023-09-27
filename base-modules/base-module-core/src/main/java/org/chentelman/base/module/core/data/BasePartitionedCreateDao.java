package org.chentelman.base.module.core.data;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;

/**
 * BasePartitionedCreateDao
 *
 * This class defines the base operations for creating a {@link BasePartitionedEntity} to a partitioned storage
 *
 * @param <E> the type of the entity to persist
 * @param <I> the type of the key of the entity
 * @param <P> the type of the partition key of the entity
 */
public interface BasePartitionedCreateDao<E extends BasePartitionedEntity<I, P>, I, P> extends BaseCreateDao<E, I> {
}



