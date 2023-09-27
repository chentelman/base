package org.chentelman.base.module.client.api.entity;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TestPartitionedEntity extends TestEntity implements BasePartitionedEntity<Long, String> {
	private static final long serialVersionUID = 1L;

	String partition;
}



