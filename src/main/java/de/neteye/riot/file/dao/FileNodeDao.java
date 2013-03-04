package de.neteye.riot.file.dao;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

import org.riotfamily.core.dao.Hierarchy;
import org.riotfamily.core.dao.ListParams;
import org.springframework.dao.DataAccessException;

public class FileNodeDao extends FileDao implements Hierarchy {

	@Override
	public Object getParent(Object entity) {
		File file = (File) entity;
		if (file != null && rootFile != null && !rootFile.equals(file)) {
			return file.getParentFile();
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
