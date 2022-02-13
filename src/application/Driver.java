package application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Driver {

	private Timeline timeline;
	private View mainview;
	private GameOfLife game;
	
	public Driver(View mainview, GameOfLife game) {
		this.mainview = mainview;
		this.game = game;
		timeline = new Timeline(new KeyFrame(Duration.millis(500), this::doNext));
		timeline.setCycleCount(Timeline.INDEFINITE);
	}
	
	private void doNext(ActionEvent actionEvent) {
		game.next();
		mainview.draw();
	}
	
	public void start() {
		timeline.play();
	}
	
	public void stop() {
		timeline.stop();
	}
	
}
