package pobj.pinboard.editor;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import pobj.pinboard.editor.commands.Command;

public class CommandStack {
	private Stack<Command> redo;
	private Stack<Command> undo;
	private Set<ICommandStackListener> lis;

	public CommandStack() {
		
		redo = new Stack<Command>();
		undo = new Stack<Command>();
		lis = new HashSet<>();
		
	}

	public void addCommand(Command cmd) {
		
		undo.add(cmd);
		redo.clear();
		notifyListener();
	}

	public void undo() {
		
		Command l= undo.lastElement();
		undo.remove(undo.size()-1);
		l.undo();
		redo.add(l);
		notifyListener();
	}

	public void redo() {
		
		Command l = redo.lastElement();
		redo.remove(redo.size()-1);
		l.execute();
		undo.add(l);
		notifyListener();
	}


	public boolean isRedoEmpty() {
		return redo.isEmpty();
	}

	public boolean isUndoEmpty() {
		return undo.isEmpty();
	}
	
	private void notifyListener() {
		
		for (ICommandStackListener l : lis)
			l.commandStackChanged();
	}

	public void addListener(ICommandStackListener listener) {
		
		lis.add(listener);
		notifyListener();
	}

	public void removeListener(ICommandStackListener listener) {
		
		lis.remove(listener);
	}

}
