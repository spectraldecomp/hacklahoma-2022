package application;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
public class Toolbar extends ToolBar {
	
	//Data
	private View mainview;
	public static boolean isInfoEnabled = false;
	
	//Constructor; creates button toolbar
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
		Button infoOn = new Button("Control Theory - On");
		infoOn.setOnAction(this::handleInfoOn);
		Button infoOff = new Button("Control Theory - Off");
		infoOff.setOnAction(this::handleInfoOff);
		
		getItems().addAll(draw, erase, next, reset, start, stop, infoOn, infoOff);
	}
	//Event handlers
	private void handleNext(ActionEvent actionEvent) {
		if (isInfoEnabled == true) {
			mainview.setState(mainview.RUNNING);
			int information = mainview.getGameOfLife().getInformation();
			mainview.getGameOfLife().adjustBounds(information);
			mainview.getGameOfLife().infoNext();
			mainview.draw();	
		}
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
	private void handleInfoOn(ActionEvent actionEvent) {
		isInfoEnabled = true;
	}
	private void handleInfoOff(ActionEvent actionEvent) {
		isInfoEnabled = false;
	}
}
