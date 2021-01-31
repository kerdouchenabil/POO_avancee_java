package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipEllipse extends AbstractClip {

	public ClipEllipse(double left, double top, double right, double bottom, Color color) {
		super(left, top, right, bottom, color);
	}

	
	@Override
	public boolean isSelected(double x, double y) {
		double cx = (left + right)/2;
		double cy = (top + bottom)/2;
		double rx =	(right - left)/2;
		double ry = (bottom - top)/2;
		double res = ((x-cx)/rx)*((x-cx)/rx)  +  ((y-cy)/ry)*((y-cy)/ry);
		return res<=1;
	}
	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(color);
		ctx.fillOval(left, top, right-left, bottom- top);
	}


	@Override
	public Clip copy() {
		// TODO Auto-generated method stub
		return new ClipEllipse(left, top, right, bottom, color);// a vérifier copier la couleur aussi 

	}
}
