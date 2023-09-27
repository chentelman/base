package org.chentelman.base.testing.controller.service;

import org.chentelman.base.testing.controller.testresults.BaseTestRequest;
import org.chentelman.base.testing.service.BaseListableServiceImpl;
import org.springframework.beans.factory.ListableBeanFactory;

/**
 * A base listable service implementation for {@link BaseTestRequest} objects
 */
public class BaseTestRequestListableServiceImpl extends BaseListableServiceImpl<BaseTestRequest> implements BaseTestRequestListableService {

	public BaseTestRequestListableServiceImpl (ListableBeanFactory listableBeanFactory) {
		super(listableBeanFactory, BaseTestRequest.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPathForService (String domain) {
		return getListable(domain).getPath();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPathForService (String domain, String path) {
		return getListable(domain).getPath() + path;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPathForLatestService () {
		return getLatestListable().getPath();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPathForLatestService (String path) {
		return getLatestListable().getPath() + path;
	}
}



