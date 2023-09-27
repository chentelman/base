package org.chentelman.base.testing.controller.webclient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.chentelman.base.module.core.component.BaseComponent;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BaseResponseResultsImpl extends BaseComponent implements BaseResponseResults {
	private HttpHeaders headers;
	private HttpStatus  status;
	private String      body;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validate (HttpStatus expectedStatus, boolean expectingBody, boolean multipleResults) {
		assertNotNull(expectedStatus);

		return
			status != null &&
			status.value() == expectedStatus.value() &&
			(body == null || body.isEmpty()) != expectingBody;
	}
}



