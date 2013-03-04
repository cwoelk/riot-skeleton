package de.neteye.riot.file.dao;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

import org.riotfamily.core.dao.Hierarchy;
import org.riotfamily.core.dao.ListParams;
import org.springframework.dao.DataAccessException;

import de.neteye.riot.file.model.FileModel;

public class ModelFileNodeDao extends ModelFileDao implements Hierarchy {

	@Override
	public Object getParent(Object entity) {
		FileModel fileModel = (FileModel) entity;
		if (fileModel != null && rootFile != null && !rootFile.equals(fileModel.getFile())) {
			return fileModel.getParent();
		}
		return null;
	}

	@Override
	public Collection<?> list(Object parent, ListParams params)
			throws DataAccessException {
		
		File[] files = getFiles(parent, params);
		if (files != null) {
			return convertFiles(files);
		}
		return Collections.EMPTY_LIST;
	}
	
}
