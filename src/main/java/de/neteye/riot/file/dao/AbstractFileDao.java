package de.neteye.riot.file.dao;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.riotfamily.core.dao.Hierarchy;
import org.riotfamily.core.dao.ListParams;
import org.riotfamily.core.dao.RiotDao;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.util.StringUtils;

import de.neteye.riot.file.filter.AndFileFilter;
import de.neteye.riot.file.filter.NameFileFilter;

public abstract class AbstractFileDao implements RiotDao, Hierarchy {

	private boolean countSize = true;

	private FileFilter fileFilter;	

	protected File rootFile;

	public void setRoot(Resource root) throws IOException {
		this.rootFile = root.getFile();
	}

	public void setRootFile(File rootFile) {
		this.rootFile = rootFile;
	}

	public void setFileFilter(FileFilter fileFilter) {
		this.fileFilter = fileFilter;
	}

	protected void setCountSize(boolean countSize) {
		this.countSize = countSize;
	}
	
	public FileFilter getFileFilter(ListParams params) {
		if (fileFilter == null) {
			fileFilter = new FileFilter() {
				@Override
				public boolean accept(File file) {
					return true;
				}
			};
		}
		FileFilter resultFilter = fileFilter;

		// Simple name search
    	if (params.getFilter() instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<String, ?> filterMap = (Map<String, ?>) params.getFilter();
			
			String filterValue = (String) filterMap.get("name");
			if (StringUtils.hasText(filterValue)) {
				resultFilter = new AndFileFilter(fileFilter,
						new NameFileFilter(".*" + filterValue + ".*"));
			}
		}
		
		return resultFilter;
	}
	
	@Override
	public Collection<?> list(Object parent, ListParams params) throws DataAccessException {
		File[] files = getFiles(parent, params);
		if (files != null) {
			files = Arrays.copyOfRange(files, params.getOffset(),
					Math.min(files.length, params.getOffset() + params.getPageSize()));

			return convertFiles(files);
		}
		return Collections.EMPTY_LIST;
	}
	
	protected abstract Collection<?> convertFiles(File[] files);

	protected abstract File convertModelToFile(Object entity);
	
	@Override
	public int getListSize(Object parent, ListParams params) throws DataAccessException {
		if (this.countSize) {
			File[] files = getFiles(parent, params);
			if (files != null) {
				return files.length;
			}
			return 0;
		}
		return -1;
	}

	protected File[] getFiles(Object parent, ListParams params) {
		File parentFile = convertModelToFile(parent);
		File[] files = new File[] {};

		File file = (File) parentFile;
		if (file == null) {
			if (rootFile != null) {
				file = rootFile;
			}
		}

		if (file != null) {
			files = file.listFiles(getFileFilter(params));
		}
		
		return files;
	}
	
}
