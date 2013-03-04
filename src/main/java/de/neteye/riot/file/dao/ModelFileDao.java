package de.neteye.riot.file.dao;

import java.io.File;
import java.util.Collection;

import org.riotfamily.common.util.Generics;
import org.riotfamily.core.dao.RiotDaoException;
import org.springframework.dao.DataAccessException;

import de.neteye.riot.file.model.FileModel;

public class ModelFileDao extends AbstractFileDao {

	@Override
	public Class<?> getEntityClass() {
		return FileModel.class;
	}

	@Override
	public Object getParent(Object entity) {
		FileModel fileModel = (FileModel) entity;
		return fileModel.getParent();
	}
	
	@Override
	public String getObjectId(Object entity) {
		FileModel fileModel = (FileModel) entity;
		return fileModel.getId();
	}

	@Override
	public Object load(String id) throws DataAccessException {
		return new FileModel(id);
	}

	@Override
	public Object update(Object entity) throws DataAccessException {
		//throw new RiotDaoException("readonly", "Read-only");
		return entity;
	}

	@Override
	public void save(Object entity, Object parent) throws DataAccessException {
		throw new RiotDaoException("readonly", "Read-only");
	}

	@Override
	public void delete(Object entity, Object parent) throws DataAccessException {
		throw new RiotDaoException("readonly", "Read-only");
	}

	@Override
	protected Collection<?> convertFiles(File[] files) {
		Collection<FileModel> result = Generics.newArrayList(files.length);
		for (int i = 0; i < files.length; i++) {
			result.add(new FileModel(files[i]));
		}
		return result;
	}

	@Override
	protected File convertModelToFile(Object entity) {
		if (entity != null) {
			FileModel fileModel = (FileModel) entity;
			return fileModel.getFile();
		}
		return null;
	}

}
