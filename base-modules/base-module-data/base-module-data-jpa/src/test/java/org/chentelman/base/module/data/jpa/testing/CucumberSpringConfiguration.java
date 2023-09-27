package org.chentelman.base.module.data.jpa.testing;

import org.chentelman.base.module.data.jpa.config.DataJpaConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import io.cucumber.spring.CucumberContextConfiguration;

@SpringBootTest
@CucumberContextConfiguration
public class CucumberSpringConfiguration {

	@Bean
	public DataJpaConfiguration dataJpaConfiguration () {
		return new DataJpaConfiguration ();
	}
}



