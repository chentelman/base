package org.chentelman.base.plugins.validation.source;

import java.io.File;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FileValidation {

	private final boolean success;
	private final List<String> errors;
	private final String filename;

	public static FileValidation of (File file, List<String> errors) {
		return new FileValidation (errors.isEmpty(), errors, file.getAbsolutePath());
	}
}



