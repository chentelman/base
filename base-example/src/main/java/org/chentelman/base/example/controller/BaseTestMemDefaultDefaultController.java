package org.chentelman.base.example.controller;

import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.example.service.BaseTestMemDefaultService;
import org.chentelman.base.module.rest.controller.BaseDefaultControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BaseTestMemDefaultDefaultController")
public class BaseTestMemDefaultDefaultController extends BaseDefaultControllerImpl<BaseTestEntity, Long> {

	protected BaseTestMemDefaultDefaultController(BaseTestMemDefaultService service) {
		super(service);
	}
}



