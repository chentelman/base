package org.chentelman.base.module.data.mem.service;

import java.util.Objects;

import org.chentelman.base.module.core.data.BaseAccessDao;
import org.chentelman.base.module.core.data.BaseCreateDao;
import org.chentelman.base.module.core.data.BaseDeleteDao;
import org.chentelman.base.module.core.data.BaseUpdateDao;
import org.chentelman.base.module.core.domain.BaseEntity;
import org.chentelman.base.module.core.exception.BaseCannotUpdateException;
import org.chentelman.base.module.core.exception.BaseDuplicateException;
import org.chentelman.base.module.core.exception.BaseNotFoundException;
import org.chentelman.base.module.data.mem.dao.BaseMemDao;
import org.chentelman.base.module.service.BaseDefaultServiceImpl;

public class BaseMemDefaultServiceImpl<E extends BaseEntity<I>, I> extends BaseDefaultServiceImpl<E, I> {

	private final Class<E> servicedType;
	private final BaseMemDao<E, I> inMemoryDao;

	public BaseMemDefaultServiceImpl (
		Class<E> servicedType,
		BaseMemDao<E, I> inMemoryDao
	) {
		this.servicedType = servicedType;
		this.inMemoryDao  = inMemoryDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BaseCreateDao<E, I> getCreateDao() {
		return inMemoryDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canCreate(E entity) {
		if (inMemoryDao.exists(entity.getId())) {
			throw BaseDuplicateException.of(entity.getId(), servicedType);
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BaseAccessDao<E, I> getAccessDao() {
		return inMemoryDao;
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
		return inMemoryDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canUpdate(I id, E entity) {
		if (!Objects.equals(id, entity.getId())) {
			throw BaseCannotUpdateException.ofId(id, entity.getId(), servicedType);
		}

		if (!inMemoryDao.exists(entity.getId())) {
			throw BaseNotFoundException.of(id, servicedType);
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BaseDeleteDao<E, I> getDeleteDao() {
		return inMemoryDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canDelete(E entity) {
		return canDelete(entity.getId());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canDelete(I id) {
		if (!inMemoryDao.exists(id)) {
			throw BaseNotFoundException.of(id, servicedType);
		}
		return true;
	}
}



