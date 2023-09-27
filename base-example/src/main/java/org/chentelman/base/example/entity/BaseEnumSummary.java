package org.chentelman.base.example.entity;

import org.chentelman.base.module.core.domain.BaseEntity;

import lombok.Data;

@Data
public class BaseEnumSummary implements BaseEntity<String> {
	private static final long serialVersionUID = 1L;

	private String id;
	private String code;
}



