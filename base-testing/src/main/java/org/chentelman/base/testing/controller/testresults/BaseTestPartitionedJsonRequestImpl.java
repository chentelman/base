package org.chentelman.base.testing.controller.testresults;

import org.chentelman.base.testing.controller.webclient.BaseResponseResults;

public abstract class BaseTestPartitionedJsonRequestImpl extends BaseTestJsonRequestImpl implements BaseTestPartitionedJsonRequest {

	@Override
	public void accessResults(BaseResponseResults results) {
		accessResults (results, true);
	}
}



