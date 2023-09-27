package org.chentelman.base.module.data.mem.dao;

import java.util.HashMap;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;

/**
 * Default implementation for the base partitioned dao.
 * Inherits {@link HashMap} to provide the implementation
 * for the missing methods.
 *
 * @param <E> the entity to store in the map
 * @param <I> the key type for the map
 * @param <P> the partition type for the map
 */
public class BaseMemPartitionedDaoImpl<E extends BasePartitionedEntity<I, P>, I, P> extends BaseMemPartitionedAccessDaoImpl<E, I, P> implements BaseMemPartitionedDao<E, I, P> {
	private static final long serialVersionUID = 1L;

}



