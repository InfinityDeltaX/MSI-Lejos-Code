package auto;

import lejos.robotics.subsumption.Behavior;

public class Plow implements Behavior {

	@Override
	public boolean takeControl() {
		return Sensors.getReading(Sensors.isPressed1) > 0.5;
	}

	@Override
	public void action() {
		System.out.println("Plowing");
		Sensors.pilot.forward();
	}

	@Override
	public void suppress() {
		Sensors.pilot.stop();
	}

}
