package auto;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.DifferentialPilot;

public class Sensors {
	public final static Port one;
	public final static Port two;
	public final static Port three;
	public final static Port four;
	
	public static final SensorModes ultrasonic;
	public static final SensorModes color = null;
	public static final SensorModes touch1;
	public static final SensorModes touch2;
	public static final SensorModes gyro;
	
	public static final SampleProvider distance;
	public static final SampleProvider heading;
	public static final SampleProvider isPressed1;
	public static final SampleProvider isPressed2;
	
	public static final DifferentialPilot pilot = new DifferentialPilot(3, 3, Motor.A, Motor.B);
	
	
	static{
		one = LocalEV3.get().getPort("S1");
		two = LocalEV3.get().getPort("S2");
		three = LocalEV3.get().getPort("S3");
		four = LocalEV3.get().getPort("S4");
		
		ultrasonic = new EV3UltrasonicSensor(one);
		//color = new EV3ColorSensor(two);
		touch1 = new EV3TouchSensor(three);
		touch2 = new EV3TouchSensor(four);
		gyro = new EV3GyroSensor(two);
		
		distance = ultrasonic.getMode("Distance");
		heading = gyro.getMode("Angle");
		isPressed1 = touch1.getMode("Touch");
		isPressed2 = touch2.getMode("Touch");
	}
	
	public static float getReading(SampleProvider s){
		float[] output = new float[1];
		s.fetchSample(output, 0);
		return output[0];
	}
}
