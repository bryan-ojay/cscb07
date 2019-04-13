package lab5;

public class Vehicle {
	private Brake brake = new Brake();
	private Accelerate accelerate = new Accelerate();
	private Engine engine = new Engine();
	
	public void startBrake() {
		brake.enable(this);
	}
	
	public void startEngine() {
		engine.enable(this);
	}
	
	public void startAccelerator(int kmPerHour) {
		accelerate.enable(kmPerHour, this);
	}
	
}
