package org.chentelman.base.testing.data.service;

import org.chentelman.base.testing.data.testdao.BaseTestDao;
import org.chentelman.base.testing.service.BaseListableServiceImpl;
import org.springframework.beans.factory.ListableBeanFactory;

/**
 * A base listable service implementation for {@link BaseTestDao} objects
 */
public class BaseTestDaoListableServiceImpl extends BaseListableServiceImpl<BaseTestDao> implements BaseTestDaoListableService {

	public BaseTestDaoListableServiceImpl(ListableBeanFactory listableBeanFactory) {
		super(listableBeanFactory, BaseTestDao.class);
	}
}



