package org.chentelman.base.module.data.mem.service;

import java.util.List;
import java.util.Optional;

import org.chentelman.base.module.core.component.BaseComponent;
import org.chentelman.base.module.core.domain.BaseEnumType;
import org.chentelman.base.module.core.exception.BaseNotFoundException;
import org.chentelman.base.module.core.service.BaseAccessService;
import org.chentelman.base.module.core.utilities.BaseUtilities;
import org.chentelman.base.module.data.mem.dao.BaseEnumDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BaseEnumServiceImpl<E extends BaseEnumType> extends BaseComponent implements BaseAccessService<E, String> {

	private final Class<E> servicedType;
	private final BaseEnumDao<E> inMemoryDao;

	public BaseEnumServiceImpl (
		Class<E> servicedType,
		BaseEnumDao<E> inMemoryDao
	) {
		this.servicedType = servicedType;
		this.inMemoryDao  = inMemoryDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<E> readAll() {
		return BaseUtilities.toList(inMemoryDao.findAll());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<E> readAll(Pageable pageable) {
		return inMemoryDao.findAll(pageable);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() {
		return inMemoryDao.count();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E read(String id) {
		Optional<E> item = inMemoryDao.findById(id);

		if (item.isEmpty()) {
			throw BaseNotFoundException.of(id, servicedType);
		}

		return item.get();
	}
}



