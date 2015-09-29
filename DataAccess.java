package project.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataAccess {
	

	public static Connection getConnection()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/teamfour",
												"root", "password");
		}
		catch(SQLException ex)
		{
			System.out.println("Database Connection error " + ex);
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("Class not found " + ex);
		}
		return con;
	}
	
	public static void createTable() throws SQLException
	   {
		   Connection con = null;
		   try
		   {
			   con = getConnection();
			   Statement st = con.createStatement();	
			   st.executeUpdate("DROP TABLE IF EXISTS usertable");
			   st.executeUpdate("CREATE TABLE usertable("
			   		+ "forename VARCHAR (20),"
			   		+ "surname VARCHAR (20),"
			   		+ "username VARCHAR (20),"
			   		+ "password VARCHAR (20),"
			   		+ "isAdmin INT);");
			   
			   System.out.println("User Table created");
		   }
		   catch (SQLException ex) 
	       {
				System.out.println("Database error " + ex);
			}
		   finally
		   {
			   if(con!=null)
			   {
				   con.close();
			   }
		   }

	   }

	 public static void insertUser (String forename, String surname, String username, String password) throws Exception {
		 Scanner scanner = new Scanner (System.in);
		 List <String> insertUser = new ArrayList<>();
/*		 System.out.println("Please enter your first name: ");
		 String forename = scanner.next();
		 System.out.println("Please enter your surname: ");
		 String surname = scanner.next();
		 System.out.println("Please enter your username: ");
		 String username = scanner.next();
		 System.out.println("Please choose a password: ");
		 String password = scanner.next();
		 System.out.println("Please enter 1 for admin or 0 for user");
		 int isAdmin = scanner.nextInt();*/
		 
		 Connection con = null;
		   String html = "";
		   try
		   {
			   con = getConnection();
			   Statement st = con.createStatement();
			   st.executeUpdate ("INSERT INTO usertable (forename, surname, username, password) VALUES ('" + forename + "','" + surname + "','" + username + "','" + password + ");");
			   System.out.println("User successfully added to system.");
	   }catch (SQLException ex ) {
		  System.out.println(ex.getMessage());
	   }
	   }
	 
	 public static void main (String[]args){
/*			try {
				createTable();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				insertUser();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		*/
	 }
}
