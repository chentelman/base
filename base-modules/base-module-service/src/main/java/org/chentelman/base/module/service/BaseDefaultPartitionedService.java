package org.chentelman.base.module.service;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;

public interface BaseDefaultPartitionedService<E extends BasePartitionedEntity<I, P>, I, P> extends BaseConvertPartitionedService<E, E, I, P> {

}



