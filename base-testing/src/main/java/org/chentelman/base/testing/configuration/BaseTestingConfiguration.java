package org.chentelman.base.testing.configuration;

import org.chentelman.base.module.core.component.BaseComponent;
import org.chentelman.base.testing.conveter.BaseDecoderService;
import org.chentelman.base.testing.conveter.BaseDecoderServiceImpl;
import org.chentelman.base.testing.conveter.BaseEncoderService;
import org.chentelman.base.testing.conveter.BaseEncoderServiceImpl;
import org.chentelman.base.testing.data.service.BaseTestDaoListableService;
import org.chentelman.base.testing.data.service.BaseTestDaoListableServiceImpl;
import org.chentelman.base.testing.objects.BaseObjectService;
import org.chentelman.base.testing.objects.BaseObjectServiceImpl;
import org.chentelman.base.testing.service.service.BaseTestServiceListableService;
import org.chentelman.base.testing.service.service.BaseTestServiceListableServiceImpl;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@AutoConfiguration
public class BaseTestingConfiguration extends BaseComponent {

	private ListableBeanFactory listableBeanFactory;

	public BaseTestingConfiguration (ListableBeanFactory listableBeanFactory) {
		this.listableBeanFactory = listableBeanFactory;
	}

	/* ========================================== *
	 * BaseTestDaoListableService bean generation *
	 * ========================================== */

	private BaseTestDaoListableService baseTestDaoListableService;

	/**
	 * @return a bean for {@link BaseTestDaoListableService}
	 */
	@Bean
	@ConditionalOnMissingBean(BaseTestDaoListableService.class)
	public BaseTestDaoListableService baseTestDaoListableService () {
		if (baseTestDaoListableService == null) {
			baseTestDaoListableService = new BaseTestDaoListableServiceImpl (listableBeanFactory);
		}

		return baseTestDaoListableService;
	}

	/* ============================================== *
	 * BaseTestServiceListableService bean generation *
	 * ============================================== */

	private BaseTestServiceListableService baseTestServiceListableService;

	/**
	 * @return a bean for {@link BaseTestServiceListableService}
	 */
	@Bean
	@ConditionalOnMissingBean(BaseTestServiceListableService.class)
	public BaseTestServiceListableService baseTestServiceListableService () {
		if (baseTestServiceListableService == null) {
			baseTestServiceListableService = new BaseTestServiceListableServiceImpl (listableBeanFactory);
		}

		return baseTestServiceListableService;
	}

	/* ================================= *
	 * BaseObjectService bean generation *
	 * ================================= */

	private BaseObjectService baseObjectService;

	/**
	 * @return a bean for {@link BaseObjectService}
	 */
	@Bean
	@ConditionalOnMissingBean(BaseObjectService.class)
	public BaseObjectService baseObjectService () {
		if (baseObjectService == null) {
			baseObjectService = new BaseObjectServiceImpl (baseDecoderService());
		}

		return baseObjectService;
	}

	/* ================================== *
	 * BaseEncoderService bean generation *
	 * ================================== */

	private BaseEncoderService baseEncoderService;

	/**
	 * @return a bean for {@link BaseEncoderService}
	 */
	@Bean
	@ConditionalOnMissingBean(BaseEncoderService.class)
	public BaseEncoderService baseEncoderService () {
		if (baseEncoderService == null) {
			baseEncoderService = new BaseEncoderServiceImpl (listableBeanFactory);
		}

		return baseEncoderService;
	}

	/* ================================== *
	 * BaseDecoderService bean generation *
	 * ================================== */

	private BaseDecoderService baseDecoderService;

	/**
	 * @return a bean for {@link BaseDecoderService}
	 */
	@Bean
	@ConditionalOnMissingBean(BaseDecoderService.class)
	public BaseDecoderService baseDecoderService () {
		if (baseDecoderService == null) {
			baseDecoderService = new BaseDecoderServiceImpl (listableBeanFactory);
		}

		return baseDecoderService;
	}

	/* ========================= *
	 * WebClient bean generation *
	 * ========================= */

	private WebClient webClient;

	/**
	 * @return a bean for {@link WebClient}
	 */
	@Bean
	@Qualifier("testingWebClient")
	public WebClient webClient (ServerProperties properties) {
		if (webClient == null) {
			webClient = WebClient.builder()
				.baseUrl("http://localhost:" + properties.getPort())
				.build();
		}

		return webClient;
	}
}



