package application;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;


public class View extends VBox {
	
	//Data
	private Canvas canvas;
	private GameOfLife game;
	private GameOfLife initial;
	private Driver driver;
	private Affine affine;
	private int mode = 1;
	private int state = EDITING;
	public static final int EDITING = 0;
	public static final int RUNNING = 1;
	
	//Constructor
	public View(){

		canvas = new Canvas(752, 782);
		canvas.setOnMousePressed(this::handleDraw);
		canvas.setOnMouseDragged(this::handleDraw);
		this.setOnKeyPressed(this::onKeyPressed);
		Toolbar toolbar = new Toolbar(this);
		getChildren().addAll(toolbar, canvas);
		
		affine = new Affine();
		affine.appendScale(50, 50);
		initial = new GameOfLife(15, 15);
		game = GameOfLife.copy(initial);

	}
	// Switches between pen and eraser mode via "1" and "2" keys
	private void onKeyPressed(KeyEvent keyEvent) {
		if(keyEvent.getCode() == KeyCode.DIGIT1) {
			mode = 1;
		}
		else if (keyEvent.getCode() == KeyCode.DIGIT2) {
			mode = 0; 
		}
	}
	//Method Handler for draw
	private void handleDraw(MouseEvent event) {
		
		if (state == RUNNING) return;
		
		double mouseX = event.getX();
		double mouseY = event.getY();
		
	try {
		Point2D gameCoords = affine.inverseTransform(mouseX, mouseY);
		
		int gameCoordX = (int) gameCoords.getX();
		int gameCoordY = (int) gameCoords.getY();
		
		initial.setCellState(gameCoordX,gameCoordY, mode);
		draw();
	} catch (NonInvertibleTransformException e) {
		System.out.println("Something has gone terribly wrong");
		}
	}
	//Draws scene; calls drawGameOfLife
	public void draw() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setTransform(affine);
		gc.setFill(Color.LIGHTGOLDENRODYELLOW);
		gc.fillRect(0,0,800,800);
		
		if (state == EDITING) drawGameOfLife(initial);
		else drawGameOfLife(game);
		gc.setStroke(Color.GRAY);
		gc.setLineWidth(0.05);
		for (int j = 0; j <= game.width; j++) {
			gc.strokeLine(j, 0, j, 15);
		}
		for (int i = 0; i <= game.height; i++) {
			gc.strokeLine(0, i, 15, i);
		}
	}
	//Draws generation of cells
	public void drawGameOfLife(GameOfLife draw) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.FIREBRICK);
		for (int j = 0; j < draw.width; j++) {
			for (int i = 0; i < draw.height; i++) {
				if (draw.getCellState(j, i) == 1) gc.fillRect(j,i,1,1);
			}
		}
	}
	
	//Getters
	public GameOfLife getGameOfLife() {
		return game;
	}
	public Driver getDriver() {
		return driver;
	}
	
	//Setters
	//Changes mode of drawing
	public void setDrawMode(int mode) {
		this.mode = mode;
	}
	//Changes game state -- Editing or Running: The board cannot be edited in running mode
	public void setState(int state) {
		if (state == this.state) return;
		if (state == RUNNING) {
		this.game = GameOfLife.copy(initial);
		driver = new Driver(this, game);
		}
		this.state = state;
	}
}

