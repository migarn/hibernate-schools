import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCMain {
	public static void main(String[] args) {
		executeSQL();
	}

	private static void executeSQL() {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:school.db", "", "");
			stmt = conn.createStatement();
			String sql = "SELECT * FROM schools";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println("School name: " + rs.getString("name"));
				System.out.println("       address: " + rs.getString("address"));
			}
			rs.close();
			
			System.out.println("");
			
			String sql2 = "SELECT * FROM schoolClasses";
			ResultSet rs2 = stmt.executeQuery(sql2);
			while (rs2.next()) {
				System.out.println("Class: " + rs2.getString("profile"));
			}
			rs2.close();	
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
