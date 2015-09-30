package demo.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.*;

@Path("/countries")
public class CountriesString {
	
	@GET
	@Produces("text/plain")
	public String getText(@QueryParam("str") String str) throws SQLException{

		String temp = "";
		Connection cn = null;

		try{
			cn = getConnection();
			PreparedStatement pst = cn.prepareStatement("SELECT co.name Contact, c.Name Country FROM country c JOIN contact co ON c.id = co.countryid WHERE c.name = ?");
			pst.setString(1, str);
			ResultSet rs = pst.executeQuery();

			while(rs.next()){		
				temp += rs.getString(1) + "<br>";
			}
		}
		catch(SQLException ex){
			System.out.println("Error getting data: " + ex);
		}
		finally{
			if(cn != null){
				cn.close();
			}
		}
		return temp;
	}
		
	public static Connection getConnection(){

		Connection cn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/montestdb", "root", "password");
		}
		catch(SQLException ex){
			System.out.println("Database connection error: " + ex);
		}
		catch(ClassNotFoundException ex){
			System.out.println("Class not found: " + ex);
		}
		return cn;
	}

}
