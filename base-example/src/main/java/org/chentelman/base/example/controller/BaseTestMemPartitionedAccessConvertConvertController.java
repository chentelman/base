package org.chentelman.base.example.controller;

import org.chentelman.base.example.entity.BaseTestDetails;
import org.chentelman.base.example.entity.BaseTestDomain;
import org.chentelman.base.example.entity.BaseTestSummary;
import org.chentelman.base.example.service.BaseTestMemPartitionedAccessConvertService;
import org.chentelman.base.module.rest.controller.BasePartitionedAccessConvertControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BaseTestMemPartitionedAccessConvertConvertController")
public class BaseTestMemPartitionedAccessConvertConvertController extends BasePartitionedAccessConvertControllerImpl<Long, String, BaseTestDetails, BaseTestSummary, BaseTestDomain> {

	protected BaseTestMemPartitionedAccessConvertConvertController(BaseTestMemPartitionedAccessConvertService service) {
		super(service);
	}

	@Override
	protected BaseTestDetails toDetails(BaseTestDomain domain) {
		BaseTestDetails details = new BaseTestDetails ();

		details.setId(domain.getId());
		details.setCode(domain.getCode());
		details.setName(domain.getName());

		return details;
	}

	@Override
	protected BaseTestSummary toSummary(BaseTestDomain domain) {
		BaseTestSummary summary = new BaseTestSummary ();

		summary.setId(domain.getId());
		summary.setCode(domain.getCode());

		return summary;
	}
}



