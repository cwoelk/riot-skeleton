package de.neteye.riot.file.dao;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import org.riotfamily.core.dao.RiotDaoException;
import org.springframework.dao.DataAccessException;

public class FileDao extends AbstractFileDao {

	@Override
	public Class<?> getEntityClass() {
		return File.class;
	}

	@Override
	public Object getParent(Object entity) {
		File file = (File) entity;
		return file.getParentFile();
	}

	@Override
	public String getObjectId(Object entity) {
		File file = (File) entity;
		return file.getAbsolutePath().replaceAll("/", ":");
	}

	@Override
	public Object load(String id) throws DataAccessException {
		return new File(id.replaceAll(":", "/"));
	}

	@Override
	public Object update(Object entity) throws DataAccessException {
		throw new RiotDaoException("readonly", "Read-only");
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
		return Arrays.asList(files);
	}

	@Override
	protected File convertModelToFile(Object entity) {
		return (File) entity;
	}

}
