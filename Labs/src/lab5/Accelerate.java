package lab5;

public class Accelerate {
	
	public void enable(int kmPerHour, Vehicle vehicle) {
		System.out.println(vehicle.getClass().getSimpleName() + " accelerate by " + kmPerHour + " km/hr.");
	}
	
}
