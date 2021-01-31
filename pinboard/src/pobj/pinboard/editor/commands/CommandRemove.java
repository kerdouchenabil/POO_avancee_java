package pobj.pinboard.editor.commands;

import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandRemove implements Command {
	private EditorInterface edit;
	private List<Clip> ls;

	public CommandRemove(EditorInterface editor, List<Clip> clips) {
		
		edit = editor;
		ls = clips;
	}

	@Override
	public void execute() {
		
		edit.getBoard().removeClip(ls);

	}

	@Override
	public void undo() {
		
		edit.getBoard().addClip(ls);
		
	}

}
