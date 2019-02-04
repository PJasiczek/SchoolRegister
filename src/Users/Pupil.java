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
		
		String query = "SELECT uwaga as 'Uwagi'\r\n" + "FROM uwaga\r\n" + "WHERE uczen_id= " + Long.toString(this.id)
		+ " GROUP BY uwaga_id";
		
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
		
		String query = "SELECT ocena as 'Ocena Koncowa', oceny as 'Oceny Czastkowe' " + "FROM ocena\r\n"
				+ "WHERE przedmiot_id = " + Long.toString(subjectId) + " AND uczen_id=" + Long.toString(this.id)
				+ " GROUP BY przedmiot_id";
		
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
		
		String query = "SELECT przedmiot.nazwa_przedmiotu as 'Przedmiot', ocena.ocena as 'Oceny Koncowe'\r\n"
				+ "FROM przedmiot, ocena\r\n" + "WHERE ocena.przedmiot_id=przedmiot.przedmiot_id\r\n"
				+ "and ocena.uczen_id= " + Long.toString(id) + " GROUP BY ocena.ocena_id";
		
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
		
		String query = "SELECT nauczyciel.imie as 'Imie', nauczyciel.nazwisko as 'Nazwisko', przedmiot.nazwa_przedmiotu as 'Przedmiot', nieobecnosc.data as 'Data'\r\n"
				+ "FROM nauczyciel, przedmiot, nieobecnosc\r\n"
				+ "WHERE nieobecnosc.nauczyciel_id=nauczyciel.nauczyciel_id and nieobecnosc.przedmiot_id=przedmiot.przedmiot_id\r\n"
				+ "and nieobecnosc.uczen_id= " + Long.toString(id) + " GROUP BY nieobecnosc.nieobecnosc_id";
		
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
		
		String query = "SELECT nauczyciel.imie as 'Imie', nauczyciel.nazwisko as 'Nazwisko', nauczyciel.tytul_naukowy as 'Tytul', "
				+ "nauczyciel.nr_kontaktowy as 'Numer kontaktowy', przedmiot.nazwa_przedmiotu as 'Prowadzony przedmiot'\r\n"
				+ "FROM nauczyciel, przedmiot\r\n" + "where przedmiot.nauczyciel_id=nauczyciel.nauczyciel_id\r\n"
				+ "AND nauczyciel.imie= '" + firstName + "' and nauczyciel.nazwisko= '"
				+ lastName + "' GROUP BY nauczyciel.nauczyciel_id";
		
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
