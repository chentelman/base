package org.chentelman.base.module.client.api;

import java.util.List;

import org.chentelman.base.module.core.controller.BasePartitionedAPIDefinition;
import org.chentelman.base.module.core.domain.BasePartitionedEntity;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Web client implementing the partitioned api definition
 *
 * @param <K> the type of the key of the remote entity
 * @param <P> the type of the partition of the remote entity
 * @param <C> the object required to create a remote entity
 * @param <R> the object representing the server response for an entity
 * @param <S> the object representing the server response for an entity summary
 * @param <U> the object required to update a remote entity
 */
public class BasePartitionedWebClient<K, P, C, R extends S, S extends BasePartitionedEntity<K, P>, U> extends BaseAbstractWebClient implements BasePartitionedAPIDefinition<K, P, C, R, S, U> {

	private final Class<R> details;
	private final Class<S> summary;
	private final String baseUri;

	/**
	 * Initialization of the base partitioned web client
	 *
	 * @param mapper to be passed to super class
	 * @param client to be passed to super class
	 * @param uri the base path of the request.
	 * @param details the type of the response expected for list responses
	 * @param summary the type of the response excpected for direct responses
	 */
	public BasePartitionedWebClient (ObjectMapper mapper, WebClient client, String uri, Class<R> details, Class<S> summary) {
		super (mapper, client);

		this.details = details;
		this.summary = summary;
		this.baseUri = uri;
	}

	/**
	 * Request remote server for all items
	 */
	@Override
	public List<S> findAll () {
		return get(baseUri)
			.bodyToFlux(summary)
			.collectList()
			.block();
	}

	/**
	 * Request remote server for a page of items
	 *
	 * @param page the page number to get, 0 being the first page
	 * @param size the size of the page to get
	 */
	@Override
	public List<S> findAll (int page, int size) {
		return get(baseUri, headers(page, size))
			.bodyToFlux(summary)
			.collectList()
			.block();
	}

	/**
	 * Request remote server for all items with a specific id
	 *
	 * @param id the id of the items to retrieve
	 */
	@Override
	public List<S> get (K id) {
		return get(baseUri + "/" + id)
			.bodyToFlux(summary)
			.collectList()
			.block();
	}

	/**
	 * Request remote server for a page of items with a specific id
	 *
	 * @param id the id of the items to retrieve
	 * @param page the page number to get, 0 being the first page
	 * @param size the size of the page to get
	 */
	@Override
	public List<S> get (K id, int page, int size) {
		return get(baseUri + "/" + id, headers(page, size))
			.bodyToFlux(summary)
			.collectList()
			.block();
	}

	/**
	 * Request remote server to create a new item
	 *
	 * @param data the object representing the item to create
	 */
	@Override
	public R create (C data) {
		return post(baseUri, data)
			.bodyToMono(details)
			.block();
	}

	/**
	 * Request remote server to update an item
	 *
	 * @param id the id of the item to update
	 * @param data the object representing the item to update
	 */
	@Override
	public void update (K id, U data) {
		put(baseUri + "/" + id, data)
			.bodyToMono(Void.class)
			.block();
	}

	/**
	 * Request remote server to delete all items with a specific id
	 *
	 * @param id the id of the items to delete
	 */
	@Override
	public void delete (K id) {
		delete(baseUri + "/" + id)
			.bodyToMono(Void.class)
			.block();
	}

	/**
	 * Request remote server for all items in a specific partition
	 *
	 * @param partition the partition to get the items from
	 */
	@Override
	public List<S> findAll (P partition) {
		return get(baseUri, headers(partition.toString()))
			.bodyToFlux(summary)
			.collectList()
			.block();
	}

	/**
	 * Request remote server for a page of items in a partition
	 *
	 * @param partition the partition to get the items from
	 * @param page the page number to get, 0 being the first page
	 * @param size the size of the page to get
	 */
	@Override
	public List<S> findAll (P partition, int page, int size) {
		return get(baseUri, headers(page, size, partition.toString()))
			.bodyToFlux(summary)
			.collectList()
			.block();
	}

	/**
	 * Request remote server for a specific item
	 *
	 * @param partition the partition to get the item from
	 * @param id the id of the item to retrieve
	 */
	@Override
	public R get (P partition, K id) {
		return get(baseUri + "/" + id, headers(partition.toString()))
			.bodyToMono(details)
			.block();
	}

	/**
	 * Request remote server to update a specific item
	 *
	 * @param partition the partition to get the item from
	 * @param id the id of the item to retrieve
	 * @param data the object representing the item to update
	 */
	@Override
	public void update (P partition, K id, U data) {
		put(baseUri + "/" + id, headers(partition.toString()), data)
			.bodyToMono(Void.class)
			.block();
	}

	/**
	 * Request remote server to delete an item
	 *
	 * @param partition the partition to delete the item from
	 * @param id the id of the item to delete
	 */
	@Override
	public void delete (P partition, K id) {
		delete(baseUri + "/" + id, headers(partition.toString()))
			.bodyToMono(Void.class)
			.block();
	}

	/**
	 * Request remote server to delete all items in a partition
	 *
	 * @param partition the partition to delete the item from
	 */
	@Override
	public void deletePartition (P partition) {
		delete(baseUri, headers(partition.toString()))
			.bodyToMono(Void.class)
			.block();
	}
}



