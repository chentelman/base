package org.chentelman.base.example.controller;

import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.example.service.BaseTestMemPartitionedAccessDefaultService;
import org.chentelman.base.module.rest.controller.BasePartitionedAccessDefaultControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BaseTestMemPartitionedAccessDefaultDefaultController")
public class BaseTestMemPartitionedAccessDefaultDefaultController extends BasePartitionedAccessDefaultControllerImpl<BaseTestEntity, Long, String> {

	protected BaseTestMemPartitionedAccessDefaultDefaultController(BaseTestMemPartitionedAccessDefaultService service) {
		super(service);
	}
}



