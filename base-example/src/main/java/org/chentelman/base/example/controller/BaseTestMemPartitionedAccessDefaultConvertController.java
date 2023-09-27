package org.chentelman.base.example.controller;

import org.chentelman.base.example.entity.BaseTestDetails;
import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.example.entity.BaseTestSummary;
import org.chentelman.base.example.service.BaseTestMemPartitionedAccessDefaultService;
import org.chentelman.base.module.rest.controller.BasePartitionedAccessConvertControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BaseTestMemPartitionedAccessDefaultConvertController")
public class BaseTestMemPartitionedAccessDefaultConvertController extends BasePartitionedAccessConvertControllerImpl<Long, String, BaseTestDetails, BaseTestSummary, BaseTestEntity> {

	protected BaseTestMemPartitionedAccessDefaultConvertController(BaseTestMemPartitionedAccessDefaultService service) {
		super(service);
	}

	@Override
	protected BaseTestDetails toDetails(BaseTestEntity entity) {
		BaseTestDetails details = new BaseTestDetails ();

		details.setId(entity.getId());
		details.setCode(entity.getCode());
		details.setName(entity.getName());

		return details;
	}

	@Override
	protected BaseTestSummary toSummary(BaseTestEntity entity) {
		BaseTestSummary summary = new BaseTestSummary ();

		summary.setId(entity.getId());
		summary.setCode(entity.getCode());

		return summary;
	}
}



