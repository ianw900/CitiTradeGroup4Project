package demo.morerest;

import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Path("/httpinfousers")
public class HttpInfoUserResource {

    @Context
    private ServletConfig servletConfig;

    @Context
    private ServletContext servletContext;

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    @GET
    @Produces("text/plain")
    public String getText() {

        // Demonstrate accessing ServletContext info.
        String dbInitParam = servletContext.getInitParameter("DatabaseURL");

        // Demonstrate accessing ServletConfig info.
        String myMessage = servletConfig.getInitParameter("MyMessage");

        // Demonstrate accessing HttpServletRequest info.
        boolean isManager = request.isUserInRole("Manager");

        // Demonstrate accessing HttpServletResponse info.
        response.addCookie(new Cookie("Timestamp", new Date().toString()));

        return "Database URL (context param): " + dbInitParam + "\n" +
               "My message   (servlet param): " + myMessage   + "\n" +
               "Are you a manager? " + isManager;
    }
}
