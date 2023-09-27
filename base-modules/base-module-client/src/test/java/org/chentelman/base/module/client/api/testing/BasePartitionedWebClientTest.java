package org.chentelman.base.module.client.api.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.chentelman.base.module.client.api.BasePartitionedWebClient;
import org.chentelman.base.module.client.api.entity.TestEntity;
import org.chentelman.base.module.client.api.entity.TestPartitionedEntity;
import org.chentelman.base.module.core.controller.BaseAPIDefinition;
import org.chentelman.base.module.core.controller.BasePartitionedAPIDefinition;
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
class BasePartitionedWebClientTest {
	private MockWebServer mockBackEnd;
	private ObjectMapper mapper;
	private BasePartitionedWebClient<Long, String, TestPartitionedEntity, TestPartitionedEntity, TestPartitionedEntity, TestPartitionedEntity> webClient;

	@BeforeAll
	void setUp() throws IOException {
		mockBackEnd = new MockWebServer();
		mockBackEnd.start();

		mapper = new ObjectMapper();

		WebClient client = WebClient.create("http://" + mockBackEnd.getHostName() + ":" + mockBackEnd.getPort());
		webClient = new BasePartitionedWebClient<> (mapper, client, "/partitioned", TestPartitionedEntity.class, TestPartitionedEntity.class);
	}

	@AfterAll
	void tearDown() throws IOException {
		mockBackEnd.shutdown();
	}

	@Test
	void findAll () throws InterruptedException {
		// mock request
		mockBackEnd.enqueue(new MockResponse()
			.setBody("["
					+ "{\"id\":1,\"data\":\"one\",\"partition\":\"en\"},"
					+ "{\"id\":2,\"data\":\"two\",\"partition\":\"en\"},"
					+ "{\"id\":3,\"data\":\"tre\",\"partition\":\"en\"}]")
			.addHeader("Content-Type", "application/json"));

		List<TestPartitionedEntity> entity = webClient.findAll();

		assertNotNull (entity);
		assertEquals (3, entity.size());

		RecordedRequest recordedRequest = mockBackEnd.takeRequest();

		assertEquals("GET", recordedRequest.getMethod());
		assertEquals("/partitioned", recordedRequest.getPath());
		assertEquals("application/json", recordedRequest.getHeader("Accept"));
		assertEquals(4, recordedRequest.getHeaders().size());
	}

	@Test
	void findAll_page () throws InterruptedException {
		// mock request
		mockBackEnd.enqueue(new MockResponse()
			.setBody("["
				+ "{\"id\":1,\"data\":\"one\",\"partition\":\"en\"},"
				+ "{\"id\":2,\"data\":\"two\",\"partition\":\"en\"}]")
			.addHeader("Content-Type", "application/json"));

		List<TestPartitionedEntity> entity = webClient.findAll(0, 2);

		assertNotNull (entity);
		assertEquals (2, entity.size());

		RecordedRequest recordedRequest = mockBackEnd.takeRequest();

		assertEquals("GET", recordedRequest.getMethod());
		assertEquals("/partitioned", recordedRequest.getPath());
		assertEquals("0", recordedRequest.getHeader(BaseAPIDefinition.PAGE_HEADER));
		assertEquals("2", recordedRequest.getHeader(BaseAPIDefinition.SIZE_HEADER));
		assertEquals("application/json", recordedRequest.getHeader("Accept"));
		assertEquals(6, recordedRequest.getHeaders().size());
	}

	@Test
	void get () throws InterruptedException {
		// mock request
		mockBackEnd.enqueue(new MockResponse()
			.setBody("[{\"id\":1,\"data\":\"one\",\"partition\":\"en\"}]")
			.addHeader("Content-Type", "application/json"));

		List<TestPartitionedEntity> entity = webClient.get(1L);

		assertNotNull (entity);
		assertEquals (1, entity.size());
		assertEquals (1, entity.get(0).getId());
		assertEquals ("one", entity.get(0).getData());

		RecordedRequest recordedRequest = mockBackEnd.takeRequest();

		assertEquals("GET", recordedRequest.getMethod());
		assertEquals("/partitioned/1", recordedRequest.getPath());
		assertEquals("application/json", recordedRequest.getHeader("Accept"));
		assertEquals(4, recordedRequest.getHeaders().size());
	}

	@Test
	void create () throws InterruptedException {
		TestPartitionedEntity create = new TestPartitionedEntity();

		create.setId(0L);
		create.setData("zero");

		// mock request
		mockBackEnd.enqueue(new MockResponse()
			.setBody("{\"id\":0,\"data\":\"zero\",\"partition\":\"en\"}")
			.addHeader("Content-Type", "application/json"));

		TestPartitionedEntity entity = webClient.create(create);

		assertNotNull (entity);
		assertEquals (0, entity.getId());
		assertEquals ("zero", entity.getData());

		RecordedRequest recordedRequest = mockBackEnd.takeRequest();

		assertEquals("POST", recordedRequest.getMethod());
		assertEquals("/partitioned", recordedRequest.getPath());
		assertEquals("application/json", recordedRequest.getHeader("Accept"));
		assertEquals("application/json", recordedRequest.getHeader("Content-Type"));
		assertEquals(6, recordedRequest.getHeaders().size());
	}

	@Test
	void update () throws InterruptedException {
		TestPartitionedEntity update = new TestPartitionedEntity();

		update.setId(0L);
		update.setData("zero");
		update.setPartition("en");

		// mock request
		mockBackEnd.enqueue(new MockResponse());

		webClient.update(1L, update);

		RecordedRequest recordedRequest = mockBackEnd.takeRequest();

		assertEquals("PUT", recordedRequest.getMethod());
		assertEquals("/partitioned/1", recordedRequest.getPath());
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
		assertEquals("/partitioned/1", recordedRequest.getPath());
		assertEquals("application/json", recordedRequest.getHeader("Accept"));
		assertEquals(4, recordedRequest.getHeaders().size());
	}

