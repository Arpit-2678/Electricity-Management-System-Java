package electricity_management;

import java.sql.DriverManager;
import java.sql.Statement;

public class conn {
	java.sql.Connection c = null;
	Statement s;

	public conn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/details", "root", "");
			s = c.createStatement();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	public static void main(String[] args) throws Exception {

	}

}
