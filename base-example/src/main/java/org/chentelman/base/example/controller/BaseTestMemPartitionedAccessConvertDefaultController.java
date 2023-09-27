package org.chentelman.base.example.controller;

import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.example.service.BaseTestMemPartitionedAccessDefaultService;
import org.chentelman.base.module.rest.controller.BasePartitionedAccessDefaultControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BaseTestMemPartitionedAccessConvertDefaultController")
public class BaseTestMemPartitionedAccessConvertDefaultController extends BasePartitionedAccessDefaultControllerImpl<BaseTestEntity, Long, String> {

	protected BaseTestMemPartitionedAccessConvertDefaultController(BaseTestMemPartitionedAccessDefaultService service) {
		super(service);
	}
}



