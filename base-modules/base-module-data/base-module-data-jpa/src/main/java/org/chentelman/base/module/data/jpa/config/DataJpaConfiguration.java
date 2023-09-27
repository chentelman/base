package org.chentelman.base.module.data.jpa.config;

import org.chentelman.base.module.core.component.BaseComponent;
import org.chentelman.base.module.data.jpa.repository.BaseRepository;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@EnableJpaRepositories(repositoryBaseClass = BaseRepository.class)
@AutoConfigureOrder(Integer.MIN_VALUE)
public class DataJpaConfiguration extends BaseComponent {

	@Bean
	@ConditionalOnMissingBean(DataJpaExceptionHandler.class)
	public DataJpaExceptionHandler dataJpaExceptionHandler () {
		return new DataJpaExceptionHandler ();
	}
}



