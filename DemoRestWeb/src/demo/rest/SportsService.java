package demo.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;

@Path("sports/{test}")
public class SportsService {

	@GET
	@Produces("text/plain")
	public String getText(@PathParam("team")String team, @PathParam("empid")String id) {
		
		System.out.println("Received a GET request for sports");
		
		
		return team + " are the ultimate rugby warriors";
	}

	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces("text/html")
	public String getTable(@FormParam("table")String table) {
		int table2 = Integer.parseInt(table);
		String results = "";
		
		for(int i = 1; i <= table2; i++){
			results += table + "x" + i + " = " + table2*i + "<br>";
		}

		return results.toString();
	}
}
