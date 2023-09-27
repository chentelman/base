package org.chentelman.base.module.rest.controller;

import java.util.List;

import org.chentelman.base.module.core.component.BaseComponent;
import org.chentelman.base.module.core.controller.BasePartitionedAccessAPIDefinition;
import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Base partitioned access controller class.
 *
 * This is an abstract class and does not provide any method implementations
 * but rather sets the rest api definitions such as request method, paths and headers
 *
 * @param <K> the key type of the base entity
 * @param <P> the partition type of the base entity
 * @param <R> the entity used for the read operation
 *            it must extend S (summary entity) as the summary entity is a subset of the retrieved entities fields
 * @param <S> the entity used for the summary
 *            it is used instead of the R entity for operations returning a list of results such as search and findAll
 */
public abstract class BasePartitionedAccessController<K, P, R extends S, S extends BasePartitionedEntity<K, P>> extends BaseComponent implements BasePartitionedAccessAPIDefinition<K, P, R, S> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public abstract List<S> findAll();

	/**
	 * {@inheritDoc}
	 */
	@Override
	@GetMapping(headers = {PAGE_HEADER, SIZE_HEADER})
	@ResponseStatus(HttpStatus.OK)
	public abstract List<S> findAll (
		@RequestHeader(name = PAGE_HEADER) final int page,
		@RequestHeader(name = SIZE_HEADER) final int size
	);

	/**
	 * {@inheritDoc}
	 */
	@Override
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public abstract List<S> get(@PathVariable("id") final K id);

	/**
	 * {@inheritDoc}
	 */
	@Override
	@GetMapping(value = "/{id}", headers = {PAGE_HEADER, SIZE_HEADER})
	@ResponseStatus(HttpStatus.OK)
	public abstract List<S> get(
		@PathVariable("id") final K id,
		@RequestHeader(name = PAGE_HEADER) final int page,
		@RequestHeader(name = SIZE_HEADER) final int size
	);

	/**
	 * {@inheritDoc}
	 */
	@Override
	@GetMapping(value = "/{id}", headers = {PARTITION_HEADER})
	@ResponseStatus(HttpStatus.OK)
	public abstract R get(
		@RequestHeader(name = PARTITION_HEADER) final P partition,
		@PathVariable("id") final K id
	);

	/**
	 * {@inheritDoc}
	 */
	@Override
	@GetMapping(headers = {PARTITION_HEADER})
	@ResponseStatus(HttpStatus.OK)
	public abstract List<S> findAll(@RequestHeader(value = PARTITION_HEADER) final P partition);

	/**
	 * {@inheritDoc}
	 */
	@Override
	@GetMapping(headers = {PAGE_HEADER, SIZE_HEADER, PARTITION_HEADER})
	@ResponseStatus(HttpStatus.OK)
	public abstract List<S> findAll(
		@RequestHeader(name = PARTITION_HEADER) final P partition,
		@RequestHeader(name = PAGE_HEADER) final int page,
		@RequestHeader(name = SIZE_HEADER) final int size
	);
}



