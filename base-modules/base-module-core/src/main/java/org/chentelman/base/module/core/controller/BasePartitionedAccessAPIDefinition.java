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
 * @param <R> the entity used for the read operation
 *            it must extend S (summary entity) as the summary entity is a subset of the retrieved entities fields
 * @param <S> the entity used for the summary
 *            it is used instead of the R entity for operations returning a list of results such as search and findAll
 */
public interface BasePartitionedAccessAPIDefinition<K, P, R extends S, S extends BasePartitionedEntity<K, P>> extends BaseAccessAPIDefinition<K, S, List<S>> {
	public static final String PARTITION_HEADER = "X-Partition";

	/**
	 * Request to access a page of the items based on their id
	 *
	 * @param id the id of the items to match against
	 * @param page the page number, 0 being the first page
	 * @param size the page size
	 */
	public List<S> get (final K id, final int page, final int size);

	/**
	 * Request to access a single item from a specific container
	 *
	 * @param partition the partition of the item to match against
	 * @param id the id of the item to match against
	 */
	public R get (final P partition, final K id);

	/**
	 * Request to access all items from a specific partition
	 *
	 * @param partition the partition of the item to match against
	 */
	public List<S> findAll (final P partition);

	/**
	 * Request to access a page of items from a specific partition
	 *
	 * @param partition the partition of the item to match against
	 * @param page the page number, 0 being the first page
	 * @param size the page size
	 */
	public List<S> findAll (final P partition, final int page, final int size);
}



