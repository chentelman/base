package org.chentelman.base.example.controller;

import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.example.service.BaseTestJpaDefaultService;
import org.chentelman.base.module.rest.controller.BaseDefaultControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BaseTestJpaDefaultDefaultController")
public class BaseTestJpaDefaultDefaultController extends BaseDefaultControllerImpl<BaseTestEntity, Long> {

	protected BaseTestJpaDefaultDefaultController(BaseTestJpaDefaultService service) {
		super(service);
	}
}



