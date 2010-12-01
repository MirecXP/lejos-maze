
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.TachoMotor;
import lejos.robotics.navigation.TachoPilot;


public class RobotPilot extends TachoPilot{
	protected Motor left;
	protected Motor right;
	protected UltrasonicSensor Uleft;
	protected UltrasonicSensor Uright;
	protected TouchSensor Ufront;

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
	
	public RobotPilot(float wheelDiameter, float trackWidth, TachoMotor leftMotor, TachoMotor rightMotor, UltrasonicSensor left, UltrasonicSensor right, TouchSensor front) {
		
		super(wheelDiameter, trackWidth, leftMotor, rightMotor);
		this.left = (Motor) leftMotor;
		this.right = (Motor) rightMotor;
		this.Uleft = left;
		this.Uright = right;
		this.Ufront = front;
		// TODO Auto-generated constructor stub
	}
	
	public void forward(){
		
		left.forward();
		right.forward();
		
	}
	
	public boolean correctYourSelf(){
		left.stop();
		right.stop();
		double range = 0;
		int leftd = Uleft.getDistance();
		int rightd = Uright.getDistance();
		String frontd = Ufront.isPressed()?"pressed":"not pressed";
		LCD.clearDisplay();
		LCD.drawString(leftd + "\n" + rightd + "\n" + frontd, 2, 2);
		LCD.refresh();
		int distAVG = (leftd + rightd) / 2;
		
		if (leftd > 23 || rightd > 23||Math.abs(leftd-rightd) > 7)
			return true;
		else if (rightd <18){
			getAwayFromWall();
			return correctYourSelf();
		}
		else if (Math.abs(Uleft.getDistance() - Uright.getDistance()) > range)
		{
			if (Uleft.getDistance() > Uright.getDistance())
			{
				left.rotate(-10);
				return correctYourSelf();
			}
			if  (Uleft.getDistance() < Uright.getDistance())
			{
				left.rotate(10);
				return correctYourSelf();
			}
		}
		else return true;
		return false;
	}
	public void getAwayFromWall(){
		int leftd = Uleft.getDistance();
		int rightd = Uright.getDistance();
		
		if (Math.abs(leftd - rightd) < 5)
		{
			if (leftd>=rightd)
			{
				left.rotate(-45);
				this.travel(3);
				left.rotate(45);
				this.travel(-3);
			}
			else {
				right.rotate(45);
				this.travel(3);
				right.rotate(-45);
				this.travel(-3);
			}
		}
		else {
			right.rotate(-10);
			left.rotate(10);
		}
	}
	
}
