package demo.morerest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/users/{surname}/{firstname}")
public class MultipleVariablesUserResource {

    @GET
    @Produces("text/plain")
    public String getText(@PathParam("surname")String surname,
                          @PathParam("firstname")String firstname) {

        return "Your surname is " + surname + " and your firstname is " + firstname;
    }

    @PUT
    @Consumes("text/plain")
    public void putText(@PathParam("surname")String surname,
                        @PathParam("firstname")String firstname, String content) {
    }
}
