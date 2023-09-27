package org.chentelman.base.example.stepdefs;

import java.util.List;

import org.chentelman.base.example.entity.BaseEnumEntity;
import org.chentelman.base.module.core.component.BaseComponent;
import org.chentelman.base.testing.data.service.BaseTestDaoListableService;
import org.chentelman.base.testing.data.testdao.BaseTestDao;
import org.chentelman.base.testing.utilities.BaseTestUtilities;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

public class ExampleUtilityStepDefinitions extends BaseComponent {

	private final BaseTestDaoListableService baseTestDaoListableService;

	public ExampleUtilityStepDefinitions (BaseTestDaoListableService baseTestDaoListableService) {
		this.baseTestDaoListableService = baseTestDaoListableService;
	}

	private void initialize (BaseTestDao testDao, String id, String code, String name) {
		DataTable data = BaseTestUtilities.buildDataTable(List.of(
			List.of("field", "value"),
			List.of(   "id", id),
			List.of( "code", code),
			List.of( "name", name)
		));

		testDao.initialize(data);
	}

	@Given("an initialized {string} storage")
	public void initialize (String domain) {
		BaseTestDao testDao = baseTestDaoListableService.getLatestListable(domain);

		try {
			// clear the current repository
			testDao.deleteAll();
		} catch (Exception e) {
			logger.info("Skip initialization : " + e.getMessage());
		}

		try {
			// create new entries
			for (BaseEnumEntity value : BaseEnumEntity.values()) {
				initialize (testDao, value.getCode(), value.getCode(), value.getName());
			}
		} catch (Exception e) {
			logger.info("Skip initialization : " + e.getMessage());
		}
	}

	@Given("an initialized partitioned {string} storage")
	public void initializePartition (String domain) {
		BaseTestDao testDao = baseTestDaoListableService.getLatestListable(domain);

		try {
			// clear the current repository
			testDao.deleteAll();
		} catch (Exception e) {
			logger.info("Skip initialization : " + e.getMessage());
		}

		try {
			String[] en = { "one", "two", "three", "four", "five",  "six"};
			String[] ja = {"ichi",  "ni",   "san",  "yon",   "go", "roku"};

			int len = Math.min(en.length, ja.length);

			// create new entries
			for (int i = 0; i < len; i += 1) {
				initialize (testDao, String.valueOf(i+1), "en", en[i]);
				initialize (testDao, String.valueOf(i+1), "ja", ja[i]);
			}
		} catch (Exception e) {
			logger.info("Skip initialization : " + e.getMessage());
		}
	}
}



