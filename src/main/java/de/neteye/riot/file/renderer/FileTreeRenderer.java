package de.neteye.riot.file.renderer;

import java.io.File;

public class FileTreeRenderer extends AbstractTreeRenderer {
	
	@Override
	protected boolean isFolder(Object object) {
		File file = (File) object;
		return file.isDirectory();
	}
	
	@Override
	protected String getTitle(Object object) {
		File file = (File) object;
		return file.getName();
	}

}
