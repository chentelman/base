package org.chentelman.base.module.data.jpa.service;

import java.util.function.Function;

import org.chentelman.base.module.core.data.BaseAccessDao;
import org.chentelman.base.module.core.data.BaseCreateDao;
import org.chentelman.base.module.core.data.BaseDeleteDao;
import org.chentelman.base.module.core.data.BaseUpdateDao;
import org.chentelman.base.module.core.domain.BaseEntity;
import org.chentelman.base.module.core.exception.BaseNotFoundException;
import org.chentelman.base.module.data.jpa.repository.BaseRepository;
import org.chentelman.base.module.service.BaseConvertServiceImpl;

public class BaseJpaConvertServiceImpl<D extends BaseEntity<I>, E extends BaseEntity<I>, I> extends BaseConvertServiceImpl<D, E, I> {

	private final Class<D> servicedType;
	private final BaseRepository<E, I> repository;
	private final Function<E, D> domainConverter;
	private final Function<D, E> entityConverter;

	public BaseJpaConvertServiceImpl (
		Class<D> servicedType,
		BaseRepository<E, I> repository,
		Function<E, D> domainConverter,
		Function<D, E> entityConverter
	) {
		this.servicedType = servicedType;
		this.repository   = repository;

		this.domainConverter = domainConverter;
		this.entityConverter = entityConverter;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public D toDomain(E entity) {
		return domainConverter.apply(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E toEntity(D domain) {
		return entityConverter.apply(domain);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BaseCreateDao<E, I> getCreateDao() {
		return repository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canCreate(D domain) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BaseAccessDao<E, I> getAccessDao() {
		return repository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void handleNotFound(I id) {
		throw BaseNotFoundException.of(id, servicedType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BaseUpdateDao<E, I> getUpdateDao() {
		return repository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canUpdate(I id, D domain) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BaseDeleteDao<E, I> getDeleteDao() {
		return repository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canDelete(D domain) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canDelete(I id) {
		return true;
	}
}



