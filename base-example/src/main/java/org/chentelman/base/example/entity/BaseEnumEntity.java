package org.chentelman.base.example.entity;

import org.chentelman.base.module.core.domain.BaseEnumType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonIncludeProperties(value = {
	"id",
	"code",
	"name"
})
public enum BaseEnumEntity implements BaseEnumType {
	OPTION1 ("1", "The first option"),
	OPTION2 ("2", "The second option"),
	OPTION3 ("3", "The third option");

	private String id;
	private String code;
	private String name;

	@Override
	@JsonIgnore(value = false)
	public String getId () {
		return id;
	}

	private BaseEnumEntity (String code, String name) {
		this.id   = code;
		this.code = code;
		this.name = name;
	}
}



