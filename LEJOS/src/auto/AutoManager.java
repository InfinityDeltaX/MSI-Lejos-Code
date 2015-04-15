package auto;

import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class AutoManager {

	public static void main(String[] args) {
		Behavior[] behaviors = new Behavior[]{new Search(), new Plow(), new WatchLine()};
		Arbitrator a = new Arbitrator(behaviors);
		a.start();
	}

}
