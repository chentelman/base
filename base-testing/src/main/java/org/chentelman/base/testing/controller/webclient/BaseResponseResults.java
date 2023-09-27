package org.chentelman.base.testing.controller.webclient;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public interface BaseResponseResults {

	public HttpHeaders getHeaders ();
	public HttpStatus  getStatus  ();
	public String      getBody    ();

	public boolean validate (HttpStatus expectedStatus, boolean expectingBody, boolean multipleResults);
}



