package org.chentelman.base.module.data.mem.dao;

import java.util.HashMap;
import java.util.Map;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Default implementation for the base partitioned access dao.
 * Inherits {@link HashMap} to provide the implementation
 * for the missing methods.
 *
 * @param <E> the entity to store in the map
 * @param <I> the key type for the map
 * @param <P> the partition type for the map
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class BaseMemPartitionedAccessDaoImpl<E extends BasePartitionedEntity<I, P>, I, P> extends HashMap<P, Map<I, E>> implements BaseMemPartitionedAccessDao<E, I, P> {
	private static final long serialVersionUID = 1L;

}



