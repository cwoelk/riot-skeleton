package de.neteye.riot.file.filter;

import java.io.File;
import java.io.FileFilter;

public class AndFileFilter implements FileFilter {

	private FileFilter filterLeft;
	
	private FileFilter filterRight;
	
	public AndFileFilter(FileFilter filterLeft, FileFilter filterRight) {
		this.filterLeft = filterLeft;
		this.filterRight = filterRight;
	}
	
	@Override
	public boolean accept(File file) {
		return filterLeft.accept(file) && filterRight.accept(file);
	}

}
