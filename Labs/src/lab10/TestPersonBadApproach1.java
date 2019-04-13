package lab10;

public class TestPersonBadApproach1 {

	public static void main(String[] args) {

		PersonBadApproach1 John = new PersonBadApproach1("John", "Doe", "01/23/1997", 
				"17 Ash Drive", 
				"647-303-2929",
				"hi@gmail.com");
		PersonBadApproach1 Jane = new PersonBadApproach1("Jane", "Doe", "03/02/1995",
				"17 Ash Drive", 
				"416-393-8924", 
				"bye@live.com");

		System.out.println(John);
		System.out.println(Jane);

	}
}
