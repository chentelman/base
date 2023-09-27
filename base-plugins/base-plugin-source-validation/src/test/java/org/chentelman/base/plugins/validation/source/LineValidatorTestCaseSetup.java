package org.chentelman.base.plugins.validation.source;

import java.util.List;

public class LineValidatorTestCaseSetup {

	public static final List<String> ignoreTrailingList = List.of("", "txt");
	public static final List<String> ignoreLeadingList = List.of("", "txt", "yml", "yaml");
	public static final List<String> ignoreJavaDocList = List.of("java", "ts");

	protected LineValidator uut (String ext) {
		return new LineValidator(
			!ignoreTrailingList.contains(ext),
			!ignoreLeadingList.contains(ext),
			ignoreJavaDocList.contains(ext)
		);

	}
}



