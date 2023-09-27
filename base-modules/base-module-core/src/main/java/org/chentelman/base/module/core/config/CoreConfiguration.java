package org.chentelman.base.module.core.config;

import org.chentelman.base.module.core.component.BaseComponent;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class CoreConfiguration extends BaseComponent {

	@Bean
	@ConditionalOnMissingBean(CoreExceptionHandler.class)
	public CoreExceptionHandler CoreExceptionHandler () {
		return new CoreExceptionHandler();
	}
}



