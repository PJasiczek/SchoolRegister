package Users;

public class Person {

	protected long id;
	protected long PESEL;
	protected String firstName;
	protected String lastName;
	protected long contactNumber;
	protected String dateOfBirth;

	public Person(long id, String firstName, String lastName, long contactNumber, String dateOfBirth) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNumber = contactNumber;
		this.dateOfBirth = dateOfBirth;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPESEL() {
		return PESEL;
	}

	public void setPESEL(long pESEL) {
		PESEL = pESEL;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirst_LastName() {
		return firstName + " " + lastName;
	}
	
	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
}
