package org.chentelman.base.module.rest.controller;

import java.util.List;

import org.chentelman.base.module.core.component.BaseComponent;
import org.chentelman.base.module.core.controller.BaseAccessAPIDefinition;
import org.chentelman.base.module.core.domain.BaseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Base access controller class.
 *
 * This is an abstract class and does not provide any method implementations
 * but rather sets the rest api definitions such as request method, paths and headers
 *
 * @param <K> the key type of the base entity
 * @param <S> the entity used for the summary
 * @param <R> the entity used for the get operation
 */
public abstract class BaseAccessController<K, R extends S, S extends BaseEntity<K>> extends BaseComponent implements BaseAccessAPIDefinition<K, S, R> {

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
	public abstract R get(@PathVariable("id") final K id);
}



