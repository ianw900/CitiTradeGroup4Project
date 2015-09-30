<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <%
        Cookie cookie = new Cookie("UserFavColour", "red");
        cookie.setMaxAge(60 * 60 * 24 * 28);     // Expire in 28 days
        response.addCookie(cookie);
        %>

        <a href="rest/cookieusers">Get Web service response</a> <br/>

    </body>
</html>
