package org.chentelman.base.module.data.jpa.testing.entity;

import org.chentelman.base.module.core.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TestEntity")
public class TestEntity implements BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "test_entity_id")
	private Long id;

	@Column(name = "test_entity_data")
	private String data;
}



