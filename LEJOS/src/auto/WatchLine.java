package auto;

import lejos.robotics.subsumption.Behavior;

public class WatchLine implements Behavior{

	@Override
	public boolean takeControl() {
		return onLine();
	}
	
	private boolean onLine(){
		return Sensors.getReading(Sensors.blackness) > 0.5;
	}

	@Override
	public void action() {
		System.out.println("On Line!");
		Sensors.pilot.travel(10);
	}

	@Override
	public void suppress() {
		Sensors.pilot.stop();
	}

}
