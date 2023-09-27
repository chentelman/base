package org.chentelman.base.module.client.api.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.chentelman.base.module.client.api.BaseWebClient;
import org.chentelman.base.module.client.api.entity.TestEntity;
import org.chentelman.base.module.core.controller.BaseAPIDefinition;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

@TestInstance(Lifecycle.PER_CLASS)
class BaseWebClientTest {
	private MockWebServer mockBackEnd;
	private ObjectMapper mapper;
	private BaseWebClient<Long, TestEntity, TestEntity, TestEntity, TestEntity> webClient;

	@BeforeAll
	void setUp() throws IOException {
		mockBackEnd = new MockWebServer();
		mockBackEnd.start();

		mapper = new ObjectMapper();

		WebClient client = WebClient.create("http://" + mockBackEnd.getHostName() + ":" + mockBackEnd.getPort());
		webClient = new BaseWebClient<> (mapper, client, "/simple", TestEntity.class, TestEntity.class);
	}

	@AfterAll
	void tearDown() throws IOException {
		mockBackEnd.shutdown();
	}

	@Test
	void findAll () throws InterruptedException {
		// mock request
		mockBackEnd.enqueue(new MockResponse()
			.setBody("[{\"id\":1,\"data\":\"one\"},{\"id\":2,\"data\":\"two\"},{\"id\":3,\"data\":\"three\"}]")
			.addHeader("Content-Type", "application/json"));

		List<TestEntity> entity = webClient.findAll();

		assertNotNull (entity);
		assertEquals (3, entity.size());

		RecordedRequest recordedRequest = mockBackEnd.takeRequest();

		assertEquals("GET", recordedRequest.getMethod());
		assertEquals("/simple", recordedRequest.getPath());
		assertEquals("application/json", recordedRequest.getHeader("Accept"));
		assertEquals(4, recordedRequest.getHeaders().size());
	}

	@Test
	void findAll_page () throws InterruptedException {
		// mock request
		mockBackEnd.enqueue(new MockResponse()
			.setBody("[{\"id\":1,\"data\":\"one\"},{\"id\":2,\"data\":\"two\"}]")
			.addHeader("Content-Type", "application/json"));

		List<TestEntity> entity = webClient.findAll(0, 2);

		assertNotNull (entity);
		assertEquals (2, entity.size());

		RecordedRequest recordedRequest = mockBackEnd.takeRequest();

		assertEquals("GET", recordedRequest.getMethod());
		assertEquals("/simple", recordedRequest.getPath());
		assertEquals("0", recordedRequest.getHeader(BaseAPIDefinition.PAGE_HEADER));
		assertEquals("2", recordedRequest.getHeader(BaseAPIDefinition.SIZE_HEADER));
		assertEquals("application/json", recordedRequest.getHeader("Accept"));
		assertEquals(6, recordedRequest.getHeaders().size());
	}

	@Test
	void get () throws InterruptedException {
		// mock request
		mockBackEnd.enqueue(new MockResponse()
			.setBody("{\"id\":1,\"data\":\"one\"}")
			.addHeader("Content-Type", "application/json"));

		TestEntity entity = webClient.get(1L);

		assertNotNull (entity);
		assertEquals (1, entity.getId());
		assertEquals ("one", entity.getData());

		RecordedRequest recordedRequest = mockBackEnd.takeRequest();

		assertEquals("GET", recordedRequest.getMethod());
		assertEquals("/simple/1", recordedRequest.getPath());
		assertEquals("application/json", recordedRequest.getHeader("Accept"));
		assertEquals(4, recordedRequest.getHeaders().size());
	}

	@Test
	void create () throws InterruptedException {
		TestEntity create = new TestEntity();

		create.setId(0L);
		create.setData("zero");

		// mock request
		mockBackEnd.enqueue(new MockResponse()
			.setBody("{\"id\":0,\"data\":\"zero\"}")
			.addHeader("Content-Type", "application/json"));

		TestEntity entity = webClient.create(create);

		assertNotNull (entity);
		assertEquals (0, entity.getId());
		assertEquals ("zero", entity.getData());

		RecordedRequest recordedRequest = mockBackEnd.takeRequest();

		assertEquals("POST", recordedRequest.getMethod());
		assertEquals("/simple", recordedRequest.getPath());
		assertEquals("application/json", recordedRequest.getHeader("Accept"));
		assertEquals("application/json", recordedRequest.getHeader("Content-Type"));
		assertEquals(6, recordedRequest.getHeaders().size());
	}

	@Test
	void update () throws InterruptedException {
		TestEntity update = new TestEntity();

		update.setId(0L);
		update.setData("zero");

		// mock request
		mockBackEnd.enqueue(new MockResponse());

		webClient.update(1L, update);

		RecordedRequest recordedRequest = mockBackEnd.takeRequest();

		assertEquals("PUT", recordedRequest.getMethod());
		assertEquals("/simple/1", recordedRequest.getPath());
		assertEquals("application/json", recordedRequest.getHeader("Accept"));
		assertEquals("application/json", recordedRequest.getHeader("Content-Type"));
		assertEquals(6, recordedRequest.getHeaders().size());
	}

	@Test
	void delete () throws InterruptedException {
		// mock request
		mockBackEnd.enqueue(new MockResponse());

		webClient.delete(1L);

		RecordedRequest recordedRequest = mockBackEnd.takeRequest();

		assertEquals("DELETE", recordedRequest.getMethod());
		assertEquals("/simple/1", recordedRequest.getPath());
		assertEquals("application/json", recordedRequest.getHeader("Accept"));
		assertEquals(4, recordedRequest.getHeaders().size());
	}
}



