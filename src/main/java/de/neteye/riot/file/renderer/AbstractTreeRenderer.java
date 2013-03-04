package de.neteye.riot.file.renderer;

import java.io.PrintWriter;

import org.riotfamily.common.ui.ObjectRenderer;
import org.riotfamily.common.ui.RenderContext;
import org.riotfamily.common.util.TagWriter;

public abstract class AbstractTreeRenderer implements ObjectRenderer {
	
	@Override
	public void render(Object obj, RenderContext context, PrintWriter writer) {
		if (obj != null) {
			
			boolean folder = isFolder(obj);
			String icon = folder ? "icon-folder" : "icon-page";
			
			new TagWriter(writer).start("span")
					.attribute("class", icon).body().end();
			
			
			if (folder) {
				TagWriter span = new TagWriter(writer).start("span")
						.attribute("class", "folder").body();
				
					writer.print(getTitle(obj));
				span.end();
			}
			else {
				writer.print(getTitle(obj));
			}
		}
		
	}
	
	protected abstract boolean isFolder(Object object);
	
	protected abstract String getTitle(Object object);

}
