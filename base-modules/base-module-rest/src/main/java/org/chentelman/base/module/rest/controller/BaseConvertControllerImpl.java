package org.chentelman.base.module.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.chentelman.base.module.core.domain.BaseEntity;
import org.chentelman.base.module.core.service.BaseService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import jakarta.validation.Valid;

/**
 * Base convert controller class.
 *
 * Convert controllers do not return the entity retrieved from the service layer directly
 * but rather convert that entity to one of a different type, potentially
 * introducing additional logic to do calculations or reduce the visible scope of the operation.
 *
 * Methods to convert the entities to the returned objects are defined as abstract
 * for each different type to implement their own
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
 * @param <D> the type of the managed by the service layer
 */
public abstract class BaseConvertControllerImpl<K, C, R extends S, S extends BaseEntity<K>, U, D extends BaseEntity<K>> extends BaseController<K, C, R, S, U> {

	protected final BaseService<D, K> service;

	protected BaseConvertControllerImpl (BaseService<D, K> service) {
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
	 * Conversion method to get a domain entity from a creation object
	 *
	 * @param create the object describing a new domain entity
	 * @return the domain entity to create
	 */
	protected abstract D toCreate  (final C create);

	/**
	 * Conversion method to get a domain entity from a update object
	 *
	 * @param create the updated version of a domain entity
	 * @return the domain entity to update
	 */
	protected abstract D toUpdate  (final U update, final K id);

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
	public R get(@PathVariable("id") final K id) {
		return toDetails(service.read(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public R create(@Valid @RequestBody final C data) {
		return toDetails(service.create(toCreate(data)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(@PathVariable("id") final K id, @Valid @RequestBody final U data) {
		service.update(id, toUpdate(data, id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(@PathVariable("id") final K id) {
		service.delete(id);
	}
}



