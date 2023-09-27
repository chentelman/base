package org.chentelman.base.module.core.controller;

import java.util.List;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;

/**
 * Base API Definition for Base Partitioned Entities
 *
 * Provides the CRUD operations
 *
 * @param <K> the key type of the base entity
 * @param <P> the partition type of the base entity
 * @param <C> the entity used for creation,
 *            it must extend R (read entity) as all elements retrieved must be provided in the creation of the entity
 * @param <R> the entity used for the read operation
 *            it must extend S (summary entity) as the summary entity is a subset of the retrieved entities fields
 * @param <S> the entity used for the summary
 *            it is used instead of the R entity for operations returning a list of results such as search and findAll
 * @param <U> the entity used for update
 *            this entity is unrestricted as any number of field can be set to be updatable
 */
public interface BasePartitionedAPIDefinition<K, P, C, R extends S, S extends BasePartitionedEntity<K, P>, U> extends
	BaseAPIDefinition<K, C, R, S, List<S>, U>,
	BasePartitionedAccessAPIDefinition<K, P, R, S> {

	/**
	 * Request to update an existing item from a container
	 *
	 * @param partition the partition of the item to match against
	 * @param id identifier for the item to update
	 * @param data information to update the item with
	 */
	public void update(final P partition, final K id, final U data);

	/**
	 * Request to delete an existing item from a container
	 *
	 * @param partition the partition of the item to match against
	 * @param id identifier for the item to update
	 */
	public void delete(final P partition, final K id);

	/**
	 * Request to delete all items from a container
	 *
	 * @param partition the partition of the item to match against
	 */
	public void deletePartition(final P partition);
}



