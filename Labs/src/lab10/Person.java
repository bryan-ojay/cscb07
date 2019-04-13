package lab10;

public class Person {

	//there should be 6 fields
	private final String firstName;   // required
	private final String lastName; 	  // required
	private final String dateOfBirth; // required
	private final String email; 	  // optional
	private final String address;     // optional
	private final String phoneNumber; // optional



	public static class Builder {

		// required parameters (3)
		private final String firstName;
		private final String lastName;
		private final String dateOfBirth;
		//TODO

		// optional parameters (3)-initialized to default values
		// (of course these should be more reasonable default values)
		// why are these not final?
		private String address = "N/A";
		private String phoneNumber = "N/A";
		private String email = "N/A";
		//TODO

		// Builder constructor with required fields (3)
		public Builder(String firstName, String lastName, String dob) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.dateOfBirth = dob;
		}

		//methods below are to change the default values of the optional parameters
		public Builder address(String address) {
			this.address = address;
			return this;
		}

		public Builder phoneNumber(String number) {
			this.phoneNumber = number;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Person build() {
			// the inner Builder class can call the
			// private constructor of the outer
			// Person class
			return new Person(this);
		}
	}

	private Person(Builder b) { // private constructor of the outer Person class
		this.firstName = b.firstName;
		this.lastName = b.lastName;
		this.dateOfBirth = b.dateOfBirth;
		this.address = b.address;
		this.phoneNumber = b.phoneNumber;
		this.email = b.email;
	}

	public String toString() {
		String fname = "First name: " + this.firstName;
		String lname = "Last name: " + this.lastName;
		String address = "Address: " + this.address;
		String dob = "Date of birth: " + this.dateOfBirth;
		String number = "Number: " + this.phoneNumber;
		String email = "Email: " + this.email;
		return fname + "\n" + lname + "\n" + dob + "\n" + address + "\n" + number + "\n" + email + "\n";
	}

}
