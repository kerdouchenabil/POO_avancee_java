package pobj.pinboard.editor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import javafx.scene.control.MenuBar;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandAdd;
import pobj.pinboard.editor.commands.CommandColor;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.Composite;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import pobj.pinboard.editor.tools.ToolImage;
import pobj.pinboard.document.IBoardListener;
import pobj.pinboard.editor.commands.CommandGroup;
import pobj.pinboard.editor.commands.CommandRemove;
import pobj.pinboard.editor.commands.CommandUngroup;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolSelection;

public class EditorWindow implements EditorInterface, ICommandStackListener, ClipboardListener, IBoardListener{
	
	private Board board;
	private Tool t;
	private Label status;
	private Scene scene;
	private Stage stage;
	private VBox root;
	private Canvas canvas;
	private MenuBar menu;
	private ToolBar boutons;
	private File file;
	private Selection select;
	private Clipboard cb;
	private List<Tool> tool_list;
	private MenuItem delete;
	private MenuItem copy;
	private MenuItem paste;
	private MenuItem undo;
	private MenuItem redo;
	private ColorPicker cpick;
	private CommandStack stack;

	public EditorWindow(Stage stage){
		this.stage = stage;
		board = new Board();
		board.addListener(this);
		select = new Selection();
		cb = Clipboard.getInstance();
		cb.addListener(this);

		stage.onCloseRequestProperty().addListener((e) -> {
			cb.removeListener(this);
		});
		
		tool_list = new ArrayList<Tool>();
		tool_list.add(new ToolRect());
		tool_list.add(new ToolEllipse());
		tool_list.add(new ToolSelection());
		tool_list.add(new ToolImage());

		t = tool_list.get(0);
		stage.setTitle("Pinboard");
		root = new VBox();
		scene = new Scene(root);
		stage.setScene(scene);

		this.createMenuBarre();
		this.createToolBouton();
		stack = new CommandStack();
		stack.addListener(this);
		canvas = new Canvas(500, 500);
		this.configureMouseCanvas();
		root.getChildren().add(canvas);
		root.getChildren().add(new Separator());
		status = new Label("Filled rectangle tool");
		root.getChildren().add(status);
		stage.show();
	}

	private void createMenuBarre() {
		
		menu = new MenuBar();
		Menu file = new Menu("File");
		menu.getMenus().add(file);
		MenuItem newItem = new MenuItem("New");
		MenuItem close = new MenuItem("Close");
		file.getItems().add(newItem);
		file.getItems().add(close);
		newItem.setOnAction((e) -> new EditorWindow(new Stage()));

		close.setOnAction((e) -> {
			stage.close();
			cb.removeListener(this);
		});
		
		Menu m = new Menu("Edit");
		
		delete = new MenuItem("Delete");
		delete.setOnAction((e) -> delete());
		
		copy = new MenuItem("Copy");
		copy.setOnAction((e) -> {
			if (!select.getContents().isEmpty()) {
				cb.copyToClipboard(select.getContents());
			}
		});
		
		paste = new MenuItem("Paste");
		paste.setDisable(cb.isEmpty());
		paste.setOnAction((e) -> paste());

		MenuItem gr = new MenuItem("Group");
		MenuItem ungr = new MenuItem("Ungroup");
		
		undo = new MenuItem("Undo");
		redo = new MenuItem("Redo");

		undo.setOnAction((e) -> stack.undo());
		redo.setOnAction((e) -> stack.redo());

		gr.setOnAction((e) -> group());
		ungr.setOnAction((e) -> ungroup());

		m.getItems().add(copy);
		m.getItems().add(paste);
		m.getItems().add(delete);
		m.getItems().add(gr);
		m.getItems().add(ungr);
		m.getItems().add(undo);
		m.getItems().add(redo);

		menu.getMenus().add(m);

		Menu tools = new Menu("Tools");
		menu.getMenus().add(tools);
		root.getChildren().add(menu);
	}
	

	@Override
	public Board getBoard() {
		return this.board;
	}
	
	@Override
	public File getSelectedFile() {
		return file;
	}

