package pobj.pinboard.editor.commands;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandAdd implements Command {
	
	private EditorInterface edit;
	private Clip toadd;

	public CommandAdd(EditorInterface editor, Clip toAdd) {
		
		this.edit = editor;
		this.toadd = toAdd;

	}

	@Override
	public void execute() {
		
		edit.getBoard().addClip(toadd);
	}

	@Override
	public void undo() {
		
		edit.getBoard().removeClip(toadd);
	}

}
