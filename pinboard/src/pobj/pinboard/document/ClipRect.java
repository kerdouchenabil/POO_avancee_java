package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipRect extends AbstractClip implements Clip {

	public ClipRect(double left, double top, double right, double bottom, Color color) {
		super(left, top, right, bottom, color);
	}

	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(color);
		ctx.fillRect(left, top, right - left, bottom - top);
		
	}

	@Override
	public Clip copy() {
		return new ClipRect(left, top, right, bottom, color);// a vérifier copier la couleur aussi 
	}

}
