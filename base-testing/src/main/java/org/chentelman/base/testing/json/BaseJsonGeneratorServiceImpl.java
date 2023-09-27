package org.chentelman.base.testing.json;

import org.chentelman.base.testing.service.BaseListableServiceImpl;
import org.springframework.beans.factory.ListableBeanFactory;

/**
 * A base listable service implementation for {@link BaseJsonGenerator} objects
 */
public class BaseJsonGeneratorServiceImpl extends BaseListableServiceImpl<BaseJsonGenerator> implements BaseJsonGeneratorService {

	public BaseJsonGeneratorServiceImpl (ListableBeanFactory listableBeanFactory) {
		super(listableBeanFactory, BaseJsonGenerator.class);
	}

	/**
	 * Method to register the listable
	 *
	 * beans with a duplicate name are prohibited and will cause the application to fail.
	 */
	@Override
	protected void register (BaseJsonGenerator discoverable) {
		for (String alias : discoverable.getAliases()) {
			if (listables.containsKey(alias)) {
				logger.error("dublicate discoverable with name {} found", alias);
				logger.error(" new -> {}", discoverable.getClass().getName());
				logger.error(" old -> {}", listables.get(alias).getClass().getName());
				System.exit(1);
			}

			listables.put(alias, discoverable);
		}
	}
}



