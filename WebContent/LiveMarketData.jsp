<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Live Market Data</title>
</head>
<body>
	<h1>Citi Trade</h1>
	<br></br>
	<input type="submit" value="My Trades"/>
	<br></br>
	<input type="text" name="txtStockSearch"/>
	<input type="submit" value="Search"/>
	<br></br>
	<label>Active Strategies / Favourites</label>
	<br></br>
	<table style='border: 3px solid red'>
			<tr><th style='border: 1px solid black'>Symbol</th>
			<th style='border: 1px solid black'>Bid Price</th>
			<th style='border: 1px solid black'>Ask Price</th>
			<th style='border: 1px solid black'>Bid Size</th>
			<th style='border: 1px solid black'>Ask Size</th></tr>
			
		<%
// 		List<Stocks> stocks = DataAccess.getContactsObjects((request.getParameter("txtCountry")));
		
// 		for(Stocks s : stocks){
// 			out.println("<tr><td style='border: 1px solid black'>"+ s.getSymbol() + "</td>");
// 			out.println("<td style='border: 1px solid black'>"+ s.getBidPrice() + "</td>");
// 			out.println("<td style='border: 1px solid black'>"+ s.getAskPrice() + "</td>");
// 			out.println("<td style='border: 1px solid black'>"+ s.getBidSize() + "</td>");
// 			out.println("<td style='border: 1px solid black'>"+ s.getaskSize() + "</td></tr>");
// 		}
		%>
	</table>
	<br></br>
	<label>All Market Data</label>
	<br></br>
	<table style='border: 3px solid red'>
			<tr><th style='border: 1px solid black'>Symbol</th>
			<th style='border: 1px solid black'>Bid Price</th>
			<th style='border: 1px solid black'>Ask Price</th>
			<th style='border: 1px solid black'>Bid Size</th>
			<th style='border: 1px solid black'>Ask Size</th></tr>
			
			<%
// 		List<Stocks> stocks = DataAccess.getContactsObjects((request.getParameter("txtCountry")));
		
// 		for(Stocks s : stocks){
// 			out.println("<tr><td style='border: 1px solid black'>"+ s.getSymbol() + "</td>");
// 			out.println("<td style='border: 1px solid black'>"+ s.getBidPrice() + "</td>");
// 			out.println("<td style='border: 1px solid black'>"+ s.getAskPrice() + "</td>");
// 			out.println("<td style='border: 1px solid black'>"+ s.getBidSize() + "</td>");
// 			out.println("<td style='border: 1px solid black'>"+ s.getaskSize() + "</td></tr>");
// 		}
		%>
	</table>

</body>
</html>