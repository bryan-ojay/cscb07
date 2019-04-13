package lab5;

public class Plane extends Vehicle {
	private Wings wings = new Wings();
	
	
	public void turnOnWings() {
		wings.enable(this);
	}
}
