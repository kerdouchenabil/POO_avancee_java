package pobj.pinboard.editor;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import javafx.scene.canvas.GraphicsContext;
import pobj.pinboard.document.ClipRect;
import javafx.scene.paint.Color;

public class Selection {
	
	private Set<Clip> sel;

	public Selection() {
		sel = new HashSet<>();
	}

	public void select(Board board, double x, double y) {
		
		clear();
		for (Clip c : board.getContents()) {
			if (c.isSelected(x, y)) {
				if (!sel.contains(c))	sel.add(c);
				return;
			}
		}
		
	}

	public void toogleSelect(Board board, double x, double y) {
		
		for (Clip c : board.getContents()) {
			if (c.isSelected(x, y)) {
				if (sel.contains(c))
					sel.remove(c);
				else
					sel.add(c);
				return;
			}
		}
		
	}

	public void clear() {
		
		sel.clear();
	}

	public List<Clip> getContents() {
		
		return new ArrayList<Clip> (sel);
	}

	public void drawFeedback(GraphicsContext gc) {
		
		ClipRect c = new ClipRect(0, 0, 0, 0, Color.BLUE);
		
		for (Clip s : sel) {
			c.setGeometry(s.getLeft(), s.getTop(), s.getRight(), s.getBottom());
			c.draw(gc);
		}
	}
}
