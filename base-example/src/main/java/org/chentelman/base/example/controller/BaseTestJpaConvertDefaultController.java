package org.chentelman.base.example.controller;

import org.chentelman.base.example.entity.BaseTestDomain;
import org.chentelman.base.example.service.BaseTestJpaConvertService;
import org.chentelman.base.module.rest.controller.BaseDefaultControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BaseTestJpaConvertDefaultController")
public class BaseTestJpaConvertDefaultController extends BaseDefaultControllerImpl<BaseTestDomain, Long> {

	protected BaseTestJpaConvertDefaultController(BaseTestJpaConvertService service) {
		super(service);
	}
}



