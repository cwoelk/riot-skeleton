package de.neteye.riot.file.dao;

import java.io.File;
import java.io.IOException;

import org.riotfamily.core.dao.Hierarchy;
import org.riotfamily.core.dao.RiotDao;
import org.riotfamily.core.dao.TreeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

public class FileTreeDao extends TreeDao
		implements InitializingBean /* CutAndPaste */ {

	private Logger log = LoggerFactory.getLogger(getClass());

	private Resource root;

	private AbstractFileDao rootDao;

	private AbstractFileDao nodeDao;

	protected File rootFile;
	
	public void setRoot(Resource root) {
		this.root = root;
	}
	
	@Override
	public void setRootDao(RiotDao rootDao) {
		super.setRootDao(rootDao);
		this.rootDao = (AbstractFileDao) rootDao;
	}
	
	@Override
	public void setNodeDao(Hierarchy nodeDao) {
		super.setNodeDao(nodeDao);
		this.nodeDao = (AbstractFileDao) nodeDao;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (root != null) {			
			try {
				rootFile = root.getFile();
			}
			catch (IOException e) {
				log.error("An exception occured:", e);
			}

			if (rootDao != null) {
				rootDao.setRootFile(rootFile);
			}
			if (nodeDao != null && root != null) {
				nodeDao.setRootFile(rootFile);
			}
		}
	}

}
