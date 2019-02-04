package Users;

public class Teacher extends Pupil {

	protected String academicTitle;
	
	public Teacher(long id, String firstName, String lastName, long contactNumber, String dateOfBirth, String academicTitle) {
		super(id, firstName, lastName, contactNumber, dateOfBirth);
		this.academicTitle = academicTitle;
		
	}

	public String getAcademicTitle() {
		return academicTitle;
	}

	public void setAcademicTitle(String academicTitle) {
		this.academicTitle = academicTitle;
	}
	
}
