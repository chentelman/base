package org.chentelman.base.example.controller;

import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.example.service.BaseTestMemPartitionedDefaultService;
import org.chentelman.base.module.rest.controller.BasePartitionedDefaultControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BaseTestMemPartitionedDefaultDefaultController")
public class BaseTestMemPartitionedDefaultDefaultController extends BasePartitionedDefaultControllerImpl<BaseTestEntity, Long, String> {

	protected BaseTestMemPartitionedDefaultDefaultController(BaseTestMemPartitionedDefaultService service) {
		super(service);
	}
}



