package lab6;

public class Motorcycle extends Vehicle {
	protected double engineSize;
	
	public double getEngineSize() {
		return engineSize;
	}
	
	public void setEngineSize(double size) {
		this.engineSize = size;
	}
	
	public String toString() {
		return ("The motorcycle has a big engine, it's " + engineSize + 
				"L cubed");
	}
} 
