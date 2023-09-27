package org.chentelman.base.module.data.mem.testing.entity;

import org.chentelman.base.module.core.domain.BaseEnumType;

import lombok.Getter;

@Getter
public enum TestEnum implements BaseEnumType {
	VALUE1 ("1", "name1"),
	VALUE2 ("2", "name2"),
	VALUE3 ("3", "name3"),
	VALUE4 ("4", "name4");

	private String code;
	private String name;

	private TestEnum (String code, String name) {
		this.code = code;
		this.name = name;
	}
}



