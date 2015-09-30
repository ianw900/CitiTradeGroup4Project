package demo.morerest;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/headerusers")
public class HeaderUserResource {

    @GET
    @Produces("text/plain")
    public String getText(@HeaderParam("User-agent")@DefaultValue("Unknown") String ua) {
            return "User agent: " + ua;
    }
}
