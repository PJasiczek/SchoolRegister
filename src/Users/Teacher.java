package Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Register.SQLConnection;
import System.Class;
import System.Subject;

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
	
	public static void findClass(JComboBox<String> comboBoxClass,  ArrayList<Class> classList, JComboBox<String> comboBoxSubjects,  ArrayList<Subject> subjectsList, JComboBox<String> comboBoxPupils,  ArrayList<Pupil> pupilsList, long teacherId) {
		
		try {
			
			Connection connection = SQLConnection.getConnection();
			
			String query = "SELECT class_id, name\r\n" + "FROM class\r\n" + "GROUP BY class_id";

			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
				
			long classId = 0;
			
			while (rs.next()) {
				
				classId = rs.getLong("class_id");
				String name = rs.getString("name");
				classList.add(new Class(classId, name));
				comboBoxClass.addItem(name);
			}

		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
	}
	
	public static void findSubject(JComboBox<String> comboBoxSubjects,  ArrayList<Subject> subjectsList, long classId, long teacherId) {
		
		try {
			
			Connection connection = SQLConnection.getConnection();
			
			String query = "SELECT subject.subject_id, subject.subject_name\r\n" + "FROM subject, class\r\n"
					+ "WHERE class.class_id=? and subject.class_id=class.class_id\r\n"
					+ "and subject.teacher_id=? " + "GROUP BY subject.subject_id";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, classId);
			ps.setLong(2, teacherId);
			
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
	
	public static void findPupil(JComboBox<String> comboBoxPupils,  ArrayList<Pupil> pupilsList, long classId) {
		
		try {
			
			Connection connection = SQLConnection.getConnection();
			
			String query = "SELECT id, first_name, last_name, contact_number, date_of_birth\r\n" + "FROM pupil\r\n" + "WHERE class_id = ?";
			
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, classId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				long pupilId = rs.getLong("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				long contactNumber = rs.getLong("contact_number");
				String dateOfBirth = rs.getString("date_of_birth");
				
				pupilsList.add(new Pupil(pupilId, firstName, lastName, contactNumber, dateOfBirth));
				comboBoxPupils.addItem(firstName + " " + lastName);
			}

		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
	}
	
	public String insertGrade(long subjectId, long pupilId, String grades, String grade) { 
		
		boolean decision = true;
		
		try {		
			
			Connection connection = SQLConnection.getConnection();
			
			String query = "SELECT grade_id\r\n" + "FROM grade\r\n" + "WHERE pupil_id=? and subject_id=?";
	
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, pupilId);
			ps.setLong(2, subjectId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				rs.getLong("grade_id");
				decision = rs.next();
			}
		}catch (Exception ex) {
			
				JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		if(decision) {
			try {
				
				Connection connection = SQLConnection.getConnection();
				
				String query = "INSERT INTO grade (grade_id,subject_id,grades,grade,pupil_id) VALUES (?,?,?,?,?) ;";
				
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, null);
				ps.setLong(2, subjectId);
				ps.setString(3, grades);
				ps.setString(4, grade);
				ps.setLong(5, pupilId);
				ps.executeUpdate();
				ps.close();
	
			} catch (Exception ex) {
				
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
		else {
			
			try {
				Connection connection = SQLConnection.getConnection();
				
				String query = "UPDATE grade SET grades='" + grades + "'," + "grade='" + grade
						+ "' WHERE pupil_id=" + pupilId + ";";
				
				PreparedStatement st = connection.prepareStatement(query);
				st.executeUpdate();
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}
		return grades;
	}
	
	public String insertFinalGrade(long subjectId, long pupilId, String grades, String grade) { 
		
		boolean decision = true;
		
		try {		
			
			Connection connection = SQLConnection.getConnection();
			
			String query = "SELECT grade_id\r\n" + "FROM grade\r\n" + "WHERE pupil_id=? and subject_id=?";
	
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, pupilId);
			ps.setLong(2, subjectId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				rs.getLong("grade_id");
				decision = rs.next();
			}
		}catch (Exception ex) {
			
				JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		if(decision) {
			
			try {
				
				Connection connection = SQLConnection.getConnection();
				
				String query = "INSERT INTO grade (grade_id,subject_id,grades,grade,pupil_id) VALUES (?,?,?,?,?) ;";
				
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, null);
				ps.setLong(2, subjectId);
				ps.setString(3, grades);
				ps.setString(4, grade);
				ps.setLong(5, pupilId);
				ps.executeUpdate();
				ps.close();
	
			} catch (Exception ex) {
				
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
		else {
			try {
				
				Connection connection = SQLConnection.getConnection();
				
				String query = "UPDATE grade SET grades='" + grades + "'," + "grade='" + grade
						+ "' WHERE pupil_id=" + pupilId + ";";
				
				PreparedStatement st = connection.prepareStatement(query);
				st.executeUpdate();
				
			} catch (Exception ex) {
				
				ex.printStackTrace();
			}
			
		}
		return grade;
	}
	
	public String insertNote(long pupilId, String note) {
		
		if(note == null) {
			
			JOptionPane.showMessageDialog(null, "Wpisz treœæ uwagi w wyznczonym do tego miejscu");
			
			return null;
		}else {
			try {
				Connection connection = SQLConnection.getConnection();
	
				String query = "INSERT INTO note (note_id,note,pupil_id) VALUES (?,?,?) ;";
				PreparedStatement ps = connection.prepareStatement(query);
	
				ps.setString(1, null);
				ps.setString(2, note);
				ps.setLong(3, pupilId);
				ps.executeUpdate();
				ps.close();
				
			} catch (Exception ex) {
				
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			
			return note;
		}
	}
	
	public void checkPresence(long pupilId, long subjectId, long classId, String pupilName) {
		
		try {
			
			Connection connection = SQLConnection.getConnection();

			String query = "INSERT INTO absence (absence_id,pupil_id,subject_id,teacher_id,date,class_id) VALUES (?,?,?,?,?,?) ;";
			
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, null);
			ps.setLong(2, pupilId);
			ps.setLong(3, subjectId);
			ps.setLong(4, id);
			ps.setString(5, LocalDate.now().toString());
			ps.setLong(6, classId);
			ps.executeUpdate();
			ps.close();
			
			JOptionPane.showMessageDialog(null, "Pomyœlnie wprowadzono nieobecnoœæ dla ucznia " + pupilName);
		} catch (Exception ex) {
			
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	public ResultSet lookOverGrades(long classId, long subjectId) {
		
		String query = "SELECT pupil.register_number as 'Nr.', pupil.first_name as 'Imie', pupil.last_name as 'Nazwisko', pupil.date_of_birth as 'Data urodzenia', \r\n"
				+ "grade.grades as 'Oceny Czastkowe', grade.grade as 'Ocena Semestralna'\r\n" + "FROM pupil, grade\r\n"
				+ "WHERE grade.pupil_id=pupil.id\r\n" + "and pupil.class_id=?\r\n" + "and grade.subject_id=? \r\n" + "ORDER BY register_number";
		
		try {
			
			Connection connection = SQLConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, classId);
			ps.setLong(2, subjectId);
			ResultSet rs = ps.executeQuery();
			
			return rs;
		} catch (SQLException ex) {
			
			ex.printStackTrace();
			return null;
		}
	}

}
