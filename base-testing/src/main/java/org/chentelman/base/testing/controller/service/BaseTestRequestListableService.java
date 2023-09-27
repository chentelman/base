package org.chentelman.base.testing.controller.service;

import org.chentelman.base.testing.controller.testresults.BaseTestRequest;
import org.chentelman.base.testing.service.BaseListableService;

/**
 * A base listable service specialisation for {@link BaseTestRequest} objects
 */
public interface BaseTestRequestListableService extends BaseListableService<BaseTestRequest> {

	public String getPathForService (String domain);
	public String getPathForService (String domain, String path);

	public String getPathForLatestService ();
	public String getPathForLatestService (String path);
}



