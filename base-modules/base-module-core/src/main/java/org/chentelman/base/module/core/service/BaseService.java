package org.chentelman.base.module.core.service;

import org.chentelman.base.module.core.domain.BaseEntity;

/**
 * Group all service interfaces together
 *
 * @param <E> the type of the entity to service
 * @param <I> the type of the key of the entity
 */
public interface BaseService<E extends BaseEntity<I>, I> extends
	BaseCreateService<E, I>,
	BaseAccessService<E, I>,
	BaseUpdateService<E, I>,
	BaseDeleteService<E, I> {

}



