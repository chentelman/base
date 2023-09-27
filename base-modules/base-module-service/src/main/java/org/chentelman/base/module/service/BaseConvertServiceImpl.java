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
 * A implementation for the base service interfaces, converting the dao managed type
 * to a different serviced one.
 */
public abstract class BaseConvertServiceImpl<D extends BaseEntity<I>, E extends BaseEntity<I>, I> extends BaseComponent implements BaseConvertService<D, E, I> {

	/**
	 * Method to convert an entity to a domain
	 *
	 * @param entity the object managed by the dao layer
	 * @return a converted object that is managed by the service layer
	 */
	public abstract D toDomain (E entity);

	/**
	 * Method to convert a domain to an entity
	 *
	 * @param domain the object managed by the service layer
	 * @return a converted object that is managed by the dao layer
	 */
	public abstract E toEntity (D domain);

	/*
	 * BaseCreateService interface
	 */

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public D create (D domain) {
		if (canCreate(domain)) {
			return toDomain(getCreateDao().add(toEntity(domain)));
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
	public List<D> readAll () {
		return BaseUtilities.toList(getAccessDao().findAll(), this::toDomain);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public Page<D> readAll (Pageable pageable) {
		return getAccessDao().findAll(pageable).map(this::toDomain);
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
	public D read (I id) {
		Optional<E> entity = getAccessDao().findById(id);

		if (entity.isEmpty()) {
			handleNotFound(id);
			return null;
		}

		return toDomain(entity.get());
	}

	/*
	 * BaseUpdateService interface
	 */

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	public void update (I id, D domain) {
		if (canUpdate(id, domain)) {
			getUpdateDao().update(toEntity(domain));
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
	public void delete (D domain) {
		if (canDelete(domain)) {
			getDeleteDao().delete(toEntity(domain));
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



