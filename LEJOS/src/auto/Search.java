package auto;

import java.util.HashMap;
import java.util.Map;

import lejos.hardware.motor.Motor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;

public class Search implements Behavior{

	static boolean go = true;

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		go = true;
		System.out.println("Searching");
		search();
	}

	@Override
	public void suppress() {
		go = false;
	}

	private static void search(){
		while(go){
			float angle = getClosestObstacleHeading(10);
			align(angle);
			driveTowardObject();
		}
	}

	private static float getClosestObstacleHeading(int resolution){
		Map<Float, Float> map = scan(resolution);
		float minDist = Float.MAX_VALUE;
		float bestHeading = 0;
		for(Float f : map.keySet()){
			if(minDist > map.get(f)){
				minDist = map.get(f);
				bestHeading = f;
			}
		}
		return bestHeading;
	}

	private static void driveTowardObject(){
		float currDist = Sensors.getReading(Sensors.distance);
		float lastDist = currDist;

		while(currDist <= lastDist && go){
			Sensors.pilot.travel(5);
			lastDist = currDist;
			currDist = Sensors.getReading(Sensors.distance);
		}
	}

	private static Map<Float, Float> scan(int resolution){ //res is the number of samples to take.
		Map<Float, Float> output = new HashMap<Float, Float>();

		int currentAngle = 0;
		while(currentAngle <= 360 && go){
			Motor.C.rotateTo(currentAngle, false);
			float heading = Sensors.getReading(Sensors.heading);
			float dist = Sensors.getReading(Sensors.distance);
			output.put(heading, dist);
		}

		return output;
	}

	private static void align(float angle){
		final int tolerance = 2;
		float currentAngle = 0;

		do{
			currentAngle = Sensors.getReading(Sensors.heading);
			Sensors.pilot.rotate(currentAngle-angle);
		} while(Math.abs(currentAngle-angle) >= tolerance && go);
	}

}
