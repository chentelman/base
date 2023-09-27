package org.chentelman.base.plugins.validation.source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo (name = "validate", defaultPhase = LifecyclePhase.VALIDATE)
public class SourceValidationMojo extends AbstractMojo {
	@Parameter(defaultValue = "true", required = true)
	private Boolean includePom;

	@Parameter(defaultValue = "true", required = true)
	private Boolean errorOnUnreadableFiles;

	@Parameter(defaultValue = "src", required = true)
	private String[] sourceFolders;

	@Parameter(defaultValue = "txt,ico", required = true)
	private String[] ignoreFileExtensions;

	@Parameter(defaultValue = "java,ts", required = false)
	private String[] ignoreJavaDocWhiteSpaceFileExtensions;

	@Parameter(defaultValue = "txt", required = false)
	private String[] ignoreTrailingWhiteSpaceFileExtensions;

	@Parameter(defaultValue = "txt,yml,yaml", required = false)
	private String[] ignoreLeadingWhiteSpaceFileExtensions;

	@Parameter(defaultValue = "", required = false)
	private String[] excludePaths = {};

	@Parameter(defaultValue = "java", required = false)
	private String[] checkFileEndingFileExtensions;

	@Parameter(defaultValue = "${project}", required = true)
	private MavenProject project;

	private Set<String> ignoreTrailingSet;
	private Set<String> ignoreLeadingSet;
	private Set<String> ignoreJavaDocSet;

	private List<String> failures = new LinkedList<>();

	private Set<String> getIgnoreTrailingSet () {
		if (Objects.isNull(ignoreTrailingSet)) {
			ignoreTrailingSet = new HashSet<>(Arrays.asList(ignoreTrailingWhiteSpaceFileExtensions));
		}
		return ignoreTrailingSet;
	}

	private Set<String> getIgnoreLeadingSet () {
		if (Objects.isNull(ignoreLeadingSet)) {
			ignoreLeadingSet = new HashSet<>(Arrays.asList(ignoreLeadingWhiteSpaceFileExtensions));
		}
		return ignoreLeadingSet;
	}

	private Set<String> getIgnoreJavaDocSet () {
		if (Objects.isNull(ignoreJavaDocSet)) {
			ignoreJavaDocSet = new HashSet<>(Arrays.asList(ignoreJavaDocWhiteSpaceFileExtensions));
		}
		return ignoreJavaDocSet;
	}

	@Override
	public void execute() throws MojoExecutionException {
		// check files on all source folders
		for(String folder : sourceFolders) {
			File file = new File(project.getBasedir().getAbsolutePath(), folder);
			if (!file.exists()) {
				getLog().debug(folder + " does not exist, skipping");
				continue;
			}

			getLog().info("validating : " + file.getName());
			checkFile (file, true);
		}

		// also check pom.xml
		if (Boolean.TRUE.equals(includePom)) {
			File pom = project.getFile();
			getLog().info("validating : " + pom.getName());
			checkFile (pom, true);
		}
	}

	// recursively look into all files under this file to check each file individually
	private void checkFile(File file, boolean report) throws MojoExecutionException {
		// check if file is in the exclude path
		if (excludePaths != null) {
			for (String excludePath : excludePaths) {
				if (file.getAbsolutePath().endsWith(excludePath)) {
					return;
				}
			}
		}

		// in case this is a file proceed with validations
		if (file.isFile() && !isValid(file)) {
			failures.add(file.getAbsolutePath());
		}

		// if that is a directory recursively process all files in the directory
		if (file.isDirectory()) {
			for (File cfile : file.listFiles()) {
				checkFile (cfile, false);
			}
		}

		// in case any failures are found log the file list and throw an error
		if (report && !failures.isEmpty()) {
			getLog().error("validation failed on:");

			for (String name : failures) {
				getLog().error(" - " + name);
			}

			throw new MojoExecutionException("Source validation failed for " + failures.size() + " file(s)");
		}
	}

	private boolean isValid (File file) {
		// handle unreadable files
		if (!file.canRead()) {
			return !errorOnUnreadableFiles;
		}
		String fileName = file.getName();
		if (!fileName.contains(".")) {
			getLog().debug("skipping file without extension: " + fileName);
			return true;
		}

		// get the file extension
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);

		// if file extension is to be ignored do not read file at all
		if (ignoreFileExtensions != null) {
			for (String extension : ignoreFileExtensions) {
				if (extension.equals(ext)) {
					getLog().debug("skipping file with extension " + ext + ": " + fileName);
					return true;
				}
			}
		}

		// read file and validate lines
		BufferedReader reader;
		LineValidator validator = new LineValidator(
			!getIgnoreTrailingSet().contains(ext),
			!getIgnoreLeadingSet().contains(ext),
			getIgnoreJavaDocSet().contains(ext)
		);
		validator.init();

		try {
			reader = new BufferedReader(new FileReader(file));
			reader.lines().forEach(validator);
			reader.close();
		} catch (IOException e) {
			getLog().debug(e.getMessage());
			return false;
		}

		if (checkFileEndingFileExtensions != null) {
			for (String extension : checkFileEndingFileExtensions) {
				if (extension.equals(ext) && validator.getConsecutiveEmptyLines() != 3) {
					getLog().debug("file " + fileName + " must end with three empty lines");
					return false;
				}
			}
		}

		// TODO
		for (String error : validator.getErrors()) {
			getLog().error(file.getAbsolutePath() + ": " + error);
		}
		return !validator.hasErrors();
	}

}



