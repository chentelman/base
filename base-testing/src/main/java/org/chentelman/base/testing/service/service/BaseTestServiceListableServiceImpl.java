package org.chentelman.base.testing.service.service;

import org.chentelman.base.testing.data.testdao.BaseTestDao;
import org.chentelman.base.testing.service.BaseListableServiceImpl;
import org.chentelman.base.testing.service.testservice.BaseTestService;
import org.springframework.beans.factory.ListableBeanFactory;

/**
 * A base listable service implementation for {@link BaseTestDao} objects
 */
public class BaseTestServiceListableServiceImpl extends BaseListableServiceImpl<BaseTestService> implements BaseTestServiceListableService {

	public BaseTestServiceListableServiceImpl(ListableBeanFactory listableBeanFactory) {
		super(listableBeanFactory, BaseTestService.class);
	}
}



