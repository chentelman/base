package org.chentelman.base.testing.configuration;

import java.util.Random;

import org.chentelman.base.module.core.component.BaseComponent;
import org.chentelman.base.testing.controller.service.BaseTestRequestListableService;
import org.chentelman.base.testing.controller.service.BaseTestRequestListableServiceImpl;
import org.chentelman.base.testing.conveter.BaseDecoderService;
import org.chentelman.base.testing.json.BaseCompositeKeyService;
import org.chentelman.base.testing.json.BaseCompositeKeyServiceImpl;
import org.chentelman.base.testing.json.BaseJsonGeneratorService;
import org.chentelman.base.testing.json.BaseJsonGeneratorServiceImpl;
import org.chentelman.base.testing.json.BaseJsonService;
import org.chentelman.base.testing.json.BaseJsonServiceImpl;
import org.chentelman.base.testing.json.generators.BaseJsonBigIntegerGenerator;
import org.chentelman.base.testing.json.generators.BaseJsonBooleanGenerator;
import org.chentelman.base.testing.json.generators.BaseJsonCompositeIdGenerator;
import org.chentelman.base.testing.json.generators.BaseJsonFractionGenerator;
import org.chentelman.base.testing.json.generators.BaseJsonIntegerGenerator;
import org.chentelman.base.testing.json.generators.BaseJsonJsonGenerator;
import org.chentelman.base.testing.json.generators.BaseJsonLatestArrayGenerator;
import org.chentelman.base.testing.json.generators.BaseJsonLatestKeyGenerator;
import org.chentelman.base.testing.json.generators.BaseJsonLatestObjectGenerator;
import org.chentelman.base.testing.json.generators.BaseJsonLongGenerator;
import org.chentelman.base.testing.json.generators.BaseJsonNullGenerator;
import org.chentelman.base.testing.json.generators.BaseJsonRandomStringGenerator;
import org.chentelman.base.testing.json.generators.BaseJsonStringGenerator;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class BaseTestJsonGeneratorConfiguration extends BaseComponent {

	private ListableBeanFactory listableBeanFactory;

	public BaseTestJsonGeneratorConfiguration (ListableBeanFactory listableBeanFactory) {
		this.listableBeanFactory = listableBeanFactory;
	}

	/* =========================================== *
	 * BaseJsonBigIntegerGenerator bean generation *
	 * =========================================== */

	private BaseJsonBigIntegerGenerator baseJsonBigIntegerGenerator;

	@Bean
	public BaseJsonBigIntegerGenerator baseJsonBigIntegerGenerator () {
		if (baseJsonBigIntegerGenerator == null) {
			baseJsonBigIntegerGenerator = new BaseJsonBigIntegerGenerator ();
		}

		return baseJsonBigIntegerGenerator;
	}

	/* ======================================== *
	 * BaseJsonBooleanGenerator bean generation *
	 * ======================================== */

	private BaseJsonBooleanGenerator baseJsonBooleanGenerator;

	@Bean
	public BaseJsonBooleanGenerator baseJsonBooleanGenerator () {
		if (baseJsonBooleanGenerator == null) {
			baseJsonBooleanGenerator = new BaseJsonBooleanGenerator ();
		}

		return baseJsonBooleanGenerator;
	}

	/* ============================================ *
	 * BaseJsonCompositeIdGenerator bean generation *
	 * ============================================ */

	private BaseJsonCompositeIdGenerator baseJsonCompositeIdGenerator;

	@Bean
	public BaseJsonCompositeIdGenerator baseJsonCompositeIdGenerator () {
		if (baseJsonCompositeIdGenerator == null) {
			baseJsonCompositeIdGenerator = new BaseJsonCompositeIdGenerator (baseCompositeKeyService());
		}

		return baseJsonCompositeIdGenerator;
	}

	/* ========================================= *
	 * BaseJsonFractionGenerator bean generation *
	 * ========================================= */

	private BaseJsonFractionGenerator baseJsonFractionGenerator;

	@Bean
	public BaseJsonFractionGenerator baseJsonFractionGenerator () {
		if (baseJsonFractionGenerator == null) {
			baseJsonFractionGenerator = new BaseJsonFractionGenerator ();
		}

		return baseJsonFractionGenerator;
	}

	/* ======================================== *
	 * BaseJsonIntegerGenerator bean generation *
	 * ======================================== */

	private BaseJsonIntegerGenerator baseJsonIntegerGenerator;

	@Bean
	public BaseJsonIntegerGenerator baseJsonIntegerGenerator () {
		if (baseJsonIntegerGenerator == null) {
			baseJsonIntegerGenerator = new BaseJsonIntegerGenerator ();
		}

		return baseJsonIntegerGenerator;
	}

	/* ===================================== *
	 * BaseJsonJsonGenerator bean generation *
	 * ===================================== */

	private BaseJsonJsonGenerator baseJsonJsonGenerator;

	@Bean
	public BaseJsonJsonGenerator baseJsonJsonGenerator () {
		if (baseJsonJsonGenerator == null) {
			baseJsonJsonGenerator = new BaseJsonJsonGenerator (baseJsonService());
		}

		return baseJsonJsonGenerator;
	}

	/* ============================================ *
	 * BaseJsonLatestArrayGenerator bean generation *
	 * ============================================ */

	private BaseJsonLatestArrayGenerator baseJsonLatestArrayGenerator;

	@Bean
	public BaseJsonLatestArrayGenerator baseJsonLatestArrayGenerator () {
		if (baseJsonLatestArrayGenerator == null) {
			baseJsonLatestArrayGenerator = new BaseJsonLatestArrayGenerator (baseTestRequestListableService());
		}

		return baseJsonLatestArrayGenerator;
	}

	/* ========================================== *
	 * BaseJsonLatestKeyGenerator bean generation *
	 * ========================================== */

	private BaseJsonLatestKeyGenerator baseJsonLatestKeyGenerator;

	@Bean
	public BaseJsonLatestKeyGenerator baseJsonLatestKeyGenerator () {
		if (baseJsonLatestKeyGenerator == null) {
			baseJsonLatestKeyGenerator = new BaseJsonLatestKeyGenerator (baseTestRequestListableService());
		}

		return baseJsonLatestKeyGenerator;
	}

	/* ============================================= *
	 * BaseJsonLatestObjectGenerator bean generation *
	 * ============================================= */

	private BaseJsonLatestObjectGenerator baseJsonLatestObjectGenerator;

	@Bean
	public BaseJsonLatestObjectGenerator baseJsonLatestObjectGenerator () {
		if (baseJsonLatestObjectGenerator == null) {
			baseJsonLatestObjectGenerator = new BaseJsonLatestObjectGenerator (baseTestRequestListableService());
		}

		return baseJsonLatestObjectGenerator;
	}

	/* ===================================== *
	 * BaseJsonLongGenerator bean generation *
	 * ===================================== */

	private BaseJsonLongGenerator baseJsonLongGenerator;

	@Bean
	public BaseJsonLongGenerator baseJsonLongGenerator () {
		if (baseJsonLongGenerator == null) {
			baseJsonLongGenerator = new BaseJsonLongGenerator ();
		}

		return baseJsonLongGenerator;
	}

	/* ===================================== *
	 * BaseJsonNullGenerator bean generation *
	 * ===================================== */

	private BaseJsonNullGenerator baseJsonNullGenerator;

	@Bean
	public BaseJsonNullGenerator baseJsonNullGenerator () {
		if (baseJsonNullGenerator == null) {
			baseJsonNullGenerator = new BaseJsonNullGenerator ();
		}

		return baseJsonNullGenerator;
	}

	/* ============================================= *
	 * BaseJsonRandomStringGenerator bean generation *
	 * ============================================= */

	private BaseJsonRandomStringGenerator baseJsonRandomStringGenerator;

	@Bean
	public BaseJsonRandomStringGenerator baseJsonRandomStringGenerator () {
		if (baseJsonRandomStringGenerator == null) {
			baseJsonRandomStringGenerator = new BaseJsonRandomStringGenerator (new Random(System.currentTimeMillis()));
		}

		return baseJsonRandomStringGenerator;
	}

	/* ======================================= *
	 * BaseJsonStringGenerator bean generation *
	 * ======================================= */

	private BaseJsonStringGenerator baseJsonStringGenerator;

	@Bean
	public BaseJsonStringGenerator baseJsonStringGenerator () {
		if (baseJsonStringGenerator == null) {
			baseJsonStringGenerator = new BaseJsonStringGenerator ();
		}

		return baseJsonStringGenerator;
	}

	/* ======================================== *
	 * BaseJsonGeneratorService bean generation *
	 * ======================================== */

	private BaseJsonGeneratorService baseJsonGeneratorService;

	/**
	 * @return a bean for {@link BaseDecoderService}
	 */
	@Bean
	@ConditionalOnMissingBean(BaseDecoderService.class)
	public BaseJsonGeneratorService baseJsonGeneratorService () {
		if (baseJsonGeneratorService == null) {
			baseJsonGeneratorService = new BaseJsonGeneratorServiceImpl (listableBeanFactory);
		}

		return baseJsonGeneratorService;
	}

	/* ============================================== *
	 * BaseTestRequestListableService bean generation *
	 * ============================================== */

	private BaseTestRequestListableService baseTestRequestListableService;

	/**
	 * @return a bean for {@link BaseTestRequestListableService}
	 */
	@Bean
	@ConditionalOnMissingBean(BaseTestRequestListableService.class)
	public BaseTestRequestListableService baseTestRequestListableService () {
		if (baseTestRequestListableService == null) {
			baseTestRequestListableService = new BaseTestRequestListableServiceImpl (listableBeanFactory);
		}

		return baseTestRequestListableService;
	}

	/* =============================== *
	 * BaseJsonService bean generation *
	 * =============================== */

	private BaseJsonService baseJsonService;

	/**
	 * @return a bean for {@link BaseJsonService}
	 */
	@Bean
	@ConditionalOnMissingBean(BaseJsonService.class)
	public BaseJsonService baseJsonService () {
		if (baseJsonService == null) {
			baseJsonService = new BaseJsonServiceImpl (baseJsonGeneratorService());
		}

		return baseJsonService;
	}

	/* ======================================= *
	 * BaseCompositeKeyService bean generation *
	 * ======================================= */

	private BaseCompositeKeyService baseCompositeKeyService;

	/**
	 * @return a bean for {@link BaseCompositeKeyService}
	 */
	@Bean
	@ConditionalOnMissingBean(BaseCompositeKeyService.class)
	public BaseCompositeKeyService baseCompositeKeyService () {
		if (baseCompositeKeyService == null) {
			baseCompositeKeyService = new BaseCompositeKeyServiceImpl (baseJsonService());
		}

		return baseCompositeKeyService;
	}
}



