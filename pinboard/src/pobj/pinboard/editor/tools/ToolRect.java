package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.document.CountourRect;


public class ToolRect implements Tool{
	
	private CountourRect elem;
	private boolean last_c;
	private CommandAdd cmd;
	private ClipRect c_r;
	private MouseEvent first_c;

	@Override
	public void press(EditorInterface i, MouseEvent e) {
		
		last_c = false;
		first_c = e;
		elem = new CountourRect(e.getX(), e.getSceneY(), e.getSceneX(), e.getSceneY(), i.getCurrentColor());

	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		
		double left = Math.min(first_c.getX(), e.getX());
		double right = Math.max(first_c.getX(), e.getX());
		double top = Math.min(first_c.getY(), e.getY());
		double bottom = Math.max(first_c.getY(), e.getY());

		elem.setGeometry(left, top, right, bottom);
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		
		drag(i, e);
		c_r = new ClipRect(elem.getLeft(), elem.getTop(), elem.getRight(), elem.getBottom(), i.getCurrentColor());
		cmd = new CommandAdd(i, c_r);
		i.getUndoStack().addCommand(cmd);
		cmd.execute();
		last_c = true;

	}

	
	@Override
	public String getName() {
		
		return "Filled rectangle tool";
	}
	
	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		
		if (!last_c) {
			elem.draw(gc);
		}

	}



}
