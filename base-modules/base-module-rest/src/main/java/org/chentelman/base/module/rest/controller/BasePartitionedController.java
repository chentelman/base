package org.chentelman.base.module.rest.controller;

import org.chentelman.base.module.core.controller.BasePartitionedAPIDefinition;
import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.Valid;

/**
 * Base partitioned controller class.
 *
 * This is an abstract class and does not provide any method implementations
 * but rather sets the rest api definitions such as request method, paths and headers
 *
 * @param <K> the key type of the base entity
 * @param <P> the partition type of the base entity
 * @param <C> the entity used for creation,
 *            it must extend R (read entity) as all elements retrieved must be provided in the creation of the entity
 * @param <R> the entity used for the read operation
 *            it must extend S (summary entity) as the summary entity is a subset of the retrieved entities fields
 * @param <S> the entity used for the summary
 *            it is used instead of the R entity for operations returning a list of results such as search and findAll
 * @param <U> the entity used for update
 *            this entity is unrestricted as any number of field can be set to be updatable
 */
public abstract class BasePartitionedController<K, P, C, R extends S, S extends BasePartitionedEntity<K, P>, U> extends BasePartitionedAccessController<K, P, R, S> implements BasePartitionedAPIDefinition<K, P, C, R, S, U> {

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

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PutMapping(value = "/{id}", headers = {PARTITION_HEADER})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public abstract void update(@RequestHeader(value = PARTITION_HEADER) final P partition, @PathVariable("id") final K id, @Valid @RequestBody final U data);

	/**
	 * {@inheritDoc}
	 */
	@Override
	@DeleteMapping(value = "/{id}", headers = {PARTITION_HEADER})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public abstract void delete(@RequestHeader(value = PARTITION_HEADER) final P partition, @PathVariable("id") final K id);

	/**
	 * {@inheritDoc}
	 */
	@Override
	@DeleteMapping(headers = {PARTITION_HEADER})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public abstract void deletePartition(@RequestHeader(value = PARTITION_HEADER) final P partition);
}



