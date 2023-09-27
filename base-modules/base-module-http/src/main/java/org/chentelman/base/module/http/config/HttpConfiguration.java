package org.chentelman.base.module.http.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class HttpConfiguration {

	@Bean
	@ConditionalOnMissingBean(HttpExceptionHandler.class)
	public HttpExceptionHandler httpExceptionHandler () {
		return new HttpExceptionHandler ();
	}
}



