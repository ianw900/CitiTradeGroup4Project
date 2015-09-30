<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Get product</title>
	<link rel="stylesheet" href="styles/MyStylesheet.css">
    </head>
    <body>
        
        <h1>Get a product</h1>
        
        <form action="rest/productform/text" method="GET">
            <label for="ID">Product ID:</label>
            <input type="text" id="id" name="id">
            <input type="submit" value="Get as plain text"/>
        </form>

        <form action="rest/productform/xml" method="GET">
            <label for="ID">Product ID:</label>
            <input type="text" id="id" name="id"/>
            <input type="submit" value="Get as XML"/>
        </form>
        
        <form action="rest/productform/json" method="GET">
            <label for="ID">Product ID:</label>
            <input type="text" id="id" name="id"/>
            <input type="submit" value="Get as JSON"/>
        </form>

        <form action="rest/productform" method="GET">
            <label for="ID">Product ID:</label>
            <input type="text" id="id" name="id"/>
            <input type="submit" name="GetText" value="Get as plain text"/>
            <input type="submit" name="GetXml"  value="Get as XML"/>
            <input type="submit" name="GetJson" value="Get as JSON"/>
        </form>

    </body>
</html>
