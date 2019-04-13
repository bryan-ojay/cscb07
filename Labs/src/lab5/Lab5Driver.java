package lab5;

public class Lab5Driver {
	public static void main (String args[]) {
		Car lambo = new Car();
		Plane boeing = new Plane();
		
		lambo.startEngine();
		lambo.startAccelerator(65);
		lambo.startBrake();
		System.out.println();
		boeing.startEngine();
		boeing.turnOnWings();
		
		
	}
}
