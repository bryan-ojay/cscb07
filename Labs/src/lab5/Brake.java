package lab5;

public class Brake {

	public void enable(Vehicle vehicle) {
		System.out.println(vehicle.getClass().getSimpleName() + " brake - engine stopped.");
	}
	
}
