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
			
			String query = "SELECT klasa_id, nazwa\r\n" + "FROM klasa\r\n" + "GROUP BY klasa_id";

			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
				
			long classId = 0;
			
			while (rs.next()) {
				
				classId = rs.getLong("klasa_id");
				String name = rs.getString("nazwa");
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
			
			String query = "SELECT przedmiot.przedmiot_id, przedmiot.nazwa_przedmiotu\r\n" + "FROM przedmiot, klasa\r\n"
					+ "WHERE klasa.klasa_id=? and przedmiot.klasa_id=klasa.klasa_id\r\n"
					+ "and przedmiot.nauczyciel_id=? " + "GROUP BY przedmiot.przedmiot_id";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, classId);
			ps.setLong(2, teacherId);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				long subjectId = rs.getLong("przedmiot_id");
				String name = rs.getString("nazwa_przedmiotu");
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
			
			String query = "SELECT id, imie, nazwisko, nr_kontaktowy, data_urodzenia\r\n" + "FROM uczen\r\n" + "WHERE klasa_id = ?";
			
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, classId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				long pupilId = rs.getLong("id");
				String firstName = rs.getString("imie");
				String lastName = rs.getString("nazwisko");
				long contactNumber = rs.getLong("nr_kontaktowy");
				String dateOfBirth = rs.getString("data_urodzenia");
				
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
			
			String query = "SELECT ocena_id\r\n" + "FROM ocena\r\n" + "WHERE uczen_id=? and przedmiot_id=?";
	
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, pupilId);
			ps.setLong(2, subjectId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				rs.getLong("ocena_id");
				decision = rs.next();
			}
		}catch (Exception ex) {
			
				JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		if(decision) {
			try {
				
				Connection connection = SQLConnection.getConnection();
				
				String query = "INSERT INTO ocena (ocena_id,przedmiot_id,oceny,ocena,uczen_id) VALUES (?,?,?,?,?) ;";
				
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
				
				String query = "UPDATE ocena SET oceny='" + grades + "'," + "ocena='" + grade
						+ "' WHERE uczen_id=" + pupilId + ";";
				
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
			
			String query = "SELECT ocena_id\r\n" + "FROM ocena\r\n" + "WHERE uczen_id=? and przedmiot_id=?";
	
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, pupilId);
			ps.setLong(2, subjectId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				rs.getLong("ocena_id");
				decision = rs.next();
			}
		}catch (Exception ex) {
			
				JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		if(decision) {
			
			try {
				
				Connection connection = SQLConnection.getConnection();
				
				String query = "INSERT INTO ocena (ocena_id,przedmiot_id,oceny,ocena,uczen_id) VALUES (?,?,?,?,?) ;";
				
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
				
				String query = "UPDATE ocena SET oceny='" + grades + "'," + "ocena='" + grade
						+ "' WHERE uczen_id=" + pupilId + ";";
				
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
	
				String query = "INSERT INTO uwaga (uwaga_id,uwaga,uczen_id) VALUES (?,?,?) ;";
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

			String query = "INSERT INTO nieobecnosc (nieobecnosc_id,uczen_id,przedmiot_id,nauczyciel_id,data,klasa_id) VALUES (?,?,?,?,?,?) ;";
			
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
		
		String query = "SELECT uczen.numer_z_dziennika as 'Nr.', uczen.imie as 'Imie', uczen.nazwisko as 'Nazwisko', uczen.data_urodzenia as 'Data urodzenia', \r\n"
				+ "ocena.oceny as 'Oceny Czastkowe', ocena.ocena as 'Ocena Semestralna'\r\n" + "FROM uczen, ocena\r\n"
				+ "WHERE ocena.uczen_id=uczen.id\r\n" + "and uczen.klasa_id=?\r\n" + "and ocena.przedmiot_id=? \r\n" + "ORDER BY numer_z_dziennika";
		
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
