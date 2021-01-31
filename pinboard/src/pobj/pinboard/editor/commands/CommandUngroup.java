package pobj.pinboard.editor.commands;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.document.Composite;

public class CommandUngroup implements Command{
	
	private EditorInterface edit;
	private Composite gr;

	public CommandUngroup(EditorInterface editor, Composite group) {
		
		edit = editor;
		gr = group;
	}

	@Override
	public void execute() {
		
		for (Clip c : gr.getClips()) edit.getBoard().addClip(c);
		edit.getBoard().removeClip(gr);
	}

	@Override
	public void undo() {
		
		for (Clip c : gr.getClips()) edit.getBoard().removeClip(c);
		edit.getBoard().addClip(gr);
	}

}
