<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Please provide the following details:</h1>
        <form action="rest/formusers" method="POST">
            Player name: <input type="text" name="playerName"> <br>
            Team:        <input type="text" name="team">  <br>
            <input type="submit" value="Add">
        </form>
    </body>
</html>
