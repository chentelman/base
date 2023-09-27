package org.chentelman.base.plugins.validation.source;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class LineValidator implements Consumer<String> {
	private int lineNo;
	private int consecutiveEmptyLines;

	private boolean checkTrailing;
	private boolean checkLeading;
	private boolean ignoreJavaDoc;

	private List<String> errors;

	public LineValidator (boolean checkTrailing, boolean checkLeading, boolean ignoreJavaDoc) {
		this.checkTrailing = checkTrailing;
		this.checkLeading  = checkLeading;
		this.ignoreJavaDoc = ignoreJavaDoc;
	}

	public void init () {
		lineNo = 0;
		consecutiveEmptyLines = 0;
		errors = new LinkedList<>();
	}

	@Override
	public void accept(String line) {
		// increase line size
		lineNo += 1;

		// process line as bytes
		byte[] bytes = line.getBytes();

		// calculate consecutive empty lines
		if (bytes.length == 0) {
			consecutiveEmptyLines += 1;
		} else {
			int i;
			int j;

			// skip leading tab characters and trailing whitespace
			for (i = 0; i < bytes.length && bytes[i] == '\t'; i += 1);
			for (j = bytes.length - 1; j >= 0 && Character.isWhitespace(bytes[j]); j -= 1);

			// line must not have trailing whitespace
			checkTrailingWhitespace (bytes);

			// line must not have leading whitespace other than tabs
			checkLeadingWhitespace (bytes, i);

			// check that tabs are not used after the initial tab sequence
			checkTabs (bytes, i, j);

			consecutiveEmptyLines = 0;
		}
	}

	private void checkTrailingWhitespace (byte[] bytes) {
		if (checkTrailing && Character.isWhitespace(bytes[bytes.length-1])) {
			errors.add(generateErrorMessage("ends with whitespace"));
		}
	}

	private void checkLeadingWhitespace (byte[] bytes, int i) {
		if (checkLeading && i < bytes.length && Character.isWhitespace(bytes[i])) {
			// check for javadocs
			if (ignoreJavaDoc && i+1 < bytes.length && bytes[i] == ' ' && bytes[i+1] == '*') {
				return;
			}
			errors.add(generateErrorMessage("starts with non tab whitespace"));
		}
	}

	private void checkTabs (byte[] bytes, int i, int j) {
		for (; i <= j; i += 1) {
			if (bytes[i] == '\t') {
				errors.add(generateErrorMessage("contains tab"));
			}
		}
	}

	private String generateErrorMessage (String message) {
		return String.format("line %d %s", lineNo, message);
	}

	public boolean hasErrors () {
		return !errors.isEmpty();
	}

	public List<String> getErrors () {
		return errors;
	}

	public int getConsecutiveEmptyLines () {
		return consecutiveEmptyLines;
	}

}



