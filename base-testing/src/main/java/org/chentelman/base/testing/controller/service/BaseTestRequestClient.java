package org.chentelman.base.testing.controller.service;

import static org.chentelman.base.module.core.controller.BaseAccessAPIDefinition.PAGE_HEADER;
import static org.chentelman.base.module.core.controller.BaseAccessAPIDefinition.SIZE_HEADER;
import static org.chentelman.base.module.core.controller.BasePartitionedAccessAPIDefinition.PARTITION_HEADER;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.chentelman.base.testing.component.BaseTestComponent;
import org.chentelman.base.testing.controller.testresults.BaseTestRequest;
import org.chentelman.base.testing.controller.webclient.BaseResponseResults;
import org.chentelman.base.testing.controller.webclient.BaseResponseResultsImpl;
import org.chentelman.base.testing.utilities.BaseTestUtilities;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class BaseTestRequestClient extends BaseTestComponent {

	protected final WebClient webClient;
	protected final MediaType mediaType;

	protected BaseTestRequestClient (WebClient webClient, MediaType mediaType) {
		this.webClient = webClient;
		this.mediaType = mediaType;
	}

	/* ================= *
	 * Create operations *
	 * ================= */

	protected void add (BaseTestRequest request, String body, MediaType contentType) {
		ResponseEntity<String> response = webClient.post()
			.uri(request.getPath())
			.accept(mediaType)
			.contentType(contentType)
			.bodyValue(body)
			.retrieve()
			.onStatus(HttpStatusCode::isError, e -> Mono.empty())
			.toEntity(String.class)
			.block();

		assertNotNull(response);

		if (response != null) {
			BaseResponseResults results = BaseResponseResultsImpl.builder()
				.body(response.getBody())
				.headers(response.getHeaders())
				.status(BaseTestUtilities.toHttpStatus(response.getStatusCode()))
				.build();

			request.createResults(results);
		}
	}

	/* =============== *
	 * Read operations *
	 * =============== */

	protected void readAll (BaseTestRequest request) {
		ResponseEntity<String> response = webClient.get()
			.uri(request.getPath())
			.accept(mediaType)
			.retrieve()
			.onStatus(HttpStatusCode::isError, e -> Mono.empty())
			.toEntity(String.class)
			.block();

		assertNotNull(response);

		if (response != null) {
			BaseResponseResults results = BaseResponseResultsImpl.builder()
				.body(response.getBody())
				.headers(response.getHeaders())
				.status(BaseTestUtilities.toHttpStatus(response.getStatusCode()))
				.build();

			request.findAllResults(results);
		}
	}

	protected void readAll (BaseTestRequest request, String partition) {
		ResponseEntity<String> response = webClient.get()
			.uri(request.getPath())
			.accept(mediaType)
			.header(PARTITION_HEADER, partition)
			.retrieve()
			.onStatus(HttpStatusCode::isError, e -> Mono.empty())
			.toEntity(String.class)
			.block();

		assertNotNull(response);

		if (response != null) {
			BaseResponseResults results = BaseResponseResultsImpl.builder()
				.body(response.getBody())
				.headers(response.getHeaders())
				.status(BaseTestUtilities.toHttpStatus(response.getStatusCode()))
				.build();

			request.findAllResults(results);
		}
	}

	protected void readAll (BaseTestRequest request, int page, int size) {
		ResponseEntity<String> response = webClient.get()
			.uri(request.getPath())
			.accept(mediaType)
			.header(PAGE_HEADER, String.valueOf(page))
			.header(SIZE_HEADER, String.valueOf(size))
			.retrieve()
			.onStatus(HttpStatusCode::isError, e -> Mono.empty())
			.toEntity(String.class)
			.block();

		assertNotNull(response);

		if (response != null) {
			BaseResponseResults results = BaseResponseResultsImpl.builder()
				.body(response.getBody())
				.headers(response.getHeaders())
				.status(BaseTestUtilities.toHttpStatus(response.getStatusCode()))
				.build();

			request.findAllResults(results);
		}
	}

	protected void readAll (BaseTestRequest request, int page, int size, String partition) {
		ResponseEntity<String> response = webClient.get()
			.uri(request.getPath())
			.accept(mediaType)
			.header(PAGE_HEADER, String.valueOf(page))
			.header(SIZE_HEADER, String.valueOf(size))
			.header(PARTITION_HEADER, partition)
			.retrieve()
			.onStatus(HttpStatusCode::isError, e -> Mono.empty())
			.toEntity(String.class)
			.block();

		assertNotNull(response);

		if (response != null) {
			BaseResponseResults results = BaseResponseResultsImpl.builder()
				.body(response.getBody())
				.headers(response.getHeaders())
				.status(BaseTestUtilities.toHttpStatus(response.getStatusCode()))
				.build();

			request.findAllResults(results);
		}
	}

	protected void read (BaseTestRequest request, String id) {
		ResponseEntity<String> response = webClient.get()
			.uri(request.getPath() + "/" + id)
			.accept(mediaType)
			.retrieve()
			.onStatus(HttpStatusCode::isError, e -> Mono.empty())
			.toEntity(String.class)
			.block();

		assertNotNull(response);

		if (response != null) {
			BaseResponseResults results = BaseResponseResultsImpl.builder()
				.body(response.getBody())
				.headers(response.getHeaders())
				.status(BaseTestUtilities.toHttpStatus(response.getStatusCode()))
				.build();

			request.accessResults(results);
		}
	}

	protected void read (BaseTestRequest request, int page, int size, String id) {
		ResponseEntity<String> response = webClient.get()
			.uri(request.getPath() + "/" + id)
			.accept(mediaType)
			.header(PAGE_HEADER, String.valueOf(page))
			.header(SIZE_HEADER, String.valueOf(size))
			.retrieve()
			.onStatus(HttpStatusCode::isError, e -> Mono.empty())
			.toEntity(String.class)
			.block();

		assertNotNull(response);

		if (response != null) {
			BaseResponseResults results = BaseResponseResultsImpl.builder()
				.body(response.getBody())
				.headers(response.getHeaders())
				.status(BaseTestUtilities.toHttpStatus(response.getStatusCode()))
				.build();

			request.accessResults(results);
		}
	}

	protected void read (BaseTestRequest request, String id, String partition) {
		ResponseEntity<String> response = webClient.get()
			.uri(request.getPath() + "/" + id)
			.accept(mediaType)
			.header(PARTITION_HEADER, partition)
			.retrieve()
			.onStatus(HttpStatusCode::isError, e -> Mono.empty())
			.toEntity(String.class)
			.block();

		assertNotNull(response);

		if (response != null) {
			BaseResponseResults results = BaseResponseResultsImpl.builder()
				.body(response.getBody())
				.headers(response.getHeaders())
				.status(BaseTestUtilities.toHttpStatus(response.getStatusCode()))
				.build();

			request.accessResults(results, false);
		}
	}

	/* ================= *
	 * Update operations *
	 * ================= */

	protected void update (BaseTestRequest request, String body, MediaType contentType, String id) {
		ResponseEntity<String> response = webClient.put()
				.uri(request.getPath() + "/" + id)
				.accept(mediaType)
				.contentType(contentType)
				.bodyValue(body)
				.retrieve()
				.onStatus(HttpStatusCode::isError, e -> Mono.empty())
				.toEntity(String.class)
				.block();

		assertNotNull(response);

		if (response != null) {
			BaseResponseResults results = BaseResponseResultsImpl.builder()
				.body(response.getBody())
				.headers(response.getHeaders())
				.status(BaseTestUtilities.toHttpStatus(response.getStatusCode()))
				.build();

			request.updateResults(results);
		}
	}

	protected void update (BaseTestRequest request, String body, MediaType contentType, String id, String partition) {
		ResponseEntity<String> response = webClient.put()
				.uri(request.getPath() + "/" + id)
				.accept(mediaType)
				.header(PARTITION_HEADER, partition)
				.contentType(contentType)
				.bodyValue(body)
				.retrieve()
				.onStatus(HttpStatusCode::isError, e -> Mono.empty())
				.toEntity(String.class)
				.block();

		assertNotNull(response);

		if (response != null) {
			BaseResponseResults results = BaseResponseResultsImpl.builder()
				.body(response.getBody())
				.headers(response.getHeaders())
				.status(BaseTestUtilities.toHttpStatus(response.getStatusCode()))
				.build();

			request.updateResults(results);
		}
	}

	/* ================= *
	 * Delete operations *
	 * ================= */

	protected void delete (BaseTestRequest request, String id) {
		ResponseEntity<String> response = webClient.delete()
			.uri(request.getPath() + "/" + id)
			.retrieve()
			.onStatus(HttpStatusCode::isError, e -> Mono.empty())
			.toEntity(String.class)
			.block();

		assertNotNull(response);

		if (response != null) {
			BaseResponseResults results = BaseResponseResultsImpl.builder()
				.body(response.getBody())
				.headers(response.getHeaders())
				.status(BaseTestUtilities.toHttpStatus(response.getStatusCode()))
				.build();

			request.deleteResults(results);
		}
	}

	protected void delete (BaseTestRequest request, String id, String partition) {
		ResponseEntity<String> response = webClient.delete()
			.uri(request.getPath() + "/" + id)
			.header(PARTITION_HEADER, partition)
			.retrieve()
			.onStatus(HttpStatusCode::isError, e -> Mono.empty())
			.toEntity(String.class)
			.block();

		assertNotNull(response);

		if (response != null) {
			BaseResponseResults results = BaseResponseResultsImpl.builder()
				.body(response.getBody())
				.headers(response.getHeaders())
				.status(BaseTestUtilities.toHttpStatus(response.getStatusCode()))
				.build();

			request.deleteResults(results);
		}
	}

	protected void deletePartition (BaseTestRequest request, String partition) {
		ResponseEntity<String> response = webClient.delete()
			.uri(request.getPath())
			.header(PARTITION_HEADER, partition)
			.retrieve()
			.onStatus(HttpStatusCode::isError, e -> Mono.empty())
			.toEntity(String.class)
			.block();

		assertNotNull(response);

		if (response != null) {
			BaseResponseResults results = BaseResponseResultsImpl.builder()
				.body(response.getBody())
				.headers(response.getHeaders())
				.status(BaseTestUtilities.toHttpStatus(response.getStatusCode()))
				.build();

			request.deleteResults(results);
		}
	}
}



