package org.chentelman.base.module.core.service;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;

/**
 * BasePartitionedCreateService
 *
 * This class defines the base create operations for {@link BasePartitionedEntity}
 *
 * @param <E> the type of the entity to service
 * @param <I> the type of the key of the entity
 * @param <P> the type of the partition key of the entity
 */
public interface BasePartitionedCreateService<E extends BasePartitionedEntity<I, P>, I, P> extends BaseCreateService<E, I> {

}



