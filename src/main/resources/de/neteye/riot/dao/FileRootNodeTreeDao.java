package de.neteye.riot.dao;

import org.riotfamily.core.dao.SingleRoot;

public class FileRootNodeTreeDao extends FileTreeDao
		implements SingleRoot /* CutAndPaste */ {

	//private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public Object getRootNode(Object parent) {
		return rootFile;
	}

}
