package org.chentelman.base.module.data.mem.dao;

import java.util.HashMap;

import org.chentelman.base.module.core.domain.BaseEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Default implementation for the base dao.
 * Inherits {@link HashMap} to provide the implementation
 * for the missing methods.
 *
 * @param <E> the entity to store in the map
 * @param <I> the key type for the map
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class BaseMemDaoImpl<E extends BaseEntity<I>, I> extends BaseMemAccessDaoImpl<E, I> implements BaseMemDao<E, I> {
	private static final long serialVersionUID = 1L;

}



