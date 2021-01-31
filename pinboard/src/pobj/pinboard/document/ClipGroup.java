package pobj.pinboard.document;

import java.util.List;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipGroup extends AbstractClip implements Composite {

	private List<Clip> child;

	public ClipGroup() {
		
		super(0, 0, 0, 0, Color.TRANSPARENT);
		child = new ArrayList<>();
		
	}


	@Override
	public Clip copy() {
		ClipGroup cp = new ClipGroup();
		for (Clip c : child)
			cp.addClip(c.copy());
		return cp;
	}
	
	
	@Override
	public void draw(GraphicsContext ct) {
		
		for (Clip c : child)
			c.draw(ct);
	}


	@Override
	public List<Clip> getClips() {
		return child;
	}

	@Override
	public void addClip(Clip ta) {
		child.add(ta);
		updateCarreEnglobant(ta);

	}

	@Override
	public void removeClip(Clip tr) {
		
		child.remove(tr);
		updateCarreEnglobant();
		
	}

	private void updateCarreEnglobant(Clip c) {
		
		if (child.size() != 1) {
			double right = Math.max(getRight(), c.getRight());
			double bottom = Math.max(getBottom(), c.getBottom());		
			double left = Math.min(getLeft(), c.getLeft());
			double top = Math.min(getTop(), c.getTop());

			setGeometry(left, top, right, bottom);
		}
		else {
			setGeometry(c.getLeft(), c.getTop(), c.getRight(), c.getBottom());
		}
	}

	private void updateCarreEnglobant() {
		if (child.isEmpty()) {
			setGeometry(0, 0, 0, 0);
			return;
		} else {

			double right = child.get(0).getRight();
			double bottom = child.get(0).getBottom();
			double left = child.get(0).getLeft();
			double top = child.get(0).getTop();

			for (Clip c : child) {
				right = Math.max(right, c.getRight());
				bottom = Math.max(bottom, c.getBottom());
				left = Math.min(left, c.getLeft());
				top = Math.min(top, c.getTop());

			}

			setGeometry(left, top, right, bottom);
		}

	}

	@Override
	public void move(double x, double y) {
		super.move(x, y);
		for (Clip c : child)
			c.move(x, y);
	}


}
