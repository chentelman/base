package org.chentelman.base.example.controller;

import org.chentelman.base.example.entity.BaseTestDomain;
import org.chentelman.base.example.service.BaseTestMemAccessConvertService;
import org.chentelman.base.module.rest.controller.BaseDefaultControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BaseTestMemAccessConvertDefaultController")
public class BaseTestMemAccessConvertDefaultController extends BaseDefaultControllerImpl<BaseTestDomain, Long> {

	protected BaseTestMemAccessConvertDefaultController(BaseTestMemAccessConvertService service) {
		super(service);
	}
}



