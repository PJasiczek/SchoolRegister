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
			
			String query = "SELECT przedmiot.przedmiot_id, przedmiot.nazwa_przedmiotu\r\n" + "FROM przedmiot, klasa\r\n"
					+ "WHERE klasa.klasa_id=? and przedmiot.klasa_id=klasa.klasa_id\r\n"
					+ "GROUP BY przedmiot.przedmiot_id";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, classId);
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
	
	public void pupilModification(long pupilId, String pesel, String firstName, String lastName, String address, String motherName, String fatherName, String contactNumber, String sex, String dateOfBirth) {
		
		try {
			
			Connection connection = SQLConnection.getConnection();
			
			String query = "UPDATE uczen SET PESEL='" + pesel + "'," + "imie='" + firstName + "'," + "nazwisko='" + lastName
					+ "'," + "adres_zamieszkania='" + address + "'," + "imie_matki='" + motherName + "'," + "imie_ojca='"
					+ fatherName + "'," + "nr_kontaktowy='" + contactNumber + "'," + "plec='" + sex + "'," + "data_urodzenia='" + dateOfBirth
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
			
			String query = "UPDATE uczen SET haslo_dostepu='" + password + "' WHERE id=" + pupilId + ";";
			
			PreparedStatement st = connection.prepareStatement(query);
			st.executeUpdate();
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
	}

}