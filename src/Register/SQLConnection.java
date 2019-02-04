package Register;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/SchoolRegister", "root", "");
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
		return connection;
	}
}
