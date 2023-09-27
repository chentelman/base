package org.chentelman.base.module.data.mem.service;

import java.util.Objects;
import java.util.function.Function;

import org.chentelman.base.module.core.data.BaseAccessDao;
import org.chentelman.base.module.core.data.BaseCreateDao;
import org.chentelman.base.module.core.data.BaseDeleteDao;
import org.chentelman.base.module.core.data.BaseUpdateDao;
import org.chentelman.base.module.core.domain.BaseEntity;
import org.chentelman.base.module.core.exception.BaseCannotUpdateException;
import org.chentelman.base.module.core.exception.BaseDuplicateException;
import org.chentelman.base.module.core.exception.BaseNotFoundException;
import org.chentelman.base.module.data.mem.dao.BaseMemDao;
import org.chentelman.base.module.service.BaseConvertServiceImpl;

public class BaseMemConvertServiceImpl<D extends BaseEntity<I>, E extends BaseEntity<I>, I> extends BaseConvertServiceImpl<D, E, I> {

	private final Class<D> servicedType;
	private final BaseMemDao<E, I> inMemoryDao;
	private final Function<E, D> domainConverter;
	private final Function<D, E> entityConverter;

	public BaseMemConvertServiceImpl (
		Class<D> servicedType,
		BaseMemDao<E, I> inMemoryDao,
		Function<E, D> domainConverter,
		Function<D, E> entityConverter
	) {
		this.servicedType = servicedType;
		this.inMemoryDao  = inMemoryDao;

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
		return inMemoryDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canCreate(D domain) {
		if (inMemoryDao.exists(domain.getId())) {
			throw BaseDuplicateException.of(domain.getId(), servicedType);
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
	public boolean canUpdate(I id, D domain) {
		if (!Objects.equals(id, domain.getId())) {
			throw BaseCannotUpdateException.ofId(id, domain.getId(), servicedType);
		}

		if (!inMemoryDao.exists(domain.getId())) {
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
	public boolean canDelete(D domain) {
		return canDelete(domain.getId());
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



