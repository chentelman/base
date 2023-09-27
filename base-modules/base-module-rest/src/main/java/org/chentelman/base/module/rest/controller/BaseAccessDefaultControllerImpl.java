package org.chentelman.base.module.rest.controller;

import java.util.List;

import org.chentelman.base.module.core.controller.BaseAccessAPIDefinition;
import org.chentelman.base.module.core.domain.BaseEntity;
import org.chentelman.base.module.core.service.BaseAccessService;
import org.chentelman.base.module.core.utilities.BaseUtilities;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

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
public class BaseAccessDefaultControllerImpl<E extends BaseEntity<I>, I> extends BaseAccessController<I, E, E> implements BaseAccessAPIDefinition<I, E, E> {

	protected final BaseAccessService<E, I> service;

	public BaseAccessDefaultControllerImpl (BaseAccessService<E, I> service) {
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
		return BaseUtilities.toList(
			service.readAll(PageRequest.of(page, size))
		);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E get(@PathVariable("id") final I id) {
		return service.read(id);
	}
}



