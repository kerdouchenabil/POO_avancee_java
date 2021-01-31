package pobj.pinboard.document;
import javafx.scene.paint.Color;

public abstract class AbstractClip implements Clip {
	protected double left,right,top,bottom;
	protected Color color;

	public AbstractClip(double left, double top, double right, double bottom, Color color) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.color = color;
	}

	
	public double getTop() {
		return top;
	}

	
	public double getLeft() {
		return left;
	}

	
	public double getBottom() {
		return bottom;
	}

	
	public double getRight() {
		return right;
	}

	
	public void setGeometry(double left, double top, double right, double bottom) {
		this.left = left;
		this.right = right;
		this.top = top ;
		this.bottom=bottom;
	}

	
	public void move(double x, double y) {
		left += x ;right += x ;top += y ; bottom += y;
		
	}

	
	public boolean isSelected(double x, double y) {
		
		return x >= left && x <= right && y >= top && y <= bottom;
	}

	
	public void setColor(Color c) {
		this.color = c ;
	}

	
	public Color getColor() {
		return color;
	}

}