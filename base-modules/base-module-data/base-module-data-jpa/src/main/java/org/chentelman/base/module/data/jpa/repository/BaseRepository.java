package org.chentelman.base.module.data.jpa.repository;

import org.chentelman.base.module.core.data.BaseAccessDao;
import org.chentelman.base.module.core.data.BaseCreateDao;
import org.chentelman.base.module.core.data.BaseDeleteDao;
import org.chentelman.base.module.core.data.BaseUpdateDao;
import org.chentelman.base.module.core.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Extend Base repository using the JpaRepository.
 *
 * Provide default implementations for all dao interfaces,
 * that are not covered by the JpaRepository.
 *
 * @param <E> the persistent entity managed by this repository
 * @param <I> the type of the id for the perstistent entity
 */
@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity<I>, I> extends
	BaseCreateDao<E, I>,
	BaseAccessDao<E, I>,
	BaseUpdateDao<E, I>,
	BaseDeleteDao<E, I>,
	JpaRepository<E, I> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default E add (E entity) {
		return save (entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default E update (E entity) {
		return save (entity);
	}
}



