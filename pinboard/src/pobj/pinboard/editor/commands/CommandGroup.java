package pobj.pinboard.editor.commands;

import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.document.ClipGroup;

public class CommandGroup implements Command {
	
	private EditorInterface edit;
	private List<Clip> l_c;
	private ClipGroup gr;

	public CommandGroup(EditorInterface editor, List<Clip> rects) {
		
		super();
		this.edit = editor;
		this.l_c = rects;
	}

	@Override
	public void execute() {
		
		gr = new ClipGroup();

		for (Clip c : l_c) gr.addClip(c);

		edit.getBoard().removeClip(l_c);
		edit.getBoard().addClip(gr);
	}

	@Override
	public void undo() {
		
		edit.getBoard().removeClip(gr);
		for (Clip c : gr.getClips()) edit.getBoard().addClip(c);

	}

}
