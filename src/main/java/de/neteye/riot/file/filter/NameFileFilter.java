package de.neteye.riot.file.filter;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameFileFilter implements FileFilter {
	
	private Pattern pattern;

	public NameFileFilter(String pattern) {
		this(pattern, false);
	}
	
	public NameFileFilter(String pattern, boolean caseSensitive) {
		this.pattern = Pattern.compile(pattern, caseSensitive ? 0 : Pattern.CASE_INSENSITIVE + Pattern.UNICODE_CASE);
	}
	
	@Override
	public boolean accept(File file) {
		Matcher matcher = pattern.matcher(file.getName());
		return matcher.matches();
	}

}
