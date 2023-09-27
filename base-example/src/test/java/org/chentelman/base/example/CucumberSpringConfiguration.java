package org.chentelman.base.example;

import org.chentelman.base.module.data.jpa.config.DataJpaConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Bean;

import io.cucumber.spring.CucumberContextConfiguration;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
public class CucumberSpringConfiguration {

	@Bean
	public DataJpaConfiguration dataJpaConfiguration () {
		return new DataJpaConfiguration ();
	}
}



