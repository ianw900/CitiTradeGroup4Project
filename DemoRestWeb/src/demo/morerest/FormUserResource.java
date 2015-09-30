package demo.morerest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

@Path("/formusers")
public class FormUserResource {

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public String getText(@FormParam("playerName")String playerName,
                          @FormParam("team")@DefaultValue("Swansea")String team) {

        return "Added " + playerName + " to " + team;
    }
}
