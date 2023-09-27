package org.chentelman.base.plugins.validation.source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

public class FileValidator implements Supplier<FileValidation> {

	private final File file;
	private final LineValidator lineValidator;
	private final boolean checkFileEnding;

	public FileValidator (File file, boolean checkTrailing, boolean checkLeading, boolean ignoreJavaDoc, boolean checkFileEnding) {
		this.lineValidator = new LineValidator (checkTrailing, checkLeading, ignoreJavaDoc);
		this.file = file;
		this.checkFileEnding = checkFileEnding;
	}

	@Override
	public FileValidation get() {
		lineValidator.init();
		List<String> errors = lineValidator.getErrors();

		// read file and validate lines
		try {
			BufferedReader reader;
			reader = new BufferedReader (new FileReader(file));
			reader.lines().forEach (lineValidator);
			reader.close();
		} catch (IOException e) {
			errors.add ("Cannot process file");
		}

		if (checkFileEnding && lineValidator.getConsecutiveEmptyLines() != 3) {
			errors.add ("file must end with three empty lines");
		}

		return FileValidation.of (file, errors);
	}

}



