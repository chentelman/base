package org.chentelman.base.module.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.chentelman.base.module.core.controller.BaseAccessAPIDefinition;
import org.chentelman.base.module.core.domain.BaseEntity;
import org.chentelman.base.module.core.service.BaseAccessService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Base access convert controller class.
 *
 * Convert controllers do not return the entity retrieved from the service layer directly
 * but rather convert that entity to one of a different type, potentially
 * introducing additional logic to do calculations or reduce the visible scope of the operation.
 *
 * Methods to convert the entities to the returned objects are defined as abstract
 * for each different type to implement their own
 *
 * @param <K> the key type of the base entity
 * @param <S> the entity used for the summary
 * @param <R> the entity used for the get operation
 * @param <D> the type of the managed by the service layer
 */
public abstract class BaseAccessConvertControllerImpl<D extends BaseEntity<I>, R extends S, S extends BaseEntity<I>, I> extends BaseAccessController<I, R, S> implements BaseAccessAPIDefinition<I, S, R> {

	protected final BaseAccessService<D, I> service;

	protected BaseAccessConvertControllerImpl (BaseAccessService<D, I> service) {
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
		return service.readAll(PageRequest.of(page, size))
			.stream()
			.map(this::toSummary)
			.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public R get(@PathVariable("id") final I id) {
		return toDetails(service.read(id));
	}
}



