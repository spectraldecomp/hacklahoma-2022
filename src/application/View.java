package application;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

public class View extends VBox {
	
	private Button nextButton;
	private Canvas canvas;
	private GameOfLife game;
	private Affine affine;
	
	
	public View(){
		nextButton = new Button("next");
		nextButton.setOnAction(actionEvent->{
			game.next();
			draw();
		});
		canvas = new Canvas(400, 400);
		
		getChildren().addAll(nextButton, canvas);
		
		affine = new Affine();
		affine.appendScale(400 / 10, 400/10);
		game = new GameOfLife(10, 10);
		
		game.setAlive(0, 3);
		game.setAlive(1, 3);
		game.setAlive(2, 3);
		game.setAlive(3, 4);
		game.setAlive(3, 5);
		game.setAlive(3, 6);
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

