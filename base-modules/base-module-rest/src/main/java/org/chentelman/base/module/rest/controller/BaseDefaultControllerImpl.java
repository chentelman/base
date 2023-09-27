package org.chentelman.base.module.rest.controller;

import java.util.List;

import org.chentelman.base.module.core.domain.BaseEntity;
import org.chentelman.base.module.core.service.BaseService;
import org.chentelman.base.module.core.utilities.BaseUtilities;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import jakarta.validation.Valid;

/**
 * Base access default controller class.
 *
 * Default controllers do not perform any conversions and return
 * the objects to the user as they are accessed from the service layer.
 *
 * Classes extending this one do not have to provide any additional implementations
 * other than supply the correct service.
 *
 * @param <K> the key type of the base entity
 * @param <E> the managed entity
 */
public abstract class BaseDefaultControllerImpl<E extends BaseEntity<I>, I> extends BaseController<I, E, E, E, E> {

	protected final BaseService<E, I> service;

	protected BaseDefaultControllerImpl (BaseService<E, I> service) {
		this.service = service;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E get (@PathVariable("id") final I id) {
		return service.read(id);
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

		return BaseUtilities.toList (
			service.readAll(pageable)
		);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E create (@Valid @RequestBody final E data) {
		return service.create(data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update (@PathVariable("id") final I id, @Valid @RequestBody final E data) {
		service.update(id, data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete (@PathVariable("id") final I id) {
		service.delete(id);
	}
}



