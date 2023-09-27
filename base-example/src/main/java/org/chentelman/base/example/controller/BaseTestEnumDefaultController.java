package org.chentelman.base.example.controller;

import org.chentelman.base.example.entity.BaseEnumEntity;
import org.chentelman.base.example.service.BaseTestEnumService;
import org.chentelman.base.module.rest.controller.BaseAccessDefaultControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BaseTestEnumDefaultController")
public class BaseTestEnumDefaultController extends BaseAccessDefaultControllerImpl<BaseEnumEntity, String> {

	protected BaseTestEnumDefaultController(BaseTestEnumService service) {
		super(service);
	}
}



