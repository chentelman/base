package org.chentelman.base.example.controller;

import org.chentelman.base.example.entity.BaseTestDomain;
import org.chentelman.base.example.service.BaseTestMemConvertService;
import org.chentelman.base.module.rest.controller.BaseDefaultControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BaseTestMemConvertDefaultController")
public class BaseTestMemConvertDefaultController extends BaseDefaultControllerImpl<BaseTestDomain, Long> {

	protected BaseTestMemConvertDefaultController(BaseTestMemConvertService service) {
		super(service);
	}
}



