package org.chentelman.base.example.controller;

import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.example.service.BaseTestMemAccessDefaultService;
import org.chentelman.base.module.rest.controller.BaseAccessDefaultControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BaseTestMemAccessDefaultDefaultController")
public class BaseTestMemAccessDefaultDefaultController extends BaseAccessDefaultControllerImpl<BaseTestEntity, Long> {

	protected BaseTestMemAccessDefaultDefaultController(BaseTestMemAccessDefaultService service) {
		super(service);
	}
}



