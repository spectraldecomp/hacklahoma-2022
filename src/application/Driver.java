package application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Driver {

	//Data
	private Timeline timeline;
	private View mainview;
	private GameOfLife game;
	
	//Constructor; controls time between generations when RUNNING
	public Driver(View mainview, GameOfLife game) {
		this.mainview = mainview;
		this.game = game;
		timeline = new Timeline(new KeyFrame(Duration.millis(500), this::doNext));
		timeline.setCycleCount(Timeline.INDEFINITE);
	}
	
	//Steps the simulation by one generation
	private void doNext(ActionEvent actionEvent) {
		if (Toolbar.isInfoEnabled == true) {
			int information = game.getInformation();
			game.adjustBounds(information);
			game.infoNext();
			mainview.draw();
		}
		else {
		game.next();
		mainview.draw();
		}
	}
	//Starts RUNNING
	public void start() {
		timeline.play();
	}
	//Stops RUNNING
	public void stop() {
		timeline.stop();
	}
	
}
