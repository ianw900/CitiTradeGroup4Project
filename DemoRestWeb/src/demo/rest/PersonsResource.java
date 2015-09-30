package demo.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("persons")
public class PersonsResource {

    @GET
    @Produces("text/plain")
    public String getText() {
    	System.out.println("Received a GET request for persons");
    	return "Hello World!";
    }

    @PUT
    @Consumes("text/plain")
    public void putText(String content) {
    	System.out.println("Received a PUT request for persons");
    }
}