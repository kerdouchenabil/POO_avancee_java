package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import pobj.pinboard.document.ClipEllipse;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;
import pobj.pinboard.document.CountourEllipse;

public class ToolEllipse implements Tool{
	
	private CommandAdd cmd;
	private ClipEllipse c_ell;
	private boolean last_c;
	private CountourEllipse elem;
	private MouseEvent first_c;

	@Override
	public void press(EditorInterface i, MouseEvent e) {
		
		first_c = e;
		elem = new CountourEllipse(e.getX(), e.getSceneY(), e.getSceneX(), e.getSceneY(), i.getCurrentColor());
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		
		double left = Math.min(first_c.getX(), e.getX());
		double right = Math.max(first_c.getX(), e.getX());
		double top = Math.min(first_c.getY(), e.getY());
		double bottom = Math.max(first_c.getY(), e.getY());

		this.elem.setGeometry(left, top, right, bottom);
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		
		drag(i, e);
		c_ell = new ClipEllipse(elem.getLeft(), elem.getTop(), elem.getRight(), elem.getBottom(), elem.getColor());
		cmd = new CommandAdd(i, c_ell);
		i.getUndoStack().addCommand(cmd);
		cmd.execute();

	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		
		if (last_c) {
			c_ell.draw(gc);
			last_c = false;
			return;
		}
		elem.draw(gc);
		
	}

	@Override
	public String getName() {
		
		return "Filled Ellipse";
	}

}
