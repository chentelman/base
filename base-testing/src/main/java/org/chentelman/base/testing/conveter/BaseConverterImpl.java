package org.chentelman.base.testing.conveter;

import java.util.List;

import org.chentelman.base.module.core.component.BaseComponent;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The default implementation of the base converter
 *
 * Provides the type and alias list.
 */
@Getter
@RequiredArgsConstructor
public class BaseConverterImpl extends BaseComponent implements BaseConverter {

	private final Class<?> type;
	private final List<String> aliases;
}



