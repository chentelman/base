package org.chentelman.base.testing.controller.testresults;

import org.chentelman.base.testing.controller.webclient.BaseResponseResults;
import org.chentelman.base.testing.service.BaseListable;

public interface BaseTestRequest extends BaseListable {
	public String getLatestId ();
	public String getPath ();
	public String getKeyName ();

	public default void accessResults(BaseResponseResults results) {
		accessResults (results, false);
	}

	public void findAllResults(BaseResponseResults results);

	public void createResults(BaseResponseResults results);
	public void accessResults(BaseResponseResults results, boolean multipleResults);
	public void updateResults(BaseResponseResults results);
	public void deleteResults(BaseResponseResults results);

	public boolean isSuccess();

	public BaseResponseResults getLatestResults();
}



