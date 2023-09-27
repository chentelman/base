package org.chentelman.base.example.entity;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class BaseTestSummary implements BasePartitionedEntity<Long, String> {
	private static final long serialVersionUID = 1L;

	private Long   id;
	private String code;

	@Override
	@JsonIgnore
	public String getPartition() {
		return code;
	}
}



