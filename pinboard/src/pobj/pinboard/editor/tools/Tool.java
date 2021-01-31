	package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import pobj.pinboard.editor.EditorInterface;
import javafx.scene.input.MouseEvent;

public interface Tool{
	
	public String getName( );
	public void press(EditorInterface i, MouseEvent e);
	public void drag(EditorInterface i, MouseEvent e);
	public void release(EditorInterface i, MouseEvent e);
	public void drawFeedback(EditorInterface i, GraphicsContext gc);

}
