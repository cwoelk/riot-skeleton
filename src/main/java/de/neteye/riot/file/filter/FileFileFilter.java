package de.neteye.riot.file.filter;

import java.io.File;
import java.io.FileFilter;

public class FileFileFilter implements FileFilter {

	@Override
	public boolean accept(File file) {
		return file.isFile();
	}

}
