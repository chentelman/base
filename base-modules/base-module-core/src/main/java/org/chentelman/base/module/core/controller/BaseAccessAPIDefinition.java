package org.chentelman.base.module.core.controller;

import java.util.List;

import org.chentelman.base.module.core.domain.BaseEntity;

/**
 * Base API Definition for Base Entities
 *
 * Provides the CRUD operations
 *
 * @param <K> the key type of the base entity
 * @param <S> the entity used for the summary
 * @param <G> the entity used for the get operation
 */
public interface BaseAccessAPIDefinition<K, S extends BaseEntity<K>, G> {
	public static final String PAGE_HEADER = "X-Page";
	public static final String SIZE_HEADER = "X-Size";

	/**
	 * Return a summary of all entities
	 */
	public List<S> findAll ();

	/**
	 * return a summary for a specific page of the entities
	 *
	 * @param page the page number, 0 being the first page
	 * @param size the page size
	 */
	public List<S> findAll (final int page, final int size);

	/**
	 * Return the item(s) corresponding to the provided id
	 *
	 * @param id to match the items against
	 */
	public G get (final K id);
}



