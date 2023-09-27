package org.chentelman.base.module.rest.controller;

import java.util.List;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.chentelman.base.module.core.service.BasePartitionedService;
import org.chentelman.base.module.core.utilities.BaseUtilities;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import jakarta.validation.Valid;

/**
 * Base partitioned default controller class.
 *
 * Default controllers do not perform any conversions and return
 * the objects to the user as they are accessed from the service layer.
 *
 * Classes extending this one do not have to provide any additional implementations
 * other than supply the correct service.
 *
 * @param <K> the key type of the base entity
 * @param <P> the partition type of the base entity
 * @param <E> the managed entity
 */
public abstract class BasePartitionedDefaultControllerImpl<E extends BasePartitionedEntity<K, P>, K, P> extends BasePartitionedController<K, P, E, E, E, E> {

	protected final BasePartitionedService<E, K, P> service;

	protected BasePartitionedDefaultControllerImpl (BasePartitionedService<E, K, P> service) {
		this.service = service;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<E> findAll() {
		return service.readAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<E> findAll (
		@RequestHeader(name = PAGE_HEADER) final int page,
		@RequestHeader(name = SIZE_HEADER) final int size
	) {
		Pageable pageable = PageRequest.of(page, size);

		return BaseUtilities.toList(
			service.readAll(pageable)
		);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<E> get(@PathVariable("id") final K id) {
		return service.readAllById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<E> get (
		@PathVariable("id") final K id,
		@RequestHeader(name = PAGE_HEADER) final int page,
		@RequestHeader(name = SIZE_HEADER) final int size
	) {
		Pageable pageable = PageRequest.of(page, size);

		return service.readAllById(pageable, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E create(@Valid @RequestBody final E data) {
		return service.create(data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(@PathVariable("id") final K id, @Valid @RequestBody final E data) {
		service.update(id, data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(@PathVariable("id") final K id) {
		service.delete(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<E> findAll(@RequestHeader(value = PARTITION_HEADER) final P partition) {
		return service.readAll(partition);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<E> findAll (
		@RequestHeader(value = PARTITION_HEADER) final P partition,
		@RequestHeader(name = PAGE_HEADER) final int page,
		@RequestHeader(name = SIZE_HEADER) final int size
	) {
		Pageable pageable = PageRequest.of(page, size);

		return service.readAll(pageable, partition);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E get(@RequestHeader(value = PARTITION_HEADER) final P partition, @PathVariable("id") final K id) {
		return service.read(id, partition);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(@RequestHeader(value = PARTITION_HEADER) final P partition, @PathVariable("id") final K id, @Valid @RequestBody final E data) {
		service.update(id, partition, data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(@RequestHeader(value = PARTITION_HEADER) final P partition, @PathVariable("id") final K id) {
		service.delete(id, partition);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletePartition(@RequestHeader(value = PARTITION_HEADER) final P partition) {
		service.deleteAll(partition);
	}
}



