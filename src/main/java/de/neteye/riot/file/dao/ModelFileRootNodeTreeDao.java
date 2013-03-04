package de.neteye.riot.file.dao;

import org.riotfamily.core.dao.RiotDao;
import org.riotfamily.core.dao.SingleRoot;

import de.neteye.riot.file.model.FileModel;

public class ModelFileRootNodeTreeDao extends FileTreeDao
		implements SingleRoot /* CutAndPaste */ {

	@Override
	public void setRootDao(RiotDao rootDao) {
		((AbstractFileDao) rootDao).setCountSize(false);
		super.setRootDao(rootDao);
	}
	
	@Override
	public Object getRootNode(Object parent) {
		return new FileModel(rootFile);
	}
	
}
