package lab6;

public class Lab6 {
  
  public static void main(String[] argv) {
   
    Vehicle vehicle1 = new Moped();
    Vehicle vehicle2 = new Car();
    Vehicle vehicle3 = new Vehicle();
    
    // TODO fix this line using casting
    Motorcycle motorcycle = (Motorcycle) vehicle1;

    // Note: This is okay, because Vehicle is a subclass of Object 
    Object object1 = vehicle1;
    
    // TODO one of these can be cast, the other will be a runtime error.
    // TODO comment out the runtime error, and cast the other line. 
    Moped moped1 = (Moped) vehicle1;
    //Moped moped2 = (Moped) vehicle3;
    
    
    System.out.println(object1);
    System.out.println(vehicle1);
    System.out.println(motorcycle);
   
    System.out.println(vehicle2);
    System.out.println(vehicle3);
    
    // TODO change this to moped1 or moped2, depending which will work 
    System.out.println(moped1);
  }
}
