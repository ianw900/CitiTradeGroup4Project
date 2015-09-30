package demo.rest;

import javax.ws.rs.*;

@Path("/querystrlines")
public class EchoUCaseN {
	
	@GET
	@Produces("text/plain")
	public String getText(@QueryParam("str") String str, @QueryParam("repeatCount") int repeatCount){
		String returnString = "";
		
		for(int i = 0; i < repeatCount; i++){
			returnString += str + "<br>";
		}

		return returnString.toUpperCase();
	}

}
