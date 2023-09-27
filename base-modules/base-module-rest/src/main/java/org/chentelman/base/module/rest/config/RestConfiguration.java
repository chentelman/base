package org.chentelman.base.module.rest.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class RestConfiguration {

	@Bean
	@ConditionalOnMissingBean(RestExceptionHandler.class)
	public RestExceptionHandler restExceptionHandler () {
		return new RestExceptionHandler ();
	}
}



