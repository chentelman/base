package org.chentelman.base.module.client.api.entity;

import org.chentelman.base.module.core.domain.BaseEntity;

import lombok.Data;

@Data
public class TestEntity implements BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String data;
}



