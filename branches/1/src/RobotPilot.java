
import lejos.nxt.Motor;
import lejos.robotics.TachoMotor;
import lejos.robotics.navigation.TachoPilot;


public class RobotPilot extends TachoPilot{
	protected Motor left;
	protected Motor right;

	public RobotPilot(float leftWheelDiameter, float rightWheelDiameter,
			float trackWidth, TachoMotor leftMotor, TachoMotor rightMotor,
			boolean reverse) {
		super(leftWheelDiameter, rightWheelDiameter, trackWidth, leftMotor, rightMotor,
				reverse);
		// TODO Auto-generated constructor stub
	}
	
	public RobotPilot(float wheelDiameter, float trackWidth, TachoMotor leftMotor, TachoMotor rightMotor) {
		
		super(wheelDiameter, trackWidth, leftMotor, rightMotor);
		this.left = (Motor) leftMotor;
		this.right = (Motor) rightMotor;
		// TODO Auto-generated constructor stub
	}
	
	public void forward(){
		left.forward();
		right.forward();
	}
	
}
