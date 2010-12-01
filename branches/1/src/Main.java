import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.TachoMotor;
import lejos.robotics.navigation.Pilot;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LCD.drawString("Maze Solved Bitches", 2, 2);
		LCD.refresh();
		Motor mA = Motor.A;
		Motor mB = Motor.B;
		
		/*TouchSensor bump = new TouchSensor(SensorPort.S1);
		
		mA.forward();
		mB.forward();
		while (mA.isMoving()||mB.isMoving()){
			if (bump.isPressed()){
				mA.stop();
				mB.stop();
			}
		}
		
		mB.rotate(-190);
		mA.rotate(190);
		
		
		while (mA.isMoving()||mB.isMoving()){
			if (bump.isPressed()){
				mA.stop();
				mB.stop();
			}
		}*/
		//float leftWheelDiameter, float rightWheelDiameter,
		//float trackWidth, TachoMotor leftMotor, TachoMotor rightMotor,
		//boolean reverse
		RobotPilot rbp = new RobotPilot(2.25f,4.65f,mB,mA);
		rbp.forward();
		UltrasonicSensor right = new UltrasonicSensor(SensorPort.S2);
		UltrasonicSensor left = new UltrasonicSensor(SensorPort.S3);
		UltrasonicSensor front = new UltrasonicSensor(SensorPort.S1);
		
		right.continuous();
		left.continuous();
		front.continuous();

		
		while (rbp.isMoving())
		{
			if (front.getDistance() < 10){
				rbp.stop();
				rbp.travel(-4);
				//check sonar sensor
				if (right.getDistance()<15)
					rbp.rotate(90);//left
				else rbp.rotate(-90);//right
				while(rbp.isMoving()){}
				rbp.travel(85);
				while(rbp.isMoving()){}
			}
			if (right.getDistance() > 15){
				rbp.travel(3);
				rbp.rotate(-90);//right
				rbp.stop();
				while(rbp.isMoving()){}
				rbp.travel(8);
				while(rbp.isMoving()){}
			}
			rbp.forward();
		}
		while (true){};
		
		
	}

}