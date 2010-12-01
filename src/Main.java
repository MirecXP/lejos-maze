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
		UltrasonicSensor right = new UltrasonicSensor(SensorPort.S2);
		UltrasonicSensor left = new UltrasonicSensor(SensorPort.S3);
		TouchSensor front = new TouchSensor(SensorPort.S1);
		right.continuous();
		left.continuous();
		RobotPilot rbp = new RobotPilot(2.25f,4.65f,mB,mA,left,right,front);
		rbp.setMoveSpeed((rbp.getMoveSpeed()/2));
		rbp.setTurnSpeed(rbp.getTurnSpeed()/2);
		
		
		while(!rbp.correctYourSelf()){}
		rbp.forward();
		int rightWallDistance; 
		
		while (true)
		{
			if (front.isPressed()){
				rbp.stop();
				rbp.travel(-2);
				//check sonar sensor
				if (left.getDistance()<30)
					rbp.rotate(90);//left
				else rbp.rotate(-90);//right
				while(rbp.isMoving()){}
				rbp.travel(5);
				while(rbp.isMoving()){}
			}
			if (left.getDistance() > 30){
				rbp.travel((float) -2.5);
				rbp.rotate(-90);//right
				rbp.stop();
				while(rbp.isMoving()){}
				rbp.travel(7);
				while(rbp.isMoving()){}
			}
			//while(rbp.isMoving()&&right.getDistance() > 20&&front.getDistance() < 10){}
			while(!rbp.correctYourSelf()){}
			rbp.travel(5);
		}
		
		
	}

}