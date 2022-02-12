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
	
	private Button nextButton;
	private Canvas canvas;
	private GameOfLife game;
	private Affine affine;
	private int mode = 1;
	
	
	public View(){
		nextButton = new Button("next");
		nextButton.setOnAction(actionEvent->{
			game.next();
			draw();
		});
		canvas = new Canvas(400, 400);
		canvas.setOnMousePressed(this::handleDraw);
		canvas.setOnMouseDragged(this::handleDraw);
		this.setOnKeyPressed(this::onKeyPressed);
		
		getChildren().addAll(nextButton, canvas);
		
		affine = new Affine();
		affine.appendScale(400 / 10, 400/10);
		game = new GameOfLife(10, 10);

	}
	
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
		double mouseX = event.getX();
		double mouseY = event.getY();
		
	try {
		Point2D gameCoords = affine.inverseTransform(mouseX, mouseY);
		
		int gameCoordX = (int) gameCoords.getX();
		int gameCoordY = (int) gameCoords.getY();
		
		game.setCellState(gameCoordX,gameCoordY, mode);
		draw();
	} catch (NonInvertibleTransformException e) {
		System.out.println("Something has gone terribly wrong");
		}
	}
	
	public void draw() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setTransform(affine);
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0,0,400,400);
		gc.setFill(Color.BLACK);
		for (int j = 0; j < game.width; j++) {
			for (int i = 0; i < game.height; i++) {
				if (game.getCellState(j, i) == 1) gc.fillRect(j,i,1,1);
			}
		}
		gc.setStroke(Color.GRAY);
		gc.setLineWidth(0.05);
		for (int j = 0; j <= game.width; j++) {
			gc.strokeLine(j, 0, j, 10);
		}
		for (int i = 0; i <= game.height; i++) {
			gc.strokeLine(0, i, 10, i);
		}
	}

}

