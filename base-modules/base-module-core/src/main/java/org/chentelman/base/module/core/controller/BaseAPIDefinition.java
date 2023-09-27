package org.chentelman.base.module.core.controller;

import org.chentelman.base.module.core.domain.BaseEntity;

/**
 * Base API Definition for Base Entities
 *
 * Provides the CRUD operations
 *
 * @param <K> the key type of the base entity
 * @param <C> the entity used for creation,
 *            it must extend R (read entity) as all elements retrieved must be provided in the creation of the entity
 * @param <R> the entity used for the read operation
 *            it must extend S (summary entity) as the summary entity is a subset of the retrieved entities fields
 * @param <U> the entity used for update
 *            this entity is unrestricted as any number of field can be set to be updatable
 * @param <S> the entity used for the summary
 *            it is used instead of the R entity for operations returning a list of results such as search and findAll
 * @param <G> the entity used for the get operation
 *            this is used to appropriately set the return type of the get operation.
 *            as partitioned controllers will return a list of items, whilst the non partitioned ones a single element
 *            this can be either set to a List<R> or R to accommodate those cases respectively
 */
public interface BaseAPIDefinition<K, C, R extends S, S extends BaseEntity<K>, G, U> extends BaseAccessAPIDefinition<K, S, G> {

	/**
	 * Request for an item to be created
	 *
	 * @param data descrition of the item to create
	 * @return the details of the created item
	 */
	public R create (final C data);

	/**
	 * Request to update an existing item
	 *
	 * @param id identifier for the item to update
	 * @param data information to update the item with
	 */
	public void update (final K id, final U data);

	/**
	 * Request to delete an item
	 *
	 * @param id identifier for the item to delete
	 */
	public void delete (final K id);
}



