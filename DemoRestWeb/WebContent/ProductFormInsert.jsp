<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert product</title>
	<link rel="stylesheet" href="styles/MyStylesheet.css">
    </head>
    <body>
        <h1>Insert product</h1>

        <form action="rest/productform" method="POST">
            <label for="description">Description:</label>
            <input type="text" id="description" name="description"> <br>

            <label for="unitPrice">Unit price:</label>
            <input type="text" id="unitPrice" name="unitPrice">  <br>
            
            <input type="submit" value="Insert">
        </form>

    </body>
</html>
