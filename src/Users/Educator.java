package Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JComboBox;

import Register.SQLConnection;
import System.Subject;

public class Educator extends Teacher {

	public Educator(long id, String firstName, String lastName, long contactNumber, String dateOfBirth, String academicTitle) {
		super(id, firstName, lastName, contactNumber, dateOfBirth, academicTitle);
		
	}
	
	public static void findSubject(JComboBox<String> comboBoxSubjects,  ArrayList<Subject> subjectsList, long classId) {
		
		try {
			
			Connection connection = SQLConnection.getConnection();
			
			String query = "SELECT subject.subject_id, subject.subject_name\r\n" + "FROM subject, class\r\n"
					+ "WHERE class.class_id=? and subject.class_id=class.class_id\r\n"
					+ "GROUP BY subject.subject_id";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, classId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				long subjectId = rs.getLong("subject_id");
				String name = rs.getString("subject_name");
				subjectsList.add(new Subject(subjectId, name));
				comboBoxSubjects.addItem(name);
			}
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
	}
	
	public void pupilModification(long pupilId, String pesel, String firstName, String lastName, String address, String motherName, String fatherName, String contactNumber, String sex, String dateOfBirth) {
		
		try {
			
			Connection connection = SQLConnection.getConnection();
			
			String query = "UPDATE pupil SET PESEL='" + pesel + "'," + "first_name='" + firstName + "'," + "last_name='" + lastName
					+ "'," + "address='" + address + "'," + "mother_name='" + motherName + "'," + "father_name='"
					+ fatherName + "'," + "contact_number='" + contactNumber + "'," + "sex='" + sex + "'," + "date_of_birth='" + dateOfBirth
					+ "' WHERE id=" + pupilId + ";";
			
			PreparedStatement st = connection.prepareStatement(query);
			st.executeUpdate();
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
	}
	
	public void changePasswordToLoginPupil(long pupilId, String password) {
		
		try {
			
			Connection connection = SQLConnection.getConnection();
			
			String query = "UPDATE pupil SET password='" + password + "' WHERE id=" + pupilId + ";";
			
			PreparedStatement st = connection.prepareStatement(query);
			st.executeUpdate();
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
	}

}