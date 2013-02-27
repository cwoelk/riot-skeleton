package de.neteye.riot.dao;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.riotfamily.core.dao.ListParams;
import org.riotfamily.core.dao.RiotDao;
import org.riotfamily.core.dao.RiotDaoException;
import org.springframework.dao.DataAccessException;

public class FileDao implements RiotDao {

	//private Logger log = LoggerFactory.getLogger(getClass());
	
	private FileFilter fileFilter;
		
	protected File rootFile;

	public void setRootFile(File rootFile) {
		this.rootFile = rootFile;
	}

	public void setFileFilter(FileFilter fileFilter) {
		this.fileFilter = fileFilter;
	}
	
	public FileFilter getFileFilter() {
		if (fileFilter == null) {
			fileFilter = new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return true;
				}
			};
		}
		return fileFilter;
	}
	
	@Override
	public Class<?> getEntityClass() {
		return File.class;
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
		File[] files = new File[] {};

		File file = (File) parent;
		if (file == null) {
			if (rootFile != null) {
				file = rootFile;
			}
		}

		if (file != null) {
			files = file.listFiles(getFileFilter());
		}
		
		return files;
	}
	
}
