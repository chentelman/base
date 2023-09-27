package org.chentelman.base.module.data.jpa.config;

import org.chentelman.base.module.core.config.BaseExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Implementation of a jpa exception handler to handle system exceptions
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@ConditionalOnMissingBean(DataJpaExceptionHandler.class)
public class DataJpaExceptionHandler extends BaseExceptionHandler {

}



