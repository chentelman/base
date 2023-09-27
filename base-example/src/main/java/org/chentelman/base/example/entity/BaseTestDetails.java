package org.chentelman.base.example.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseTestDetails extends BaseTestSummary {
	private static final long serialVersionUID = 1L;

	private String name;
}



