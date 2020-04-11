<%@page import="bibliotecaWebApp.model.Libro"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<head>
<meta charset="ISO-8859-1">
<title>Stampa Libri</title>
</head>
<body>
<%@include file="navbar.jsp" %>
	<%
		List<Libro> listaLibri = (List<Libro>) request.getAttribute("listaLibri");
	%>





	<table class="table table-striped">

		<tr>
			<th>Titolo</th>
			<th>Autore</th>
			<th>Prezzo</th>
			<th>Disponibilità</th>
			<th>Quantità</th>
		</tr>
		<%
			for (Libro l : listaLibri) {
		%>

		<tr>
			<td><%=l.getTitolo()%></td>
			<td><%=l.getAutore()%></td>
			<td><%=l.getPrezzo()%></td>
			<td><%=l.getDisponibilita()%></td>
			<td><%=l.getQuantita()%></td>
		</tr>
		<%
			}
		%>

	</table>
	<br>
	<form action="indietro"method="post">
		<input type="submit" class="btn btn-outline-secondary btn-block"
			style="width: 150px; height: 50px; margin: auto"
			value="Torna Indietro">

	</form>
</body>
</html>