	private void draw() {
		board.draw(canvas.getGraphicsContext2D());
		t.drawFeedback(EditorWindow.this, canvas.getGraphicsContext2D());
	}
	
	private void setLabelTxt(String txt) {
		status.setText(txt);
	}
	
	@Override
	public Color getCurrentColor() {
		return cpick.getValue();
	}
	
	private void setCurrentColor(Color currentColor) {
		if (select.getContents().isEmpty())
			return;
		for (Clip c : select.getContents()) {
			CommandColor cc = new CommandColor(this, c, currentColor);
			stack.addCommand(cc);
			cc.execute();
		}
	}

	
	private void delete() {
		
		if ( ! select.getContents().isEmpty()) {
			Command cr = new CommandRemove(this, select.getContents());
			cr.execute();
			stack.addCommand(cr);
			select.clear();		
		}

	}
	
	private void paste() {
		if ( ! cb.isEmpty()) {
			for (Clip c : cb.copyFromClipboard()) {
				Command cmd = new CommandAdd(this, c);
				cmd.execute();
				stack.addCommand(cmd);
			}
		}
			
	}

	private void ungroup() {
		
		if ( ! select.getContents().isEmpty()) {
			for (Clip c : select.getContents()) {
				if (!(c instanceof Composite))
					continue;
				Composite cg = (Composite) c;
				Command cu = new CommandUngroup(this, cg);
				cu.execute();
				stack.addCommand(cu);
				select.getContents().remove(c);
			}
		}

	}
	
	private void group() {
		
		if ( ! select.getContents().isEmpty()) {
			Command c = new CommandGroup(this, select.getContents());
			c.execute();
			stack.addCommand(c);
			select.clear();
		}

	}

	private void createToolBouton() {
		
		Button b_box = new Button("Box");
		Button b_ellipse = new Button("Ellipse");
		Button b_sel = new Button("Selection");
		Button b_img = new Button("Image");
		
		boutons = new ToolBar();

		boutons.getItems().add(b_box);
		boutons.getItems().add(b_ellipse);
		
		b_box.setOnAction((e) -> setCurrentTool(tool_list.get(0)));
		b_ellipse.setOnAction((e) -> setCurrentTool(tool_list.get(1)));

		b_img.setOnAction((e) -> {
			setCurrentTool(tool_list.get(2));
			openFileChooser();
		});

		cpick = new ColorPicker(Color.BLACK);
		cpick.setOnAction((e) -> setCurrentColor(cpick.getValue()));

		b_sel.setOnAction((e) -> setCurrentTool(tool_list.get(3)));
		
		boutons.getItems().add(b_img);
		boutons.getItems().add(b_sel);
		boutons.getItems().add(cpick);

		root.getChildren().add(boutons);
		
	}

	public void setCurrentTool(Tool to) {
		
		select.clear();
		board.notifyListener();
		t = to;
		setLabelTxt(to.getName());
		
	}

	private void configureMouseCanvas() {
		
		canvas.setOnMousePressed((e) -> {
			t.press(EditorWindow.this, e);
			t.drawFeedback(EditorWindow.this, canvas.getGraphicsContext2D());
		});
		
		canvas.setOnMouseDragged((e) -> {
			t.drag(EditorWindow.this, e);
			draw();
		});
		
		canvas.setOnMouseReleased((e) -> {
			t.release(this, e);
			draw();
		});
		
	}


	@Override
	public Selection getSelection() {
		return select;
	}

	@Override
	public CommandStack getUndoStack() {
		return stack;
	}

	private void openFileChooser() {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image", "*.png"),
				new FileChooser.ExtensionFilter("Image", "*.JPEG"), new FileChooser.ExtensionFilter("Image", "*.JPG"));
		this.file = chooser.showOpenDialog(stage);
	}
	
	@Override
	public void clipboardChanged() {
		
		this.paste.setDisable(cb.isEmpty());
	}
	
	@Override
	public void boardChanged() {
		
		board.draw(canvas.getGraphicsContext2D());
	}

	@Override
	public void commandStackChanged() {
		
		undo.setDisable(stack.isUndoEmpty());
		redo.setDisable(stack.isRedoEmpty());
	}

}
