package org.chentelman.base.testing.objects;

import org.chentelman.base.testing.conveter.BaseDecoderService;

/**
 * A simple builder to build TestObjects to test the builder functionality
 */
public class TestBuilder extends BaseObjectBuilderImpl<TestObject> {

	public TestBuilder(BaseDecoderService decoderService) {
		super(TestObject.class, decoderService);
	}

}



