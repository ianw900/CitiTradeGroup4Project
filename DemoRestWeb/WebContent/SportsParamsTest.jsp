<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Times Table of a Number:</h1>
        <form action="rest/sports/team" method="POST">
            Enter number: <input type="text" name="table"> <br>
            <input type="submit" value="Get times tables"> <br>
        </form>
        <form action="rest/querystringusers" method="get"> <br>
            Enter ID: <input type="text" name="empid"> <br>
            Enter Team: <input type="text" name="team"> <br>
            <input type="submit" value="Query String Users">
        </form>
    </body>
</html>
