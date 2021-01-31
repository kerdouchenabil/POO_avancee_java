package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.Composite;
import javafx.scene.paint.Color;
import pobj.pinboard.editor.EditorInterface;

public class CommandColor implements Command {
	
	private Color newColor;
	private EditorInterface editor;
	private Clip clip;
	private List<Command> listCommand;

	public CommandColor(EditorInterface edi, Clip c, Color color) {
		
		clip = c;
		c.getColor();
		newColor = color;
		editor = edi;
		listCommand = new ArrayList<>();
		if (c instanceof Composite) {
			decompose((Composite) c);
		}
		else {
			listCommand.add(new CommandSimpleColor(c, color));
		}

	}

	private void decompose(Composite c) {
		
		List<Clip> ch = c.getClips();
		for (Clip cl : ch) {
			if (cl instanceof Composite) {
				this.decompose((Composite) cl);
			} else {
				this.listCommand.add(new CommandSimpleColor(cl, newColor));
			}
		}
		
	}

	@Override
	public void execute() {
		
		for (Command cmd : listCommand) cmd.execute();
		editor.getBoard().notifyListener();
		
	}

	@Override
	public void undo() {
		
		for (Command c : listCommand) c.undo();
		editor.getBoard().notifyListener();

	}

	private class CommandSimpleColor implements Command {
		
		private Clip c;
		private Color old;
		private Color newc;
		
		@Override
		public void execute() {
			
			this.c.setColor(newc);
		}

		public CommandSimpleColor(Clip c, Color newColor) {
			
			this.c = c;
			this.newc = newColor;
			this.old = c.getColor();
		}

		@Override
		public void undo() {
			
			this.c.setColor(old);
		}

	}

}
