package org.chentelman.base.testing.conveter;

import lombok.Data;

@Data
public class TestEncoderObject {

	private int id;
	private String data;

	public TestEncoderObject (int id, String data) {
		this.id   = id;
		this.data = data;
	}
}



