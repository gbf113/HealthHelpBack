
package nz.ac.unitec.dbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
//	private static final String DBDRIVER = "com.mysql.jdbc.Driver";
//	private static final String DBURL = "jdbc:mysql://localhost:3306/example";
//	private static final String DBUSER = "root";
//	private static final String DBPASS = "12345678";
	
	private static final String DBDRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://us-cdbr-iron-east-03.cleardb.net/ad_f894f8aa118a5e9";
	private static final String DBUSER = "b79da5e5339fe8";
	private static final String DBPASS = "04b92b72";
	private Connection connection = null;

	public DatabaseConnection() throws Exception {
		try {

			Class.forName(DBDRIVER);
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

		} catch (Exception exception) {
			throw exception;
		} finally {
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void close() throws Exception {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				throw e;
			}
		}

	}

}