	@Test
	void findAll_partition () throws InterruptedException {
		// mock request
		mockBackEnd.enqueue(new MockResponse()
			.setBody("["
					+ "{\"id\":1,\"data\":\"one\",\"partition\":\"en\"},"
					+ "{\"id\":2,\"data\":\"two\",\"partition\":\"en\"},"
					+ "{\"id\":3,\"data\":\"tre\",\"partition\":\"en\"}]")
			.addHeader("Content-Type", "application/json"));

		List<TestPartitionedEntity> entity = webClient.findAll("en");

		assertNotNull (entity);
		assertEquals (3, entity.size());

		RecordedRequest recordedRequest = mockBackEnd.takeRequest();

		assertEquals("GET", recordedRequest.getMethod());
		assertEquals("/partitioned", recordedRequest.getPath());
		assertEquals("en", recordedRequest.getHeader(BasePartitionedAPIDefinition.PARTITION_HEADER));
		assertEquals("application/json", recordedRequest.getHeader("Accept"));
		assertEquals(5, recordedRequest.getHeaders().size());
	}

	@Test
	void findAll_partition_page () throws InterruptedException {
		// mock request
		mockBackEnd.enqueue(new MockResponse()
			.setBody("["
				+ "{\"id\":1,\"data\":\"one\",\"partition\":\"en\"},"
				+ "{\"id\":2,\"data\":\"two\",\"partition\":\"en\"}]")
			.addHeader("Content-Type", "application/json"));

		List<TestPartitionedEntity> entity = webClient.findAll("en", 0, 2);

		assertNotNull (entity);
		assertEquals (2, entity.size());

		RecordedRequest recordedRequest = mockBackEnd.takeRequest();

		assertEquals("GET", recordedRequest.getMethod());
		assertEquals("/partitioned", recordedRequest.getPath());
		assertEquals("0", recordedRequest.getHeader(BaseAPIDefinition.PAGE_HEADER));
		assertEquals("2", recordedRequest.getHeader(BaseAPIDefinition.SIZE_HEADER));
		assertEquals("en", recordedRequest.getHeader(BasePartitionedAPIDefinition.PARTITION_HEADER));
		assertEquals("application/json", recordedRequest.getHeader("Accept"));
		assertEquals(7, recordedRequest.getHeaders().size());
	}

	@Test
	void get_partition () throws InterruptedException {
		// mock request
		mockBackEnd.enqueue(new MockResponse()
			.setBody("{\"id\":1,\"data\":\"one\"}")
			.addHeader("Content-Type", "application/json"));

		TestEntity entity = webClient.get("en", 1L);

		assertNotNull (entity);
		assertEquals (1, entity.getId());
		assertEquals ("one", entity.getData());

		RecordedRequest recordedRequest = mockBackEnd.takeRequest();

		assertEquals("GET", recordedRequest.getMethod());
		assertEquals("/partitioned/1", recordedRequest.getPath());
		assertEquals("en", recordedRequest.getHeader(BasePartitionedAPIDefinition.PARTITION_HEADER));
		assertEquals("application/json", recordedRequest.getHeader("Accept"));
		assertEquals(5, recordedRequest.getHeaders().size());
	}

	@Test
	void update_partition () throws InterruptedException {
		TestPartitionedEntity update = new TestPartitionedEntity();

		update.setId(0L);
		update.setData("zero");
		update.setPartition("en");

		// mock request
		mockBackEnd.enqueue(new MockResponse());

		webClient.update("en", 1L, update);

		RecordedRequest recordedRequest = mockBackEnd.takeRequest();

		assertEquals("PUT", recordedRequest.getMethod());
		assertEquals("/partitioned/1", recordedRequest.getPath());
		System.out.println(recordedRequest.getHeaders());
		assertEquals("en", recordedRequest.getHeader(BasePartitionedAPIDefinition.PARTITION_HEADER));
		assertEquals("application/json", recordedRequest.getHeader("Accept"));
		assertEquals("application/json", recordedRequest.getHeader("Content-Type"));
		assertEquals(7, recordedRequest.getHeaders().size());
	}

	@Test
	void delete_partition () throws InterruptedException {
		// mock request
		mockBackEnd.enqueue(new MockResponse());

		webClient.delete("en", 1L);

		RecordedRequest recordedRequest = mockBackEnd.takeRequest();

		assertEquals("DELETE", recordedRequest.getMethod());
		assertEquals("/partitioned/1", recordedRequest.getPath());
		assertEquals("en", recordedRequest.getHeader(BasePartitionedAPIDefinition.PARTITION_HEADER));
		assertEquals("application/json", recordedRequest.getHeader("Accept"));
		assertEquals(5, recordedRequest.getHeaders().size());
	}

	@Test
	void delete_by_partition () throws InterruptedException {
		// mock request
		mockBackEnd.enqueue(new MockResponse());

		webClient.deletePartition("en");

		RecordedRequest recordedRequest = mockBackEnd.takeRequest();

		assertEquals("DELETE", recordedRequest.getMethod());
		assertEquals("/partitioned", recordedRequest.getPath());
		assertEquals("en", recordedRequest.getHeader(BasePartitionedAPIDefinition.PARTITION_HEADER));
		assertEquals("application/json", recordedRequest.getHeader("Accept"));
		assertEquals(5, recordedRequest.getHeaders().size());
	}
}



