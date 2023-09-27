package org.chentelman.base.example.controller;

import org.chentelman.base.example.entity.BaseTestDomain;
import org.chentelman.base.example.service.BaseTestMemPartitionedConvertService;
import org.chentelman.base.module.rest.controller.BasePartitionedDefaultControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BaseTestMemPartitionedConvertDefaultController")
public class BaseTestMemPartitionedConvertDefaultController extends BasePartitionedDefaultControllerImpl<BaseTestDomain, Long, String> {

	protected BaseTestMemPartitionedConvertDefaultController(BaseTestMemPartitionedConvertService service) {
		super(service);
	}
}



