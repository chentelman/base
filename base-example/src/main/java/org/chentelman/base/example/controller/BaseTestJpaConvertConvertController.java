package org.chentelman.base.example.controller;

import org.chentelman.base.example.entity.BaseTestCreate;
import org.chentelman.base.example.entity.BaseTestDetails;
import org.chentelman.base.example.entity.BaseTestDomain;
import org.chentelman.base.example.entity.BaseTestSummary;
import org.chentelman.base.example.entity.BaseTestUpdate;
import org.chentelman.base.example.service.BaseTestJpaConvertService;
import org.chentelman.base.module.rest.controller.BaseConvertControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BaseTestJpaConvertConvertController")
public class BaseTestJpaConvertConvertController extends BaseConvertControllerImpl<Long, BaseTestCreate, BaseTestDetails, BaseTestSummary, BaseTestUpdate, BaseTestDomain> {

	protected BaseTestJpaConvertConvertController(BaseTestJpaConvertService service) {
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

	@Override
	protected BaseTestDomain toCreate(BaseTestCreate create) {
		BaseTestDomain domain = new BaseTestDomain ();

		domain.setId(create.getId());
		domain.setCode(create.getCode());
		domain.setName(create.getName());

		return domain;
	}

	@Override
	protected BaseTestDomain toUpdate(BaseTestUpdate update, Long id) {
		BaseTestDomain domain = new BaseTestDomain ();

		domain.setId(id);
		domain.setCode(update.getCode());
		domain.setName(update.getName());

		return domain;
	}
}



