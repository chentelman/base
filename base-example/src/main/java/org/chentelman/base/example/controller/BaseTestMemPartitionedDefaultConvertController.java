package org.chentelman.base.example.controller;

import org.chentelman.base.example.entity.BaseTestCreate;
import org.chentelman.base.example.entity.BaseTestDetails;
import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.example.entity.BaseTestSummary;
import org.chentelman.base.example.entity.BaseTestUpdate;
import org.chentelman.base.example.service.BaseTestMemPartitionedDefaultService;
import org.chentelman.base.module.rest.controller.BasePartitionedConvertControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BaseTestMemPartitionedDefaultConvertController")
public class BaseTestMemPartitionedDefaultConvertController extends BasePartitionedConvertControllerImpl<Long, String, BaseTestCreate, BaseTestDetails, BaseTestSummary, BaseTestUpdate, BaseTestEntity> {

	protected BaseTestMemPartitionedDefaultConvertController(BaseTestMemPartitionedDefaultService service) {
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

	@Override
	protected BaseTestEntity toCreate(BaseTestCreate create) {
		BaseTestEntity entity = new BaseTestEntity ();

		entity.setId(create.getId());
		entity.setCode(create.getCode());
		entity.setName(create.getName());

		return entity;
	}

	@Override
	protected BaseTestEntity toUpdate(BaseTestUpdate update, Long id) {
		BaseTestEntity entity = new BaseTestEntity ();

		entity.setId(id);
		entity.setCode(update.getCode());
		entity.setName(update.getName());

		return entity;
	}
}



