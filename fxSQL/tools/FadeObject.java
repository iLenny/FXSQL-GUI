package fxSQL.tools;

import javafx.animation.FadeTransition;

import javafx.scene.Node;
import javafx.util.Duration;

public final class FadeObject {
	private static FadeTransition fadeTransition;
	
	public static void fadeIn(Node object, Duration duration) {
		fadeTransition = new FadeTransition(duration, object);
		fadeTransition.setFromValue(0);
		fadeTransition.setToValue(1);
		fadeTransition.play();
	}
	
	public static void fadeOut(Node object, Duration duration) {
		fadeTransition = new FadeTransition(duration, object);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.play();
	}
	
	public static void fadeIn(Node object, Duration duration, Runnable afterMethod) {
		fadeTransition = new FadeTransition(duration, object);
		fadeTransition.setFromValue(0);
		fadeTransition.setToValue(1);
		fadeTransition.setOnFinished(e-> afterMethod.run());
		fadeTransition.play();
	}
	
	public static void fadeOut(Node object, Duration duration, Runnable afterMethod) {
		fadeTransition = new FadeTransition(duration, object);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.setOnFinished(e-> afterMethod.run());
		fadeTransition.play();
	}
}
