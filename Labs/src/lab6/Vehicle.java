package lab6;

public class Vehicle {
	protected double speed;
	protected int numOfWheels;
	
	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double newSpeed) {
		this.speed = newSpeed;
	}
	
	public void setNumberOfWheels(int wheels) {
		this.numOfWheels = wheels;
	}
	
	public int getNumberOfWheels() {
		return numOfWheels;
	}
	
	public String toString() {
		return ("Vehicle with " + numOfWheels + " wheels, travelling at " +
				"speed " + speed);
	}
	
	
}
