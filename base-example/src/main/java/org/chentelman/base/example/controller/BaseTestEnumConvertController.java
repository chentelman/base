package org.chentelman.base.example.controller;

import org.chentelman.base.example.entity.BaseEnumDetails;
import org.chentelman.base.example.entity.BaseEnumEntity;
import org.chentelman.base.example.entity.BaseEnumSummary;
import org.chentelman.base.example.service.BaseTestEnumService;
import org.chentelman.base.module.rest.controller.BaseAccessConvertControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BaseTestEnumConvertController")
public class BaseTestEnumConvertController extends BaseAccessConvertControllerImpl<BaseEnumEntity, BaseEnumDetails, BaseEnumSummary, String> {

	protected BaseTestEnumConvertController(BaseTestEnumService service) {
		super(service);
	}

	@Override
	protected BaseEnumDetails toDetails (BaseEnumEntity domain) {
		BaseEnumDetails details = new BaseEnumDetails ();

		details.setId(domain.getId());
		details.setCode(domain.getCode());
		details.setName(domain.getName());

		return details;
	}

	@Override
	protected BaseEnumSummary toSummary(BaseEnumEntity domain) {
		BaseEnumSummary summary = new BaseEnumSummary ();

		summary.setId(domain.getId());
		summary.setCode(domain.getCode());

		return summary;
	}
}



