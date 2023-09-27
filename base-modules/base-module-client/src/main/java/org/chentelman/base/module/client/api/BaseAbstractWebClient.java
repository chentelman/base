package org.chentelman.base.module.client.api;

import static org.chentelman.base.module.core.controller.BaseAccessAPIDefinition.PAGE_HEADER;
import static org.chentelman.base.module.core.controller.BaseAccessAPIDefinition.SIZE_HEADER;
import static org.chentelman.base.module.core.controller.BasePartitionedAccessAPIDefinition.PARTITION_HEADER;

import java.util.function.Consumer;

import org.chentelman.base.module.client.exception.BaseClientException;
import org.chentelman.base.module.core.component.BaseComponent;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Root class for all web clients.
 *
 * Serves as a storage for the object mapper and web client dependencies
 */
public class BaseAbstractWebClient extends BaseComponent {

	private final ObjectMapper mapper;
	private final WebClient client;

	/**
	 * Instantiation of the root web client class.
	 *
	 * @param mapper an object mapper to convert objects to json
	 * @param client the web client used to send remote requests
	 */
	protected BaseAbstractWebClient (ObjectMapper mapper, WebClient client) {
		this.mapper = mapper;
		this.client = client;
	}

	/**
	 * Generate a get request and return the response spec
	 *
	 * @param uri the path of the request
	 * @return the response spec generated for the request
	 */
	protected ResponseSpec get (String uri) {
		return client.get()
			.uri(uri)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve();
	}

	/**
	 * Generate a get request and return the response spec
	 *
	 * @param uri the path of the request
	 * @param headers any headers for the request
	 * @return the response spec generated for the request
	 */
	protected ResponseSpec get (String uri, Consumer<HttpHeaders> headers) {
		return client.get()
			.uri(uri)
			.accept(MediaType.APPLICATION_JSON)
			.headers(headers)
			.retrieve();
	}

	/**
	 * Generate a post request and return the response spec
	 *
	 * @param uri the path of the request
	 * @param headers any headers for the request
	 * @param data an object representing the request body
	 * @return the response spec generated for the request
	 */
	protected <T> ResponseSpec post (String uri, T data) {
		String body;

		try {
			body = mapper.writer().writeValueAsString(data);
		} catch (JsonProcessingException e) {
			throw BaseClientException.of(e.getMessage(), e);
		}

		return client.post()
			.uri(uri)
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(body)
			.retrieve();
	}

	/**
	 * Generate a post request and return the response spec
	 *
	 * @param uri the path of the request
	 * @param headers any headers for the request
	 * @param data an object representing the request body
	 * @return the response spec generated for the request
	 */
	protected <T> ResponseSpec post (String uri, Consumer<HttpHeaders> headers, T data) {
		String body;

		try {
			body = mapper.writer().writeValueAsString(data);
		} catch (JsonProcessingException e) {
			throw BaseClientException.of(e.getMessage(), e);
		}

		return client.post()
			.uri(uri)
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.headers(headers)
			.bodyValue(body)
			.retrieve();
	}

	/**
	 * Generate a put request and return the response spec
	 *
	 * @param uri the path of the request
	 * @param headers any headers for the request
	 * @param data an object representing the request body
	 * @return the response spec generated for the request
	 */
	protected <T> ResponseSpec put (String uri, T data) {
		String body;

		try {
			body = mapper.writer().writeValueAsString(data);
		} catch (JsonProcessingException e) {
			throw BaseClientException.of(e.getMessage(), e);
		}

		return client.put()
			.uri(uri)
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(body)
			.retrieve();
	}

	/**
	 * Generate a put request and return the response spec
	 *
	 * @param uri the path of the request
	 * @param headers any headers for the request
	 * @param data an object representing the request body
	 * @return the response spec generated for the request
	 */
	protected <T> ResponseSpec put (String uri, Consumer<HttpHeaders> headers, T data) {
		String body;

		try {
			body = mapper.writer().writeValueAsString(data);
		} catch (JsonProcessingException e) {
			throw BaseClientException.of(e.getMessage(), e);
		}

		return client.put()
			.uri(uri)
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.headers(headers)
			.bodyValue(body)
			.retrieve();
	}

	/**
	 * Generate a delete request and return the response spec
	 *
	 * @param uri the path of the request
	 * @param headers any headers for the request
	 * @return the response spec generated for the request
	 */
	protected ResponseSpec delete (String uri) {
		return client.delete()
			.uri(uri)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve();
	}

	/**
	 * Generate a delete request and return the response spec
	 *
	 * @param uri the path of the request
	 * @param headers any headers for the request
	 * @return the response spec generated for the request
	 */
	protected ResponseSpec delete (String uri, Consumer<HttpHeaders> headers) {
		return client.delete()
			.uri(uri)
			.accept(MediaType.APPLICATION_JSON)
			.headers(headers)
			.retrieve();
	}

	protected Consumer<HttpHeaders> headers (String partition) {
		return headers -> headers.add(PARTITION_HEADER, partition);
	}

	protected Consumer<HttpHeaders> headers (int page, int size) {
		return headers -> {
			headers.add(PAGE_HEADER, String.valueOf(page));
			headers.add(SIZE_HEADER, String.valueOf(size));
		};
	}

	protected Consumer<HttpHeaders> headers (int page, int size, String partition) {
		return headers -> {
			headers.add(PAGE_HEADER, String.valueOf(page));
			headers.add(SIZE_HEADER, String.valueOf(size));
			headers.add(PARTITION_HEADER, partition);
		};
	}
}



