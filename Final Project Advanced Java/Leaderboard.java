import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Leaderboard {

	// Create Report Table
	private static String createReportTable  = "CREATE TABLE IF NOT EXISTS Report (\n"
    		+ " userName 	TEXT 	NOT NULL, \n"
    		+ " score	 	INT 	NOT NULL\n"
    		+ ");";

	private static Leaderboard instance = null;


	private Leaderboard() {
		createReportTable();
	}

	public static Leaderboard getInstance() {
		if(instance == null) {
			instance = new Leaderboard();
		}
		return instance;
	}

	public void insertPlayer(String userName, int score) {
		Connection conn = null;
		try{
			String insertSql = "INSERT INTO Report VALUES(?, ?)";
			String url = "jdbc:sqlite:C:/sqlite3/java/connect/Report.db";
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(url);
			PreparedStatement stmt = conn.prepareStatement(insertSql);
			stmt.setString(1, userName);
			stmt.setFloat(2, score);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public List<String> findTopScore() {
		Connection conn = null;
		List<String> lst = new ArrayList<>();
		try {
			String getInfo = " SELECT * FROM Report ORDER BY score DESC LIMIT 10";
			String url = "jdbc:sqlite:C:/sqlite3/java/connect/Report.db";
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getInfo);
			while (rs.next()) {
				lst.add(rs.getString("name") + " : " + rs.getInt("score"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return lst;
	}


	private static void createReportTable() {
		Connection conn = null;
		try{
			String url = "jdbc:sqlite:C:/sqlite3/java/connect/Report.db";
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			stmt.execute(createReportTable);
			
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
			
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	
//    public static void main(String[] args) {
//    	
//    	
////	   	createNewDatabase(String fileName);
//        createReportTable();
//        
//        Username player1 = new Username("John" , 22);
//        
//        findTopScore(); // to static
//        insertPlayer("John", 22); // to static
//        //findAllPeople();
//        //deletePerson(person1);
//    }
	
}
