package Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Register.SQLConnection;

public class Pupil extends Person{

	public Pupil(long id, String firstName, String lastName, long contactNumber, String dateOfBirth) {
		super(id, firstName, lastName, contactNumber, dateOfBirth);
		
	}
	
	public ResultSet lookOverNotes() {
		
		Connection connection = SQLConnection.getConnection();
		
		String query = "SELECT note as 'Uwagi'\r\n" + "FROM note\r\n" + "WHERE pupil_id= " + Long.toString(this.id)
		+ " GROUP BY note_id";
		
		try {
			
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			return rs;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}	
	}
	
	public ResultSet lookOverGrades(long subjectId) {
	
		Connection connection = SQLConnection.getConnection();
		
		String query = "SELECT grade as 'Ocena Koncowa', grades as 'Oceny Czastkowe' " + "FROM grade\r\n"
				+ "WHERE subject_id = " + Long.toString(subjectId) + " AND pupil_id=" + Long.toString(this.id)
				+ " GROUP BY subject_id";
		
		try {
			
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			return rs;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet lookOverFinalGrade() {
		
		Connection connection = SQLConnection.getConnection();
		
		String query = "SELECT subject.subject_name as 'Przedmiot', grade.grade as 'Oceny Koncowe'\r\n"
				+ "FROM subject, grade\r\n" + "WHERE grade.subject_id=subject.subject_id\r\n"
				+ "and grade.pupil_id= " + Long.toString(id) + " GROUP BY grade.grade_id";
		
		try {
			
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			return rs;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}

	}
	
	public ResultSet checkAbsence() {
		
		Connection connection = SQLConnection.getConnection();
		
		String query = "SELECT teacher.first_name as 'Imie', teacher.last_name as 'Nazwisko', subject.subject_name as 'Przedmiot', absence.date as 'Data'\r\n"
				+ "FROM teacher, subject, absence\r\n"
				+ "WHERE absence.teacher_id=teacher.teacher_id and absence.subject_id=subject.subject_id\r\n"
				+ "and absence.pupil_id= " + Long.toString(id) + " GROUP BY absence.absence_id";
		
		try {
			
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			return rs;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}

	}
	
	public static ResultSet searchInformationAboutTeachers(String firstName, String lastName) {
		
		Connection connection = SQLConnection.getConnection();
		
		String query = "SELECT teacher.first_name as 'Imie', teacher.last_name as 'Nazwisko', teacher.academic_title as 'Tytul', "
				+ "teacher.contact_number as 'Numer kontaktowy', subject.subject_name as 'Prowadzony przedmiot'\r\n"
				+ "FROM teacher, subject\r\n" + "where subject.teacher_id=teacher.teacher_id\r\n"
				+ "AND teacher.first_name= '" + firstName + "' and teacher.last_name= '"
				+ lastName + "' GROUP BY teacher.teacher_id";
		
		try {
			
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			return rs;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}
}
