package org.chentelman.base.module.service;

import java.util.List;
import java.util.Optional;

import org.chentelman.base.module.core.component.BaseComponent;
import org.chentelman.base.module.core.domain.BaseEntity;
import org.chentelman.base.module.core.utilities.BaseUtilities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

/**
 * A default implementation for the base service interfaces.
 *
 * Reuses the dao entity for the serviced entity.
 */
public abstract class BaseDefaultServiceImpl<E extends BaseEntity<I>, I> extends BaseComponent implements BaseDefaultService<E, I> {

	/*
	 * BaseCreateService interface
	 */

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public E create (E entity) {
		if (canCreate(entity)) {
			return getCreateDao().add(entity);
		}
		return null;
	}

	/*
	 * BaseAccessService interface
	 */

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public List<E> readAll () {
		return BaseUtilities.toList(getAccessDao().findAll());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public Page<E> readAll (Pageable pageable) {
		return getAccessDao().findAll(pageable);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public long count () {
		return getAccessDao().count();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public E read (I id) {
		Optional<E> entity = getAccessDao().findById(id);

		if (entity.isEmpty()) {
			handleNotFound (id);
			return null;
		}

		return entity.get();
	}

	/*
	 * BaseUpdateService interface
	 */

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public void update (I id, E entity) {
		if (canUpdate(id, entity)) {
			getUpdateDao().update(entity);
		}
	}

	/*
	 * BaseDeleteService interface
	 */

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public void delete (E entity) {
		if (canDelete(entity)) {
			getDeleteDao().delete(entity);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public void delete (I id) {
		if (canDelete(id)) {
			getDeleteDao().deleteById(id);
		}
	}
}



