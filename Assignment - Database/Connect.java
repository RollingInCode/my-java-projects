//Franklyn Gonzalez, last edit, 11/25/2019

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


public class Connect {
	
	public static Connection conn = null;
	
	public static void createNewDatabase(String fileName) {
		
		
		String url = "jdbc:sqlite:C:/sqlite3/java/connect/Person.db";
        
		
	try (Connection conn = DriverManager.getConnection(url)) {
		if (conn != null) {
			DatabaseMetaData meta = conn.getMetaData();
			System.out.println("The driver name is " + meta.getDriverName());
			System.out.println("A new database has been created.");
		}
	}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}


	
     /**
     * Connect to a sample database
     * 
     */
    public static Statement connect() {
   
    	
        try {
        	Class.forName("org.sqlite.JDBC");			
			conn = DriverManager.getConnection("jdbc:sqlite:C:/sqlite3/java/connect/Person.db");
			System.out.println("Connection to SQLite has been established.");
			Statement stmt = conn.createStatement();
			     
	        
		} catch (SQLException | ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
        return null;
    }
    
    
    public static Connection getConnection() {
    	Connection conn = null;
        
        try {
            // db parameters
            
            // create a connection to the database
            
        	Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:C:/sqlite3/java/connect/Person.db");
			System.out.println("Database created.");
			
        } catch (Exception e) {
        	System.out.println(e);
        	conn = null;
        }
        	return conn;
    }

    	public static Connection dataTable() {
    		Connection conn = null;
    		Statement stmt = null;
    	
    	try {
    	conn = getConnection();
    	stmt = conn.createStatement();
            
            String sql = "CREATE TABLE IF NOT EXISTS Person (\n"
            		+ " firstName 	TEXT 	NOT NULL, \n"
            		+ " lastName 	TEXT 	NOT NULL, \n"
            		+ " age 		INT 	NOT NULL, \n"
            		+ " ssn		 	LONG 	NOT NULL, \n"
            		+ " creditCard	LONG 	NOT NULL\n"
            		+ ");";
            // Create a new table
            stmt.execute(sql);
            stmt.close();
            conn.close();
            System.out.println("Table has been created.");
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    		conn = null;
   	}
        return conn;
    	}

 
    
    
    public static void insertPerson(Person person) {
       	String sql = "INSERT INTO Person VALUES(?,?,?,?,?)";
      	PreparedStatement pstmt = null;
       	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getFirstName());
			pstmt.setString(2, person.getLastName());
			pstmt.setInt(3, person.getAge());
			pstmt.setLong(4, person.getSsn());
			pstmt.setLong(5, person.getCreditCard());
			pstmt.executeUpdate(sql);
			System.out.println("[" + person.getLastName() + "," + person.getFirstName() + "]");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			}
			catch(SQLException e)  {
				e.printStackTrace();
			}
		}
    }
    
    
    public static void deletePerson(Person person) {
    	
    	String sql = "DELETE FROM Person WHERE firstName=? and lastName=?";
    	PreparedStatement pstmt = null;
    	try {   
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, person.getFirstName());
			pstmt.setString(2, person.getLastName());
			pstmt.executeUpdate(sql);
			System.out.println("[" + person.getLastName() + "," + person.getFirstName() + "]");
			
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			}
			catch(SQLException e)  {
				e.printStackTrace();
			}
		}
    }
    	
	public static ArrayList<Person> findAllPeople() {
		
		Connection conn = null;
		Statement stmt = null;
		ArrayList<Person> people = new ArrayList<>();
		
		try {
		conn = getConnection();
		stmt = conn.createStatement();
    	
		ResultSet rs = stmt.executeQuery("SELECT * FROM Person;");
		while (rs.next()) {
			people.add(new Person(rs.getString("firstName"),  rs.getString("lastName"), rs.getInt("age"), 
					rs.getLong("ssn"), rs.getLong("creditCard")));
	
		}
		rs.close();
		stmt.close();
		conn.close();
			
		} catch (Exception e) {
			System.out.println(e);
			people = null;
		}
		return people;
}
		
	
    	
    
    public static void main(String[] args) {
    	
    	
//	   	createNewDatabase(String fileName);
        connect();
        
        Person person1 = new Person("John","Macker",22,333333333, 0000045600000);
        
        dataTable();
        insertPerson(person1);
        findAllPeople();
        deletePerson(person1);
    }
}
    
