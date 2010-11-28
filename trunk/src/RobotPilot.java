
import lejos.robotics.TachoMotor;
import lejos.robotics.navigation.TachoPilot;


public class RobotPilot extends TachoPilot{

	public RobotPilot(float leftWheelDiameter, float rightWheelDiameter,
			float trackWidth, TachoMotor leftMotor, TachoMotor rightMotor,
			boolean reverse) {
		super(leftWheelDiameter, rightWheelDiameter, trackWidth, leftMotor, rightMotor,
				reverse);
		// TODO Auto-generated constructor stub
	}
	
}
