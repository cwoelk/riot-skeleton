package de.neteye.riot.file.renderer;

import de.neteye.riot.file.model.FileModel;

public class ModelFileTreeRenderer extends AbstractTreeRenderer {
	
	@Override
	protected boolean isFolder(Object object) {
		FileModel fileModel = (FileModel) object;
		return fileModel.isDirectory();
	}
	
	@Override
	protected String getTitle(Object object) {
		FileModel fileModel = (FileModel) object;
		return fileModel.getName();
	}

}
