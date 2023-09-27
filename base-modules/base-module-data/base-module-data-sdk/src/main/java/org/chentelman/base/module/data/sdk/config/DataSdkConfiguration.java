package org.chentelman.base.module.data.sdk.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class DataSdkConfiguration {

	@Bean
	@ConditionalOnMissingBean(DataSdkExceptionHandler.class)
	public DataSdkExceptionHandler dataSdkExceptionHandler () {
		return new DataSdkExceptionHandler ();
	}
}



