package org.chentelman.base.module.data.jpa.service;

import org.chentelman.base.module.core.data.BaseAccessDao;
import org.chentelman.base.module.core.data.BaseCreateDao;
import org.chentelman.base.module.core.data.BaseDeleteDao;
import org.chentelman.base.module.core.data.BaseUpdateDao;
import org.chentelman.base.module.core.domain.BaseEntity;
import org.chentelman.base.module.core.exception.BaseNotFoundException;
import org.chentelman.base.module.data.jpa.repository.BaseRepository;
import org.chentelman.base.module.service.BaseDefaultServiceImpl;

public class BaseJpaDefaultServiceImpl<E extends BaseEntity<I>, I> extends BaseDefaultServiceImpl<E, I> {

	private final Class<E> servicedType;
	private final BaseRepository<E, I> repository;

	public BaseJpaDefaultServiceImpl (
		Class<E> servicedType,
		BaseRepository<E, I> repository
	) {
		this.servicedType = servicedType;
		this.repository   = repository;
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
	public boolean canCreate(E domain) {
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
	public boolean canUpdate(I id, E domain) {
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
	public boolean canDelete(E domain) {
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



