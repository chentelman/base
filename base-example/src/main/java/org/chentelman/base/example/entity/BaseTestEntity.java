package org.chentelman.base.example.entity;

import org.chentelman.base.module.core.domain.BasePartitionedEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class BaseTestEntity implements BasePartitionedEntity<Long, String> {
	private static final long serialVersionUID = 1L;

	@Id
	private Long   id;
	private String code;
	private String name;

	@Override
	@JsonIgnore
	public String getPartition() {
		return code;
	}
}



