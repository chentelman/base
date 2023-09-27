package org.chentelman.base.module.core.service;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;

/**
 * Group all partitioned service interfaces together
 *
 * @param <E> the type of the entity to service
 * @param <I> the type of the key of the entity
 * @param <P> the type of the partition key of the entity
 */
public interface BasePartitionedService<E extends BasePartitionedEntity<I, P>, I, P> extends
	BasePartitionedCreateService<E, I, P>,
	BasePartitionedAccessService<E, I, P>,
	BasePartitionedUpdateService<E, I, P>,
	BasePartitionedDeleteService<E, I, P> {

}



