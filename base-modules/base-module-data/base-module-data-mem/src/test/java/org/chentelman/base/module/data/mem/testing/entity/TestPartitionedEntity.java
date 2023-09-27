package org.chentelman.base.module.data.mem.testing.entity;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;

import lombok.Data;

@Data
public class TestPartitionedEntity implements BasePartitionedEntity<Long, String> {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String partition;
	private String data;
}



