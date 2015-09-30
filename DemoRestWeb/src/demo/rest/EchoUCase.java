package demo.rest;

import javax.ws.rs.*;

@Path("/querystr")
public class EchoUCase {
	
	@GET
	@Produces("text/plain")
	public String getText(@QueryParam("str") String str){
		return str.toUpperCase();
	}

}
