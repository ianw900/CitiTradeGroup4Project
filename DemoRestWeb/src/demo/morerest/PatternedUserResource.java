package demo.morerest;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

@Path("/patternedusers/{username:[a-zA-Z][a-zA-Z0-9]*}")
public class PatternedUserResource {

    @GET
    @Produces("text/plain")
    public String getText(@PathParam("username")String username) {
        if (username.equals("Angel")) {
            return "Hola Angel Rangel";
        } else if (username.equals("Joe")) {
            return "Pryhawn da Joe Allen";
        } else {
            return "Hi " + username;
        }
    }

    @PUT
    @Consumes("text/plain")
    public void putText(@PathParam("username")String username, String content) {
    }
}
