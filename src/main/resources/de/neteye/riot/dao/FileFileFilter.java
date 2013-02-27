package de.neteye.riot.dao;

import java.io.File;
import java.io.FileFilter;

public class FileFileFilter implements FileFilter {

	@Override
	public boolean accept(File file) {
		return file.isFile();
	}

}
