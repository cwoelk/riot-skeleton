package de.neteye.riot.file.commands;

import java.io.File;

import org.riotfamily.core.screen.DefaultScreenContext;
import org.riotfamily.core.screen.RiotScreen;
import org.riotfamily.core.screen.ScreenContext;
import org.riotfamily.core.screen.list.command.CommandContext;
import org.riotfamily.core.screen.list.command.CommandResult;
import org.riotfamily.core.screen.list.command.impl.EditCommand;
import org.riotfamily.core.screen.list.command.result.GotoUrlResult;

import de.neteye.riot.file.model.FileModel;

public class GoIntoFolderCommand extends EditCommand {

	@Override
	protected String getIcon() {
		return "folder_go";
	}
	
	@Override
	protected boolean isEnabled(CommandContext context, Object item) {
		boolean enabled = false;
		if (item instanceof FileModel) {
			enabled = ((FileModel) item).isDirectory();
		}
		else if (item instanceof File) {
			enabled = ((File) item).isDirectory();			
		}
		return enabled && super.isEnabled(context, item);
	}
	
	@Override
	protected CommandResult execute(CommandContext context, Object item) {
		RiotScreen parentScreen = context.getScreenContext().getScreen().getParentScreen();
		ScreenContext parentContext = new DefaultScreenContext(
				parentScreen, context.getRequest(), null, null, false);
		
		ScreenContext childContext = parentContext.createItemContext(item);
		GotoUrlResult result = new GotoUrlResult(childContext);
		result.setTarget("self");
		return result;
	}

}
