package pobj.pinboard.editor.commands;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandMove implements Command {
	
	private double x,y;
	private EditorInterface edit;
	private Clip rect;

	public CommandMove(EditorInterface editor, Clip c, double x, double y) {
		edit = editor;	rect = c;	this.x = x;	this.y = y;
	}

	@Override
	public void undo() {
		
		rect.move(-x, -y);
		edit.getBoard().notifyListener();

	}
	
	@Override
	public void execute() {
		
		rect.move(x, y);
		edit.getBoard().notifyListener();
	}
}
