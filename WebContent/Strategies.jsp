<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Strategies</title>
</head>
<body>
	<h1>Strategy</h1>
	<h2><%
		//	getStrategy();
		%>
	</h2>
	<form>
	<table>
		<tr><td></td><td><input type="submit" value="Start""/></td></tr>
	</table>
	</form>
	<form>
	<table>
		<tr><td></td><td><input type="submit" value="Cancel"/></td></tr>
	</table>
	</form>
	<br></br>
	<label>Live Stock Feed</label>
	<table style='border: 3px solid red'>
			<tr><th style='border: 1px solid black'>Symbol</th>
			<th style='border: 1px solid black'>Price</th></tr>
			
			<%
// 		List<Stocks> stocks = DataAccess.getContactsObjects((request.getParameter("txtCountry")));
		
// 		for(Stocks s : stocks){
// 			out.println("<tr><td style='border: 1px solid black'>"+ s.getSymbol() + "</td>");
// 			out.println("<td style='border: 1px solid black'>"+ s.getBidPrice() + "</td>");
		%>
	</table>
	
	<table style='border: 3px solid red'>
			<tr><th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th></tr>
			
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