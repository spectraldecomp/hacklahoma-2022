package application;

import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;

public class View extends VBox {
	
	Button nextButton;
	Canvas canvas;
	
	public View(){
		nextButton = new Button("next");
		canvas = new Canvas(400, 400);
		
		getChildren().addAll(nextButton, canvas);
	}

}

