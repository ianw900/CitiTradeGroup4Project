package demo.morerest;

import javax.ws.rs.CookieParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/cookieusers")
public class CookieUserResource {

    @GET
    @Produces("text/plain")
    public String getText(@CookieParam("UserFavColour")@DefaultValue("Green")String fav) {
        return "Favourite colour: " + fav;
    }
}
