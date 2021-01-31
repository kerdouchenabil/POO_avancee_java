package pobj.pinboard.editor.tools;

import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandMove;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;


public class ToolSelection implements Tool {
	
	private MouseEvent last_c;
	private List<CommandMove> mv;

	@Override
	public void press(EditorInterface i, MouseEvent e) {
		
		if (e.isShiftDown()) {
			i.getSelection().toogleSelect(i.getBoard(), e.getX(), e.getY());
		} else {
			i.getSelection().select(i.getBoard(), e.getX(), e.getY());
		}
		
		last_c = e;

	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		
		for (Clip c : i.getSelection().getContents()) {
			CommandMove cm = new CommandMove(i, c, getX(e), getY(e));
			cm.execute();
			i.getUndoStack().addCommand(cm);
		}

		this.last_c = e;
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		//rien a faire
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		
		i.getBoard().draw(gc);
		i.getSelection().drawFeedback(gc);
	}
	
	private double getX(MouseEvent e) {
		
		return e.getX() - last_c.getX();
	}

	private double getY(MouseEvent e) {
		
		return e.getY() - last_c.getY();
	}

	@Override
	public String getName() {

		return "Selection Tool";
	}

}
