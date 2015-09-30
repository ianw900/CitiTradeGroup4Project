package demo.morerest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/querystringusers")
public class QueryStringUserResource {

    @GET
    @Produces("text/plain")
    public String getText(@QueryParam("empid")int empID,
                          @QueryParam("team")@DefaultValue("Swansea")String team) {

        String player = "I don't know!";
        if (team.equals("Swansea")) {
            switch (empID) {
                case 1:  player = "Fabianski";  break;
                case 8:  player = "Shelvey";    break;
                case 20: player = "Montero";    break;
            }
        } else if (team.equals("Liverpool")) {
            switch (empID) {
                case 1:  player = "Jones";    break;
                case 8:  player = "Gerrard";  break;
                case 20: player = "Lallana";  break;
            }
        }
        return "Player " + empID + " for " + team + " is: " + player;
    }

    @PUT
    @Consumes("text/plain")
    public void putText(String content) {
    }
}
