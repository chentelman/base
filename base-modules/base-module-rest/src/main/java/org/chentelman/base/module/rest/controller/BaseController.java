package org.chentelman.base.module.rest.controller;

import org.chentelman.base.module.core.controller.BaseAPIDefinition;
import org.chentelman.base.module.core.domain.BaseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.Valid;

/**
 * Base controller class.
 *
 * This is an abstract class and does not provide any method implementations
 * but rather sets the rest api definitions such as request method, paths and headers
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
 */
public abstract class BaseController<K, C, R extends S, S extends BaseEntity<K>, U> extends BaseAccessController<K, R, S> implements BaseAPIDefinition<K, C, R, S, R, U> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public abstract R create(@Valid @RequestBody final C data);

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public abstract void update(@PathVariable("id") final K id, @Valid @RequestBody final U data);

	/**
	 * {@inheritDoc}
	 */
	@Override
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public abstract void delete(@PathVariable("id") final K id);
}



