package pobj.pinboard.document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ClipImage extends AbstractClip {
	private Image img;
	private File file;

	public ClipImage(double left, double top, File fn) {
		
		super(left, 0, 0, 0, Color.WHITE);
		file = fn;
		
		try {
			img = new Image(new FileInputStream(fn.getAbsoluteFile()));
			setGeometry(left, top, left + img.getWidth(), top + img.getHeight());
		} catch (FileNotFoundException e) {
			System.out.println("file not found !");
		}
		
	}

	@Override
	public Clip copy() {
		
		return new ClipImage(getLeft(), getTop(), file);
	}
	
	@Override
	public void draw(GraphicsContext ctx) {
		
		ctx.drawImage(img, getLeft(), getTop());
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		
		super.setGeometry(left, top, left + img.getWidth(), top + img.getHeight());
	}

}
