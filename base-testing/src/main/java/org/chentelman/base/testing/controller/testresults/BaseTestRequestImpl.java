package org.chentelman.base.testing.controller.testresults;

import org.chentelman.base.testing.component.BaseTestComponent;
import org.chentelman.base.testing.controller.webclient.BaseResponseResults;
import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public abstract class BaseTestRequestImpl extends BaseTestComponent implements BaseTestRequest {
	protected boolean success;
	protected BaseResponseResults latestResults;

	/**
	 * Internal method to setup the results internally
	 */
	protected void results (BaseResponseResults results, HttpStatus expectedStatus, boolean expectingBody, boolean multipleResults) {
		success = results.validate (expectedStatus, expectingBody, multipleResults);
		latestResults = results;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void accessResults(BaseResponseResults results, boolean multipleResults) {
		results (results, HttpStatus.OK, true, multipleResults);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void findAllResults (BaseResponseResults results) {
		results (results, HttpStatus.OK, true, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createResults (BaseResponseResults results) {
		results (results, HttpStatus.CREATED, true, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateResults (BaseResponseResults results) {
		results (results, HttpStatus.NO_CONTENT, false, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteResults (BaseResponseResults results) {
		results (results, HttpStatus.NO_CONTENT, false, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear () {
		success = false;
		latestResults = null;
	}

}



