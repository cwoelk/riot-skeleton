package de.neteye.riot.file.model;

import java.io.File;
import java.util.Date;

public class FileModel {

	private String id;
	
	private String path;
	
	private File file;
	
	public FileModel() {
	}
	
	public FileModel(String id) {
		this.id = id;
		this.path = idToPath(this.id);
		this.file = new File(this.path);
	}
	
	public FileModel(File file) {
		this.file = file;
		this.path = file.getAbsolutePath();
		this.id = pathToId(this.path);
	}

	private String idToPath(String id) {
		return /*id != null ?*/ id.replaceAll(":", "/") /*: "/"*/;
	}

	private String pathToId(String path) {
		return /*path != null ?*/ path.replaceAll("/", ":") /*: ":"*/;
	}
	
	public String getId() {
		return id;
	}
	
	public String getPath() {
		return path;
	}
	
	public File getFile() {
		return file;
	}

	public String getName() {
		return file.getName();
	}
	
	public void setName(String name) {
		// fdsfkjshf
	}

	public Object getParent() {
		String parentPath = file.getParent();
		if (parentPath != null) {
			return new FileModel(pathToId(parentPath));
		}
		return null;
	}

	public Date getLastModified() {
		return new Date(file.lastModified());
	}
	
	public boolean isDirectory() {
		return file.isDirectory();
	}
	
}
