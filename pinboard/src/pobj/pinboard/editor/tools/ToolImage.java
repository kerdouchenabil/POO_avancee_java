package pobj.pinboard.editor.tools;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandAdd;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.editor.EditorInterface;


public class ToolImage implements Tool{
	
	private ClipImage elem;
	private File file;

	@Override
	public void press(EditorInterface i, MouseEvent e) {
		
		file = i.getSelectedFile();
		if (file == null) return;
		
		elem = new ClipImage(e.getX(), e.getY(), i.getSelectedFile());
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		
		if (file == null) return;
		
		elem.setGeometry(e.getX(), e.getY(), 0, 0);
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		
		if (file == null) return;
		
		drag(i, e);
		Command c = new CommandAdd(i, elem);
		i.getUndoStack().addCommand(c);
		c.execute();
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		
		if (file == null) return;
		elem.draw(gc);
	}

	@Override
	public String getName() {
		return "Image tool";
	}

}
