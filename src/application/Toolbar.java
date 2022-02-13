package application;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
public class Toolbar extends ToolBar {

	private View mainview;
	
	public Toolbar(View mainview) {
		this.mainview = mainview;
		Button draw = new Button("Pen");
		draw.setOnAction(this::handleDraw);
		Button erase = new Button("Eraser");
		erase.setOnAction(this::handleErase);
		Button next = new Button("Next generation");
		next.setOnAction(this::handleNext);
		Button reset = new Button("Reset");
		reset.setOnAction(this::handleReset);
		Button start = new Button("Start");
		start.setOnAction(this::handleStart);
		Button stop = new Button("Stop");
		stop.setOnAction(this::handleStop);
		getItems().addAll(draw, erase, next, reset, start, stop);
	}
	
	private void handleNext(ActionEvent actionEvent) {
		mainview.setState(mainview.RUNNING);
		mainview.getGameOfLife().next();
		mainview.draw();
	}
	private void handleDraw(ActionEvent actionEvent) {
		mainview.setDrawMode(1);
	}
	private void handleErase(ActionEvent actionEvent) {
		mainview.setDrawMode(0);
	}
	private void handleReset(ActionEvent actionEvent) {
		mainview.setState(mainview.EDITING);
		mainview.draw();
		
	}
	private void handleStart(ActionEvent actionEvent) {
		mainview.setState(mainview.RUNNING);
		mainview.getDriver().start();
	}
	private void handleStop(ActionEvent actionEvent) {
		mainview.getDriver().stop();
	}
}
