package de.neteye.riot.dao;

import java.io.File;
import java.io.FileFilter;

public class DirectoryFileFilter implements FileFilter {

	@Override
	public boolean accept(File file) {
		return file.isDirectory();
	}

}
