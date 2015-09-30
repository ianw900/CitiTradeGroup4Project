package demo.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetQuotes {
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.out.println("Symbol, Ask Price, Ask Size, Bid Price, Bid Size");
		while(true){
			String[] stocks = {"AAPL","IBM","CSCO", "MSFT"};
			StringBuilder url = 
		            new StringBuilder("http://finance.yahoo.com/d/quotes.csv?s=");
	        for (String s : stocks)
	            url.append(s + ",");
	        //url.deleteCharAt(url.length()-1);
	        // Properties is for bid and ask
	        url.append("&f=saa5bb6&e=.csv");
	        
	        String theUrl = url.toString();
	        URL obj = new URL(theUrl);
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	        // This is a GET request
	        con.setRequestMethod("GET");
	        con.setRequestProperty("User-Agent", "Mozilla/5.0");
	        //int responseCode = con.getResponseCode();
	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        
	       /* while ((inputLine = in.readLine()) != null)
	        {
	            int n = inputLine.indexOf("\"");
	            String sub = inputLine.substring(n+1);
	            int m = sub.indexOf("\"");
	            String s = sub.substring(0, m);
	            String[] fields = inputLine.split(",\"" + s + "\",", -1);
	            System.out.println(s + ": " + fields[0] + "," + fields[1] + "," +
	            	fields[2] + "," + fields[3]);
	        }*/
	        
	        while((inputLine = in.readLine()) != null)
	        {
	        	//System.out.println(inputLine);
	        	String[] fields = inputLine.split(",");
	        	for(int i = 0; i < fields.length; i++){
	        		fields[0].replace('"', ' ');
	        		System.out.println(fields[i]);
	        	}
	        	System.out.println("------------");
	        	
	        }
		}
	}
	
	/*public String theContacts(String str) throws SQLException{

		String temp = "";
		Connection cn = null;

		try{
			cn = getConnection();
			PreparedStatement pst = cn.prepareStatement("SELECT name FROM contact WHERE name LIKE ? ORDER BY name");
			pst.setString(1, str + "%");
			ResultSet rs = pst.executeQuery();
			

			//temp += "<input type=\"text\" list=\"Contacts\">";

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
	}*/

}
