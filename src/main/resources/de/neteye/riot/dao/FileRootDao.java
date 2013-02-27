package de.neteye.riot.dao;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.riotfamily.core.dao.ListParams;
import org.riotfamily.core.dao.RiotDao;
import org.riotfamily.core.dao.RiotDaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;

public class FileRootDao implements RiotDao {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private Resource root;
	
	public void setRoot(Resource root) {
		this.root = root;
	}

	@Override
	public Class<?> getEntityClass() {
		return File.class;
	}

	@Override
	public String getObjectId(Object entity) {
		File file = (File) entity;
		return file.getAbsolutePath();
	}

	@Override
	public Object load(String id) throws DataAccessException {
		return new File(id);
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
	public Collection<?> list(Object parent, ListParams params) throws DataAccessException {
		File[] files = getFiles(parent);
		if (files != null) {
			files = Arrays.copyOfRange(files, params.getOffset(),
					Math.min(files.length, params.getOffset() + params.getPageSize()));

			return Arrays.asList(files);
		}
		return Collections.EMPTY_LIST;
	}

	@Override
	public int getListSize(Object parent, ListParams params) throws DataAccessException {
		File[] files = getFiles(parent);
		if (files != null) {
			return files.length;
		}
		return 0;
	}

	protected File[] getFiles(Object parent) {
		File file = (File) parent;
		if (file == null) {
			try {
				file = root.getFile();
			}
			catch (IOException e) {
				log.error("An exception occured:", e);
			}
		}
		
		File[] files = file.listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.isDirectory(); // && !file.isHidden();
			}
		});

		return files;
	}
	
}
