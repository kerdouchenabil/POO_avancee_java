package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board {
	
	List<Clip> c ; 
	private Set<IBoardListener> lis;
	
	public Board() {
		c = new ArrayList<>();
	}
	public List<Clip> getContents(){return c ; }
	public void addClip(Clip clip) { c.add(clip);}
	public void addClip(List<Clip> clip) {
		c.addAll(clip);
	}
	public void removeClip(Clip clip) {c.remove(clip); }
	public void removeClip(List<Clip> clip) {c.removeAll(clip); }
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		for (Clip cc : c) {
			cc.draw(gc);
		} 
	}
	
	
	public void addListener(IBoardListener listener) {
		lis.add(listener);
	}

	public void notifyListener() {
		for (IBoardListener listener : lis)
			listener.boardChanged();
	}

	public void removeListener(IBoardListener listener) {
		lis.remove(listener);
	}
}
