package org.chentelman.base.module.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.chentelman.base.module.core.service.BasePartitionedAccessService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Base partitioned access convert controller class.
 *
 * Convert controllers do not return the entity retrieved from the service layer directly
 * but rather convert that entity to one of a different type, potentially
 * introducing additional logic to do calculations or reduce the visible scope of the operation.
 *
 * Methods to convert the entities to the returned objects are defined as abstract
 * for each different type to implement their own
 *
 * @param <K> the key type of the base entity
 * @param <P> the partition type of the base entity
 * @param <R> the entity used for the read operation
 *            it must extend S (summary entity) as the summary entity is a subset of the retrieved entities fields
 * @param <S> the entity used for the summary
 *            it is used instead of the R entity for operations returning a list of results such as search and findAll
 * @param <D> the type of the managed by the service layer
 */
public abstract class BasePartitionedAccessConvertControllerImpl<K, P, R extends S, S extends BasePartitionedEntity<K, P>, D extends BasePartitionedEntity<K, P>> extends BasePartitionedAccessController<K, P, R, S> {

	protected final BasePartitionedAccessService<D, K, P> service;

	protected BasePartitionedAccessConvertControllerImpl (BasePartitionedAccessService<D, K, P> service) {
		this.service = service;
	}

	/**
	 * Conversion method to get a detailed information for the domain
	 *
	 * @param domain the entity from the service layer
	 * @return a converted object to return to the user
	 */
	protected abstract R toDetails (final D domain);

	/**
	 * Conversion method to get a summarised information for the domain
	 *
	 * @param domain the entity from the service layer
	 * @return a converted object to return to the user
	 */
	protected abstract S toSummary (final D domain);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<S> findAll() {
		return service.readAll()
			.stream()
			.map(this::toSummary)
			.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<S> findAll (
		@RequestHeader(name = PAGE_HEADER) final int page,
		@RequestHeader(name = SIZE_HEADER) final int size
	) {
		Pageable pageable = PageRequest.of(page, size);

		return service.readAll(pageable)
			.stream()
			.map(this::toSummary)
			.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<S> get(@PathVariable("id") final K id) {
		return service.readAllById(id)
			.stream()
			.map(this::toSummary)
			.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<S> get (
		@PathVariable("id") final K id,
		@RequestHeader(name = PAGE_HEADER) final int page,
		@RequestHeader(name = SIZE_HEADER) final int size
	) {
		Pageable pageable = PageRequest.of(page, size);

		return service.readAllById(pageable, id)
			.stream()
			.map(this::toSummary)
			.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<S> findAll(@RequestHeader(value = PARTITION_HEADER) final P partition) {
		return service.readAll(partition)
			.stream()
			.map(this::toSummary)
			.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<S> findAll (
		@RequestHeader(value = PARTITION_HEADER) final P partition,
		@RequestHeader(name = PAGE_HEADER) final int page,
		@RequestHeader(name = SIZE_HEADER) final int size
	) {
		Pageable pageable = PageRequest.of(page, size);

		return service.readAll(pageable, partition)
			.stream()
			.map(this::toSummary)
			.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public R get(@RequestHeader(value = PARTITION_HEADER) final P partition, @PathVariable("id") final K id) {
		return toDetails(service.read(id, partition));
	}
}



