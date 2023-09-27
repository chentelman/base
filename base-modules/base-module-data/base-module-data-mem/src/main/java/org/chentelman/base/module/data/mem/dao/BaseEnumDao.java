package org.chentelman.base.module.data.mem.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.chentelman.base.module.core.component.BaseComponent;
import org.chentelman.base.module.core.data.BaseAccessDao;
import org.chentelman.base.module.core.domain.BaseEnumType;
import org.chentelman.base.module.core.utilities.BaseUtilities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Base enum dao.
 *
 * This is a converter from an enumeration type to a BaseAccessDao.
 * Enumerations handled by this dao must extend the BaseEnumType.
 *
 * @param <E> the entity to store in the map
 */
public class BaseEnumDao<E extends BaseEnumType> extends BaseComponent implements BaseAccessDao<E, String> {

	private List<E> list;

	/**
	 * This will initialise the dao for the specific enumeration
	 *
	 * @param type the class type of the enumeration
	 * @throws ClassCastException in case the type is not an enumeration.
	 */
	public BaseEnumDao (Class<E> type) {
		if (!type.isEnum()) {
			throw new ClassCastException("Class is not an enum");
		}

		this.list = Collections.unmodifiableList (
			Arrays.asList (
				type.getEnumConstants()
			)
		);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<E> findAll () {
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<E> findAll (Pageable pageable) {
		return BaseUtilities.toPage(list, pageable);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() {
		return list.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<E> findById (String id) {
		return list.stream()
			.filter(v -> Objects.equals(v.getCode(), id))
			.findFirst();
	}
}



