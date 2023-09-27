package org.chentelman.base.testing.data.stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.chentelman.base.testing.data.service.BaseTestDaoListableService;
import org.chentelman.base.testing.data.testdao.BaseTestDao;
import org.chentelman.base.testing.data.testdao.BaseTestPartitionedDao;
import org.mockito.Mockito;

public class BaseTestDaoAbstractStepDefinitionsHelper {

	protected final BaseTestPartitionedDao dao = Mockito.mock(BaseTestPartitionedDao.class);
	protected final BaseTestDaoListableService service = new BaseTestDaoListableService () {

		private BaseTestDao latest = null;

		@Override
		public BaseTestDao getListable(String name) {
			assertEquals("valid", name);
			return dao;
		}

		@Override
		public BaseTestDao getLatestListable(String name) {
			latest = getListable(name);
			return dao;
		}

		@Override
		public BaseTestDao getLatestListable() {
			return latest;
		}

		@Override
		public void clear() {
			latest = null;
		}

	};

}



